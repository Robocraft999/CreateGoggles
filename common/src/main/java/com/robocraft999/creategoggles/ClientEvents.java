package com.robocraft999.creategoggles;

import com.robocraft999.creategoggles.item.goggle.GoggleArmorLayer;
import com.robocraft999.creategoggles.item.modifier.ItemModifierManager;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback.RegistrationHelper;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class ClientEvents {
    public static void addEntityRenderLayers(EntityType<? extends LivingEntity> entityType, LivingEntityRenderer<?,?> entityRenderer,
                                             RegistrationHelper registrationHelper, EntityRendererProvider.Context context) {
        GoggleArmorLayer.registerOn(entityRenderer, registrationHelper);
    }
    private static void onTooltip(ItemStack stack, TooltipFlag tooltipFlag, List<Component> components) {
        if (ItemModifierManager.hasModifier(stack)) {
            components.add(1, ItemModifierManager.getModifier(stack).getHintComponent());
            if(stack.isEnchanted()){
                components.add(2, Component.nullToEmpty(""));
            }
        }
    }

    public static void register(){
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register(ClientEvents::addEntityRenderLayers);
        ItemTooltipCallback.EVENT.register(ClientEvents::onTooltip);
        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> {
            out.accept(new ModelResourceLocation(CreateGoggles.MOD_ID + ":goggle#inventory"));
        });
    }


}
