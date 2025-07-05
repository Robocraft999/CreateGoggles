package com.robocraft999.creategoggles.item.goggle;

import com.robocraft999.creategoggles.CGConfig;
import com.robocraft999.creategoggles.registry.CGTags;
import com.robocraft999.creategoggles.registry.CGTrimPatterns;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface IGoggleHelmet {

    static boolean isGoggleHelmet(LivingEntity entity) {
        ItemStack headSlot = entity.getItemBySlot(EquipmentSlot.HEAD);
        if (headSlot.is(CGTags.Items.GOGGLE)) return true;

        /*if (ItemModifierManager.hasSpecificModifier(headSlot, CGItemModifiers.GOGGLE_MODIFIER.get())){
            return true;
        }*/

        if (CGConfig.Common.enableGoggles.get())
            return true;

        if (entity instanceof Player player){
            if (player.isCreative() && CGConfig.CLIENT.enableCreativeModeGoggles.get()){
                return true;
            }
        }

        if(Minecraft.getInstance().level != null){
            var optionalTrim = headSlot.get(DataComponents.TRIM);
            if (optionalTrim != null &&
                    optionalTrim.pattern().is(CGTrimPatterns.GOGGLE_PATTERN) &&
                    optionalTrim.material().is(CGTrimPatterns.GOGGLE_MATERIAL)){
                return true;
            }
        }

        return false;
    }
}
