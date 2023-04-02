package com.robocraft999.creategoggles.forge.item.goggle;

import com.robocraft999.creategoggles.item.goggle.GoggleArmorLayer;
import com.robocraft999.creategoggles.registry.ModCompat;
import com.simibubi.create.AllItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.concurrent.atomic.AtomicBoolean;

public class GoggleArmorLayerForge {

    public static void registerOnAll(EntityRenderDispatcher renderManager) {
        for (EntityRenderer<? extends Player> renderer : renderManager.getSkinMap().values())
            registerOn(renderer);
        for (EntityRenderer<?> renderer : renderManager.renderers.values())
            registerOn(renderer);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void registerOn(EntityRenderer<?> entityRenderer) {
        if (!(entityRenderer instanceof LivingEntityRenderer<?, ?> livingRenderer))
            return;
        if (!(livingRenderer.getModel() instanceof HumanoidModel))
            return;
        GoggleArmorLayer<?, ?> layer = new GoggleArmorLayer<>(livingRenderer);
        livingRenderer.addLayer((GoggleArmorLayer) layer);
    }

    private static boolean isWearingGoggleInCurio(LivingEntity entity){
        AtomicBoolean hasGoggles = new AtomicBoolean(false);
        if(ModCompat.CURIOS.isLoaded()){
            entity.getCapability(CuriosCapability.INVENTORY).ifPresent(handler -> {
                ICurioStacksHandler stacksHandler = handler.getCurios().get("head");
                if(stacksHandler != null) hasGoggles.set(stacksHandler.getStacks().getStackInSlot(0).getItem() == AllItems.GOGGLES.get());
            });
        }
        return hasGoggles.get();
    }
}
