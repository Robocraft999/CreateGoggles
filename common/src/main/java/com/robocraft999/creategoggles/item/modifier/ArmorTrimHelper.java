package com.robocraft999.creategoggles.item.modifier;

import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class ArmorTrimHelper {
    public static boolean hasMaterialAndPattern(ItemStack stack, ResourceLocation requiredMaterial, ResourceLocation requiredPattern){
        var optionalTrim = stack.get(DataComponents.TRIM);
        return optionalTrim != null && optionalTrim.material().is(requiredMaterial) && optionalTrim.pattern().is(requiredPattern);
    }
}
