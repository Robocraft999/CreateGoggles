package com.robocraft999.creategoggles.fabric;

import com.robocraft999.creategoggles.CGConfig;
import com.robocraft999.creategoggles.CreateGoggles;
import net.fabricmc.api.ModInitializer;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class CreateGogglesFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CreateGoggles.init();
        CreateGoggles.LOGGER.info("CreateGogglesFabric init");

        CreateGoggles.REGISTRATE.register();

        ModLoadingContext.registerConfig(CreateGoggles.MOD_ID, ModConfig.Type.CLIENT, CGConfig.clientSpec);
        ModLoadingContext.registerConfig(CreateGoggles.MOD_ID, ModConfig.Type.COMMON, CGConfig.commonSpec);
    }
}