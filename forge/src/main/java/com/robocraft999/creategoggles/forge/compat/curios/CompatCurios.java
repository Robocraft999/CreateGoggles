package com.robocraft999.creategoggles.forge.compat.curios;

import com.robocraft999.creategoggles.compat.CuriosCompatDummy;
import com.robocraft999.creategoggles.registry.ModCompat;
import net.minecraft.world.entity.LivingEntity;

import java.util.concurrent.atomic.AtomicBoolean;

public class CompatCurios {

    public static void init(){
        CuriosCompatDummy.predicate = CompatCurios::isWearingGoggleInCurio;
    }
    private static boolean isWearingGoggleInCurio(LivingEntity entity){
        AtomicBoolean hasGoggles = new AtomicBoolean(false);
        if(ModCompat.CURIOS.isLoaded()){
            /*entity.getCapability(CuriosCapability.INVENTORY).ifPresent(handler -> {
                ICurioStacksHandler stacksHandler = handler.getCurios().get("head");
                if(stacksHandler != null) hasGoggles.set(stacksHandler.getStacks().getStackInSlot(0).getItem() == AllItems.GOGGLES.get());
            });*/
        }
        return hasGoggles.get();
    }
}
