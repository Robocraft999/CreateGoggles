package com.robocraft999.creategoggles.forge.mixin;

import com.robocraft999.creategoggles.item.modifier.ItemModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemModifier.class)
public class ItemModifierMixin implements IForgeRegistryEntry<ItemModifier> {
    @Shadow
    @Final
    private ResourceLocation name;

    @Override
    public ItemModifier setRegistryName(ResourceLocation arg) {
        return (ItemModifier) (Object)this;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return name;
    }

    @Override
    public Class<ItemModifier> getRegistryType() {
        return ItemModifier.class;
    }
}
