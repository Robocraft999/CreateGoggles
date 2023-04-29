package com.robocraft999.creategoggles.forge.registry;

import com.robocraft999.creategoggles.CreateGoggles;
import com.robocraft999.creategoggles.forge.item.goggle.module.GoggleModule;
import mekanism.api.MekanismAPI;
import mekanism.api.gear.ModuleData;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CGModules {

    public static final DeferredRegister<ModuleData<?>> MODULES = DeferredRegister.create(MekanismAPI.moduleRegistryName(), CreateGoggles.MOD_ID);

    public static final RegistryObject<ModuleData<?>> GOGGLE_MODULE = MODULES.register("goggle_unit",
            () -> new ModuleData<>(ModuleData.ModuleDataBuilder.custom(GoggleModule::new, CPItems.GOGGLE_UNIT::get))
    );


    public static void register(IEventBus bus) {
        MODULES.register(bus);
    }
}
