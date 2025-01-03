package com.robocraft999.creategoggles.item.modifier;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class ArmorTrimHelper {


    public static boolean hasMaterialAndPattern(ItemStack stack, ResourceLocation requiredMaterial, ResourceLocation requiredPattern){
        if (stack.getTag() != null && stack.getTag().contains("Trim")){
            CompoundTag trimTag = stack.getTag().getCompound("Trim");
            ResourceLocation material = new ResourceLocation(trimTag.getString("material"));
            ResourceLocation pattern = new ResourceLocation(trimTag.getString("pattern"));

            return material.equals(requiredMaterial) && pattern.equals(requiredPattern);
        }

        return false;
    }
}
