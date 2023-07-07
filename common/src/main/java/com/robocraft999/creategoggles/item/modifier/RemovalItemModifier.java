package com.robocraft999.creategoggles.item.modifier;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class RemovalItemModifier extends ItemModifier{
    public RemovalItemModifier(String name, Predicate<ItemStack> predicate, TagKey<Item> incompatibleItems) {
        super(name, predicate, incompatibleItems);
    }

    @Override
    public void apply(ItemStack stack) {
        ItemModifierManager.removeModifier(stack);
    }
}
