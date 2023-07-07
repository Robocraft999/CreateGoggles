package com.robocraft999.creategoggles.item.goggle;

import com.robocraft999.creategoggles.item.modifier.ItemModifierManager;
import com.robocraft999.creategoggles.registry.CGItemModifiers;
import com.robocraft999.creategoggles.registry.CGTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface IGoggleHelmet {

    static boolean isGoggleHelmet(LivingEntity entity) {
        ItemStack headSlot = entity.getItemBySlot(EquipmentSlot.HEAD);
        if (headSlot.is(CGTags.Items.GOGGLE)) return true;

        if (ItemModifierManager.hasSpecificModifier(headSlot, CGItemModifiers.GOGGLE_MODIFIER.get())){
            return true;
        }

        return false;
    }
}
