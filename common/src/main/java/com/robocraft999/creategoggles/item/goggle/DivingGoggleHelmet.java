package com.robocraft999.creategoggles.item.goggle;

import com.simibubi.create.content.equipment.armor.DivingHelmetItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.level.block.DispenserBlock;

public class DivingGoggleHelmet extends DivingHelmetItem implements IGoggleHelmet{
    public DivingGoggleHelmet(ArmorMaterial material, Properties properties, ResourceLocation textureLoc) {
        super(material, properties, textureLoc);
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
    }
}
