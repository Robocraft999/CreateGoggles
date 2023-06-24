package com.robocraft999.creategoggles.item.backtank;

import com.simibubi.create.content.equipment.armor.BacktankBlock;
import com.simibubi.create.content.equipment.armor.BacktankBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

import static com.robocraft999.creategoggles.CreateGoggles.REGISTRATE;

public class ArmoredBacktankBlock extends BacktankBlock {
    String typeId;

    public ArmoredBacktankBlock(String id, Properties properties) {
        super(properties);
        this.typeId = id;
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter p_185473_1_, BlockPos p_185473_2_, BlockState p_185473_3_) {
        ItemStack item = new ItemStack(REGISTRATE.get(typeId, Registry.ITEM_REGISTRY).get());
        Optional<BacktankBlockEntity> tileEntityOptional = getBlockEntityOptional(p_185473_1_, p_185473_2_);

        int air = tileEntityOptional.map(BacktankBlockEntity::getAirLevel)
                .orElse(0);
        CompoundTag tag = item.getOrCreateTag();
        tag.putInt("Air", air);

        ListTag enchants = tileEntityOptional.map(BacktankBlockEntity::getEnchantmentTag)
                .orElse(new ListTag());
        if (!enchants.isEmpty()) {
            ListTag enchantmentTagList = item.getEnchantmentTags();
            enchantmentTagList.addAll(enchants);
            tag.put("Enchantments", enchantmentTagList);
        }

        tileEntityOptional.map(BacktankBlockEntity::getCustomName).ifPresent(item::setHoverName);
        return item;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return REGISTRATE.get(this.typeId, Registry.BLOCK_ENTITY_TYPE_REGISTRY).get().create(pos, state);
    }
}
