package com.robocraft999.creategoggles.item.modifier;

import com.robocraft999.creategoggles.CreateGoggles;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.Predicate;

public class ItemModifier {
    private final ResourceLocation name;
    private final Predicate<ItemStack> predicate;
    private final TagKey<Item> incompatibleItems;
    private List<ItemStack> cachedValidItems;

    public ItemModifier(String name, Predicate<ItemStack> predicate, TagKey<Item> incompatibleItems) {
        this.name = CreateGoggles.asResource(name);
        this.predicate = predicate;
        this.incompatibleItems = incompatibleItems;
    }

    public void apply(ItemStack stack){}

    public TagKey<Item> getIncompatibleItems() {
        return this.incompatibleItems;
    }

    public ResourceLocation getRegistryName() {
        return this.name;
    }

    public Component getHintComponent() {
        return new TranslatableComponent("hint." + CreateGoggles.MOD_ID + ".modifier." + getRegistryName().getPath()).withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY);
    }

    public boolean isItemValid(ItemStack stack) {
        return !(stack.is(getIncompatibleItems()) || !this.predicate.test(stack) || ItemModifierManager.hasSpecificModifier(stack, this));
    }

    public List<ItemStack> getValidItems() {
        if (this.cachedValidItems == null) {
            this.cachedValidItems = Registry.ITEM.stream().map(ItemStack::new).filter(this::isItemValid).toList();
        }
        return this.cachedValidItems;
    }

    public void clearCachedValidItems() {
        this.cachedValidItems = null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ItemModifier modifier) {
            return modifier.getRegistryName().equals(this.getRegistryName());
        }
        return false;
    }
}
