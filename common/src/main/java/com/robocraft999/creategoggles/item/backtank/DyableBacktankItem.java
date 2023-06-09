package com.robocraft999.creategoggles.item.backtank;

import com.simibubi.create.content.equipment.armor.BacktankItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class DyableBacktankItem extends BacktankItem implements DyeableLeatherItem {
    public DyableBacktankItem(ArmorMaterial material, Properties properties, ResourceLocation textureLoc, Supplier<BacktankBlockItem> placeable) {
        super(material, properties, textureLoc, placeable);
    }

    @Override
    public void setColor(@Nonnull ItemStack stack, int color) {
        DyeableLeatherItem.super.setColor(stack, color);
    }
}
