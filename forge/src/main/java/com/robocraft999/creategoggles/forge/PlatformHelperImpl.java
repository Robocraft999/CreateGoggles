package com.robocraft999.creategoggles.forge;

import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class PlatformHelperImpl {

    public static <B extends Block, P> NonNullUnaryOperator<BlockBuilder<B, P>> backtank(@NotNull Supplier<ItemLike> drop){
        return (b) -> ((b.blockstate((c, p) -> p.horizontalBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p))).transform(TagGen.pickaxeOnly())).addLayer(() -> RenderType::cutoutMipped)).loot((lt, block) -> {
            LootTable.Builder builder = LootTable.lootTable();
            LootItemCondition.Builder survivesExplosion = ExplosionCondition.survivesExplosion();
            lt.add(block, builder.withPool(LootPool.lootPool().when(survivesExplosion).setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(drop.get()).apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy("VanillaTag", "{}", CopyNbtFunction.MergeStrategy.MERGE)).apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy("Air", "Air")))));
        });
    }
}
