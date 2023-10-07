package com.robocraft999.creategoggles.forge;

import com.robocraft999.creategoggles.CGConfig;
import com.robocraft999.creategoggles.CreateGoggles;
import com.robocraft999.creategoggles.forge.compat.curios.CompatCurios;
import com.robocraft999.creategoggles.forge.compat.mekanism.CompatMekanism;
import com.robocraft999.creategoggles.forge.data.RecipeDataProvider;
import com.robocraft999.creategoggles.forge.registry.CGModules;
import com.robocraft999.creategoggles.forge.registry.CPItems;
import com.robocraft999.creategoggles.registry.CGTrimPatterns;
import com.robocraft999.creategoggles.registry.ModCompat;
import com.simibubi.create.AllItems;
import com.tterrag.registrate.providers.ProviderType;
import dev.architectury.platform.forge.EventBuses;
import net.minecraft.Util;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Set;

//import top.theillusivec4.curios.api.SlotTypeMessage;

@Mod(CreateGoggles.MOD_ID)
public class CreateGogglesForge {
    public static final Logger logger = LogManager.getLogger(CreateGoggles.MOD_ID);

    public CreateGogglesForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(CreateGoggles.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        CreateGoggles.REGISTRATE.defaultCreativeTab(CreativeModeTabs.COMBAT);
        CreateGoggles.init();
        CreateGoggles.LOGGER.info("CreateGogglesForge init");

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        CreateGoggles.REGISTRATE.registerEventListeners(modEventBus);

        CPItems.register();

        if(ModCompat.MEKANISM.isLoaded())
            CGModules.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CGConfig.commonSpec);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CGConfig.clientSpec);

        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientRegistries);
        modEventBus.addListener(this::gatherData);

        ModCompat.MEKANISM.executeIfInstalled(() -> CompatMekanism::init);
        ModCompat.CURIOS.executeIfInstalled(() -> CompatCurios::init);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        logger.info("Create Goggles Setup");
    }


    private void clientRegistries(final FMLClientSetupEvent event) {
        logger.info("Create Goggles Client Setup");
    }

    private void gatherData(GatherDataEvent event){
        logger.info("gathering data");
        CGTrimPatterns.registerOnData();
        CreateGoggles.REGISTRATE.addDataGenerator(ProviderType.GENERIC_SERVER, provider -> provider.add(data -> {
            return new DatapackBuiltinEntriesProvider(
                    data.output(),
                    data.registries(),
                    new RegistrySetBuilder()
                            .add(Registries.TRIM_MATERIAL, bootstrap -> bootstrap.register(
                                    CGTrimPatterns.GOGGLE_MATERIAL,
                                    TrimMaterial.create(
                                            "goggle_material",
                                            AllItems.GOGGLES.get(),
                                            0.8f,
                                            Component.translatable(Util.makeDescriptionId("trim_material", CGTrimPatterns.GOGGLE_MATERIAL.location())),
                                            Map.of()
                                    )
                            )),
                    Set.of(CreateGoggles.MOD_ID)
            );
                }));
        event.getGenerator().addProvider(true, new RecipeDataProvider(event.getGenerator()));
    }

    @Mod.EventBusSubscriber(modid = CreateGoggles.MOD_ID, bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SuppressWarnings("unused")
        @SubscribeEvent
        public static void enqueueIMC(final InterModEnqueueEvent event) {
            if(ModCompat.CURIOS.isLoaded()) {
                /*if(CGConfig.COMMON.useCustomCurioBacktankSlot.get()){
                    InterModComms.sendTo(CreateGoggles.MOD_ID, "curios", SlotTypeMessage.REGISTER_TYPE,
                            () -> new SlotTypeMessage.Builder("creategoggles.backtank_slot")
                                    .size(1)
                                    .icon(new ResourceLocation(CreateGoggles.MOD_ID, "item/backtank_slot_icon"))
                                    .build());
                }*/

            }
            if(ModCompat.MEKANISM.isLoaded()){
                //MekanismIMC.addMekaSuitHelmetModules(CGModules.GOGGLE_MODULE.get());
            }
        }
    }
}