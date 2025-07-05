package com.robocraft999.creategoggles.item;

import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

public class ArmorColor implements ItemColor {
    @Override
    public int getColor(ItemStack itemStack, int layer) {
        return layer > 0 ? -1 : DyedItemColor.getOrDefault(itemStack, DyedItemColor.LEATHER_COLOR);
    }
}
