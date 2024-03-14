package com.robocraft999.creategoggles.fabric;

import com.robocraft999.creategoggles.CreateGoggles;
import net.fabricmc.api.ClientModInitializer;

public class CreateGogglesFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CreateGoggles.LOGGER.info("CreateGogglesFabricClient init");
        ClientEvents.register();
    }
}
