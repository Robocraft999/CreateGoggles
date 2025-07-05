package com.robocraft999.creategoggles.item.goggle;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class DyableGoggleHelmet extends GoggleHelmet{
    public DyableGoggleHelmet(Holder<ArmorMaterial> armorMaterial, Properties properties, ResourceLocation textureLoc) {
        super(armorMaterial, properties, textureLoc);
    }

    @Override
    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        //return null so ClientHooks uses layer.texture()
        return null;
    }
}
