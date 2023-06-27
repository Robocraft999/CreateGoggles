package com.robocraft999.creategoggles.item.goggle;

import com.robocraft999.creategoggles.CreateGoggles;
import com.robocraft999.creategoggles.item.modifier.ItemModifierManager;
import com.robocraft999.creategoggles.registry.CGItemModifiers;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface IGoggleHelmet {
    String GOGGLE_TAG = "goggle";

    static boolean isGoggleHelmet(LivingEntity entity) {
        ItemStack headSlot = entity.getItemBySlot(EquipmentSlot.HEAD);

        List<TagKey<Item>> tags = headSlot.getTags().filter(tag -> tag.location().getNamespace().equals(CreateGoggles.MOD_ID)).toList();
        for(TagKey<Item> tag : tags){
            if(tag.location().getPath().equals(GOGGLE_TAG)){
                return true;
            }
        }

        if (ItemModifierManager.hasSpecificModifier(headSlot, CGItemModifiers.GOGGLE_MODIFIER.get())){
            return true;
        }

        return false;
    }
}
