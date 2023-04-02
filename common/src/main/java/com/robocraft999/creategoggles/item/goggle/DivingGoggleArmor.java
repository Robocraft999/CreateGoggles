package com.robocraft999.creategoggles.item.goggle;

import com.simibubi.create.Create;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;

public class DivingGoggleArmor extends GoggleArmor {
	public static final ResourceLocation TEXTURE = Create.asResource("textures/models/armor/copper.png");
	protected static final String TEXTURE_STRING = TEXTURE.toString();
	public DivingGoggleArmor(ArmorMaterial material, Properties properties) {
		super(material, properties);
	}
}
