package com.robocraft999.creategoggles.item.goggle;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeableLeatherItem;

public class DyableGoggleHelmet extends GoggleHelmet implements DyeableLeatherItem {
    public DyableGoggleHelmet(ArmorMaterial armorMaterial, Properties properties, ResourceLocation textureLoc) {
        super(armorMaterial, properties, textureLoc);
    }
}
