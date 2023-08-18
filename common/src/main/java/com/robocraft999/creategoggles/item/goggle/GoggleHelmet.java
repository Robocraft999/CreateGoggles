package com.robocraft999.creategoggles.item.goggle;

import com.simibubi.create.content.equipment.armor.BaseArmorItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.level.block.DispenserBlock;

public class GoggleHelmet extends BaseArmorItem implements IGoggleHelmet{
    private static final ArmorItem.Type TYPE = Type.HELMET;
    public GoggleHelmet(ArmorMaterial armorMaterial, Properties properties, ResourceLocation textureLoc) {
        super(armorMaterial, TYPE, properties, textureLoc);
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
    }
}
