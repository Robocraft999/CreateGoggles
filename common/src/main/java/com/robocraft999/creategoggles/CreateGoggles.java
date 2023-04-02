package com.robocraft999.creategoggles;

import com.robocraft999.creategoggles.data.CGLang;
import com.robocraft999.creategoggles.registry.CGBlocks;
import com.robocraft999.creategoggles.registry.CGItems;
import com.robocraft999.creategoggles.registry.CGRecipeTypes;
import com.robocraft999.creategoggles.registry.CGTileEntities;
import com.simibubi.create.foundation.data.CreateRegistrate;
import dev.architectury.platform.Platform;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateGoggles {
    public static final String MOD_ID = "creategoggles";
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(CreateGoggles.MOD_ID);
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        LOGGER.info("CreateGoggles init");
        CGBlocks.register();
        CGTileEntities.register();
        CGItems.register();
        CGRecipeTypes.register();
        CGLang.register();

        if(Platform.isFabric()){
            ModLoadingContext.registerConfig(MOD_ID, ModConfig.Type.CLIENT, CPConfig.clientSpec);
            ModLoadingContext.registerConfig(MOD_ID, ModConfig.Type.COMMON, CPConfig.commonSpec);

            ClientEvents.register();
        }
    }
}