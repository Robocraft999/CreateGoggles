package com.robocraft999.creategoggles.neoforge;

import com.robocraft999.creategoggles.CGConfig;
import com.robocraft999.creategoggles.CreateGoggles;
import com.robocraft999.creategoggles.neoforge.compat.mekanism.CompatMekanism;
import com.robocraft999.creategoggles.neoforge.data.RecipeDataProvider;
import com.robocraft999.creategoggles.neoforge.registry.CGItemsNeoforge;
import com.robocraft999.creategoggles.neoforge.registry.CGModules;
import com.robocraft999.creategoggles.registry.ModCompat;
import mekanism.api.MekanismIMC;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(CreateGoggles.MOD_ID)
public class CreateGogglesNeoforge {
    public static final Logger logger = LogManager.getLogger(CreateGoggles.MOD_ID);

    public CreateGogglesNeoforge(ModContainer container) {
        // Submit our event bus to let architectury register our content on the right time
        //EventBusesHooks.registerModEventBus(CreateGoggles.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        CreateGoggles.REGISTRATE.defaultCreativeTab(CreativeModeTabs.COMBAT);
        CreateGoggles.init();
        CreateGoggles.LOGGER.info("CreateGogglesForge init");

        IEventBus modEventBus = container.getEventBus();
        CreateGoggles.REGISTRATE.registerEventListeners(modEventBus);

        if(ModCompat.MEKANISM.isLoaded()){
            CGItemsNeoforge.register();
            CGModules.register(modEventBus);
        }

        container.registerConfig(ModConfig.Type.COMMON, CGConfig.commonSpec);
        container.registerConfig(ModConfig.Type.CLIENT, CGConfig.clientSpec);

        modEventBus.addListener(this::gatherData);

        ModCompat.MEKANISM.executeIfInstalled(() -> CompatMekanism::init);
        //ModCompat.CURIOS.executeIfInstalled(() -> CompatCurios::init);
    }

    private void gatherData(GatherDataEvent event) {
        logger.info("gathering data");
        event.getGenerator().addProvider(event.includeServer(), new RecipeDataProvider(event.getGenerator().getPackOutput(), event.getLookupProvider()));
    }

    @EventBusSubscriber(modid = CreateGoggles.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SuppressWarnings("unused")
        @SubscribeEvent
        public static void enqueueIMC(final InterModEnqueueEvent event) {
            if(ModCompat.MEKANISM.isLoaded()){
                MekanismIMC.addMekaSuitHelmetModules(CGModules.GOGGLE_MODULE);
            }
        }
    }
}
