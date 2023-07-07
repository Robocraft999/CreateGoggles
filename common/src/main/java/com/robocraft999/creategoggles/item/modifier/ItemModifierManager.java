package com.robocraft999.creategoggles.item.modifier;

import com.robocraft999.creategoggles.CGConfig;
import com.robocraft999.creategoggles.registry.CGItemModifiers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;

import static com.robocraft999.creategoggles.CreateGoggles.REGISTRATE;

public class ItemModifierManager {

    public static ItemModifier getModifier(ItemStack stack) {
        if (stack.isEmpty() || !stack.hasTag()){
            return null;
        }

        String modifierName = Objects.requireNonNull(stack.getTag()).getString("Modifier");
        return REGISTRATE.get(new ResourceLocation(modifierName).getPath(), CGItemModifiers.ITEM_MODIFIER_REGISTRY).orElse(null);
    }

    public static void setModifier(ItemStack stack, ItemModifier modifier) {
        if (CGConfig.COMMON.enableExperimentalFeatures.get()){
            stack.getOrCreateTag().putString("Modifier", modifier.getRegistryName().toString());

            modifier.apply(stack);
        }
    }

    public static boolean hasModifier(ItemStack stack) {
        return stack.getOrCreateTag().contains("Modifier");
    }

    public static boolean hasSpecificModifier(ItemStack stack, ItemModifier modifier) {
        return hasModifier(stack) && getModifier(stack).equals(modifier);
    }

    //maybe add hasSpecificModifier
}
