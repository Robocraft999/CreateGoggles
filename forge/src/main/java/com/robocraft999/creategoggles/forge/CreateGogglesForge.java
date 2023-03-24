package com.robocraft999.creategoggles.forge;

import dev.architectury.platform.forge.EventBuses;
import com.robocraft999.creategoggles.CreateGoggles;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CreateGoggles.MOD_ID)
public class CreateGogglesForge {
    public CreateGogglesForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(CreateGoggles.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        CreateGoggles.init();
    }
}