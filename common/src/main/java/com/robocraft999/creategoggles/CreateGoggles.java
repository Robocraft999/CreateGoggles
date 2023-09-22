package com.robocraft999.creategoggles;

import com.robocraft999.creategoggles.data.CGLang;
import com.robocraft999.creategoggles.registry.*;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.TooltipHelper.Palette;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateGoggles {
    public static final String MOD_ID = "creategoggles";
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(CreateGoggles.MOD_ID);
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    static {
        REGISTRATE.setTooltipModifierFactory(
                item -> new ItemDescription.Modifier(item, Palette.STANDARD_CREATE)
        );
    }

    public static void init() {
        LOGGER.info("CreateGoggles init");
        CGBlocks.register();
        CGTileEntities.register();
        CGItems.register();
        CGTrimPatterns.register();
        CGRecipeTypes.register();
        CGItemModifiers.register();
        CGLang.register();
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}