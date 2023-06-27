package com.robocraft999.creategoggles.item.goggle;

import com.simibubi.create.content.equipment.armor.BaseArmorItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.level.block.DispenserBlock;

public class GoggleHelmet extends BaseArmorItem implements IGoggleHelmet{
    private static final EquipmentSlot SLOT = EquipmentSlot.HEAD;
    public GoggleHelmet(ArmorMaterial armorMaterial, Properties properties, ResourceLocation textureLoc) {
        super(armorMaterial, SLOT, properties, textureLoc);
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
    }
}
