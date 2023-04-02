package com.robocraft999.creategoggles.fabric;

import com.robocraft999.creategoggles.CreateGoggles;
import net.fabricmc.api.ModInitializer;

public class CreateGogglesFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CreateGoggles.init();
        CreateGoggles.LOGGER.info("CreateGogglesFabric init");

        CreateGoggles.REGISTRATE.register();
    }
}