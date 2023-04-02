package com.robocraft999.creategoggles.forge.item.goggle;

import com.robocraft999.creategoggles.item.goggle.DivingGoggleArmor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class DivingGoggleArmorWrapper extends DivingGoggleArmor {
    public DivingGoggleArmorWrapper(ArmorMaterial material, Properties properties) {
        super(material, properties);
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return DivingGoggleArmor.TEXTURE_STRING;
    }
}
