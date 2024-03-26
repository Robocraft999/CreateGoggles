package com.robocraft999.creategoggles.fabric;

import com.robocraft999.creategoggles.CGConfig;
import com.robocraft999.creategoggles.CreateGoggles;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.fml.config.ModConfig;

public class CreateGogglesFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CreateGoggles.REGISTRATE.defaultCreativeTab(CreativeModeTabs.COMBAT);
        CreateGoggles.init();
        CreateGoggles.LOGGER.info("CreateGogglesFabric init");

        CreateGoggles.REGISTRATE.register();

        ForgeConfigRegistry.INSTANCE.register(CreateGoggles.MOD_ID, ModConfig.Type.CLIENT, CGConfig.clientSpec);
        ForgeConfigRegistry.INSTANCE.register(CreateGoggles.MOD_ID, ModConfig.Type.COMMON, CGConfig.commonSpec);
    }
}