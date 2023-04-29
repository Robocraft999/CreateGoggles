package com.robocraft999.creategoggles.forge;

import com.robocraft999.creategoggles.CPConfig;
import com.robocraft999.creategoggles.CreateGoggles;
import com.robocraft999.creategoggles.forge.compat.mekanism.CompatMekanism;
import com.robocraft999.creategoggles.forge.data.RecipeDataProvider;
import com.robocraft999.creategoggles.forge.registry.CGModules;
import com.robocraft999.creategoggles.forge.registry.CPItems;
import com.robocraft999.creategoggles.registry.ModCompat;
import dev.architectury.platform.forge.EventBuses;
import mekanism.api.MekanismIMC;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.SlotTypeMessage;

@Mod(CreateGoggles.MOD_ID)
public class CreateGogglesForge {
    public static final Logger logger = LogManager.getLogger(CreateGoggles.MOD_ID);

    public CreateGogglesForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(CreateGoggles.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        CreateGoggles.init();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        CreateGoggles.REGISTRATE.registerEventListeners(modEventBus);

        CPItems.register();

        if(ModCompat.MEKANISM.isLoaded())
            CGModules.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CPConfig.commonSpec);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CPConfig.clientSpec);

        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientRegistries);
        modEventBus.addListener(this::gatherData);

        ModCompat.MEKANISM.executeIfInstalled(() -> CompatMekanism::init);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        logger.info("Create Plus Setup");
    }


    private void clientRegistries(final FMLClientSetupEvent event) {
        logger.info("Create Plus Client Setup");
    }

    private void gatherData(GatherDataEvent event){
        logger.info("gathering data");
        event.getGenerator().addProvider(true, new RecipeDataProvider(event.getGenerator()));
    }

    @Mod.EventBusSubscriber(modid = CreateGoggles.MOD_ID, bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SuppressWarnings("unused")
        @SubscribeEvent
        public static void enqueueIMC(final InterModEnqueueEvent event) {
            if(ModCompat.CURIOS.isLoaded()) {
                if(CPConfig.COMMON.useCustomCurioBacktankSlot.get()){
                    InterModComms.sendTo(CreateGoggles.MOD_ID, "curios", SlotTypeMessage.REGISTER_TYPE,
                            () -> new SlotTypeMessage.Builder("createplus.backtank_slot")
                                    .size(1)
                                    .icon(new ResourceLocation(CreateGoggles.MOD_ID, "item/backtank_slot_icon"))
                                    .build());
                }

            }
            if(ModCompat.MEKANISM.isLoaded()){
                MekanismIMC.addMekaSuitHelmetModules(CGModules.GOGGLE_MODULE.get());
            }
        }
    }
}