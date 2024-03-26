package com.robocraft999.creategoggles.item.modifier;

import com.robocraft999.creategoggles.CGConfig;
import com.robocraft999.creategoggles.registry.CGItemModifiers;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;

import static com.robocraft999.creategoggles.CreateGoggles.REGISTRATE;

/**
 * Code from Forbidden and Arcanus
 * @author stal111
 *
 * edited by Robocraft999
 */
public class ItemModifierManager {

    private static final String TAG_NAME = "CGModifier";

    public static RegistryEntry<ItemModifier> getModifier(ItemStack stack) {
        if (stack.isEmpty() || !stack.hasTag()){
            return null;
        }

        String modifierName = Objects.requireNonNull(stack.getTag()).getString(TAG_NAME);
        return REGISTRATE.getOptional(new ResourceLocation(modifierName).getPath(), CGItemModifiers.ITEM_MODIFIER_REGISTRY);
    }

    public static void setModifier(ItemStack stack, ItemModifier modifier) {
        if (CGConfig.COMMON.enableExperimentalFeatures.get()){
            stack.getOrCreateTag().putString(TAG_NAME, modifier.getRegistryName().toString());

            modifier.apply(stack);
        }
    }

    public static boolean hasModifier(ItemStack stack) {
        return stack.hasTag() && stack.getOrCreateTag().contains(TAG_NAME);
    }

    public static boolean hasSpecificModifier(ItemStack stack, ItemModifier modifier) {
        if (!hasModifier(stack))
            return false;
        RegistryEntry<ItemModifier> optionalModifier = getModifier(stack);
        return optionalModifier.isPresent() && optionalModifier.get().equals(modifier);
    }

    public static void removeModifier(ItemStack stack) {
        if (hasModifier(stack)) {
            stack.getOrCreateTag().remove(TAG_NAME);
        }
    }
}
