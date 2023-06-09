package com.robocraft999.creategoggles;

import com.robocraft999.creategoggles.item.goggle.GoggleArmorLayer;
import com.simibubi.create.Create;
import com.simibubi.create.content.equipment.armor.AllArmorMaterials;
import dev.architectury.platform.Platform;
import io.github.fabricators_of_create.porting_lib.util.ArmorTextureRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback.RegistrationHelper;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

public class ClientEvents {

    private static final String ITEM_PREFIX = "item." + CreateGoggles.MOD_ID;
    private static final String BLOCK_PREFIX = "block." + CreateGoggles.MOD_ID;

    public static void addEntityRenderLayers(EntityType<? extends LivingEntity> entityType, LivingEntityRenderer<?,?> entityRenderer,
                                             RegistrationHelper registrationHelper, EntityRendererProvider.Context context) {
        GoggleArmorLayer.registerOn(entityRenderer, registrationHelper);
    }

    public static void register(){
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register(ClientEvents::addEntityRenderLayers);

        if(Platform.isFabric())
            ArmorTextureRegistry.register(AllArmorMaterials.COPPER, Create.asResource("copper_diving"));
    }
}
