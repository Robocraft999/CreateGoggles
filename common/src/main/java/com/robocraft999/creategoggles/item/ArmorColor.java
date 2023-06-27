package com.robocraft999.creategoggles.item;

import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;

public class ArmorColor implements ItemColor {
    @Override
    public int getColor(ItemStack itemStack, int layer) {
        return layer > 0 ? -1 : ((DyeableLeatherItem)itemStack.getItem()).getColor(itemStack);
    }
}
