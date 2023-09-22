package com.robocraft999.creategoggles.registry;

import com.robocraft999.creategoggles.CreateGoggles;
import com.simibubi.create.AllItems;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimPattern;

import java.util.Map;

import static com.robocraft999.creategoggles.CreateGoggles.REGISTRATE;

public class CGTrimPatterns {
    public static final ResourceKey<TrimPattern> GOGGLE_PATTERN  = patternRegistryKey("goggle");
    public static final ResourceKey<TrimMaterial> GOGGLE_MATERIAL  = materialRegistryKey("goggle_material");

    private static final DeferredRegister<TrimPattern> TRIM_PATTERNS = DeferredRegister.create(CreateGoggles.MOD_ID, Registries.TRIM_PATTERN);
    private static final DeferredRegister<TrimMaterial> TRIM_MATERIALS = DeferredRegister.create(CreateGoggles.MOD_ID, Registries.TRIM_MATERIAL);

    private static ResourceKey<TrimPattern> patternRegistryKey(String string) {
        return ResourceKey.create(Registries.TRIM_PATTERN, new ResourceLocation(CreateGoggles.MOD_ID, string));
    }

    private static ResourceKey<TrimMaterial> materialRegistryKey(String string) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, new ResourceLocation(CreateGoggles.MOD_ID, string));
    }

    public static void register(){
        CreateGoggles.LOGGER.info(GOGGLE_PATTERN.toString());
        CreateGoggles.LOGGER.info(GOGGLE_MATERIAL.toString());
        CreateGoggles.LOGGER.info(Registries.TRIM_PATTERN.toString());
        //TRIM_PATTERNS.register();
    }

    public static void registerOnData() {
        REGISTRATE
                .object("goggle")
                .simple(Registries.TRIM_PATTERN, () ->
                        new TrimPattern(
                                CGTrimPatterns.GOGGLE_PATTERN.location(),
                                BuiltInRegistries.ITEM.wrapAsHolder(CGItems.GOGGLE_ARMOR_TRIM_SMITHING_TEMPLATE.get()),
                                Component.translatable(Util.makeDescriptionId("trim_pattern", CGTrimPatterns.GOGGLE_PATTERN.location()))
                        )
                );
        REGISTRATE
                .object("goggle_material")
                .simple(Registries.TRIM_MATERIAL, () ->
                        TrimMaterial.create(
                                "goggle_material",
                                AllItems.GOGGLES.get(),
                                0.8f,
                                Component.translatable(Util.makeDescriptionId("trim_material", CGTrimPatterns.GOGGLE_PATTERN.location())),
                                Map.of()
                        )
                );
        /*TRIM_PATTERNS.register("goggle", () -> new TrimPattern(
                GOGGLE_PATTERN.location(),
                BuiltInRegistries.ITEM.wrapAsHolder(CGItems.GOGGLE_ARMOR_TRIM_SMITHING_TEMPLATE.get()),
                Component.translatable(Util.makeDescriptionId("trim_pattern", CGTrimPatterns.GOGGLE_PATTERN.location()))
        ));
        TRIM_MATERIALS.register("goggle_material", () -> new TrimMaterial(
                "goggle_material",
                BuiltInRegistries.ITEM.wrapAsHolder(AllItems.GOGGLES.get()),
                0.8f,
                Map.of(),
                Component.translatable(Util.makeDescriptionId("trim_material", CGTrimPatterns.GOGGLE_PATTERN.location()))
        ));*/
    }
}
