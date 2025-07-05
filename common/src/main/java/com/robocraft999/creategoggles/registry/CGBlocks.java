package com.robocraft999.creategoggles.registry;

import com.robocraft999.creategoggles.item.backtank.CGBacktankBlock;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllDataComponents;
import com.simibubi.create.api.stress.BlockStressValues;
import com.simibubi.create.content.equipment.armor.BacktankBlock;
import com.simibubi.create.content.equipment.armor.BacktankItem;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import static com.robocraft999.creategoggles.CreateGoggles.REGISTRATE;

public class CGBlocks {
    public static final BlockEntry<? extends BacktankBlock>
            CHAINMAIL_BACKTANK_BLOCK = backtankBlock("chainmail_backtank", CGItems.CHAINMAIL_BACKTANK),
            DIAMOND_BACKTANK_BLOCK = backtankBlock("diamond_backtank", CGItems.DIAMOND_BACKTANK),
            GOLDEN_BACKTANK_BLOCK = backtankBlock("golden_backtank", CGItems.GOLDEN_BACKTANK),
            IRON_BACKTANK_BLOCK = backtankBlock("iron_backtank", CGItems.IRON_BACKTANK),
            LEATHER_BACKTANK_BLOCK = backtankBlock("leather_backtank", CGItems.LEATHER_BACKTANK);
    private static BlockEntry<CGBacktankBlock> backtankBlock(String name, ItemEntry<? extends BacktankItem> type) {
        return REGISTRATE.block(name, CGBacktankBlock::new)
                .initialProperties(SharedProperties::copperMetal)
                .transform(backtank(type::get))
                .register();
    }

    public static <B extends Block, P> NonNullUnaryOperator<BlockBuilder<B, P>> backtank(@NotNull Supplier<ItemLike> drop){
        return (b) -> ((b.blockstate((c, p) -> p
                        .horizontalBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
                .transform(TagGen.pickaxeOnly()))
                .addLayer(() -> RenderType::cutoutMipped))
                .loot((lt, block) -> {
                    LootTable.Builder builder = LootTable.lootTable();
                    LootItemCondition.Builder survivesExplosion = ExplosionCondition.survivesExplosion();
                    lt.add(block, builder.withPool(LootPool.lootPool()
                            .when(survivesExplosion)
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(drop.get())
                                    .apply(CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)
                                            .include(AllDataComponents.BACKTANK_AIR)))));
                });
    }

    private static DoubleSupplier getImpact(Block block) {
        if (!(block instanceof CGBacktankBlock)) return () -> 0;
        return () -> BlockStressValues.getImpact(AllBlocks.COPPER_BACKTANK.get());
    }

    public static void register() {
        BlockStressValues.IMPACTS.registerProvider(CGBlocks::getImpact);
    }
}
