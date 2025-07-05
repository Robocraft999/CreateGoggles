package com.robocraft999.creategoggles.registry;

import com.robocraft999.creategoggles.CreateGoggles;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimPattern;

public class CGTrimPatterns {
    public static final ResourceKey<TrimPattern> GOGGLE_PATTERN  = patternRegistryKey("goggle");
    public static final ResourceKey<TrimMaterial> GOGGLE_MATERIAL  = materialRegistryKey("goggle_material");

    private static ResourceKey<TrimPattern> patternRegistryKey(String string) {
        return ResourceKey.create(Registries.TRIM_PATTERN, CreateGoggles.asResource(string));
    }

    private static ResourceKey<TrimMaterial> materialRegistryKey(String string) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, CreateGoggles.asResource(string));
    }

    public static void register(){
        CreateGoggles.LOGGER.info(GOGGLE_PATTERN.toString());
        CreateGoggles.LOGGER.info(GOGGLE_MATERIAL.toString());
        CreateGoggles.LOGGER.info(Registries.TRIM_PATTERN.toString());
    }
}
