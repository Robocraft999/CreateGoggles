package com.robocraft999.creategoggles.neoforge.registry;

import com.robocraft999.creategoggles.CreateGoggles;
import com.robocraft999.creategoggles.neoforge.item.goggle.module.GoggleModule;
import mekanism.common.registration.impl.ModuleDeferredRegister;
import mekanism.common.registration.impl.ModuleRegistryObject;
import net.neoforged.bus.api.IEventBus;

public class CGModules {
    public static final ModuleDeferredRegister MODULES = new ModuleDeferredRegister(CreateGoggles.MOD_ID);
    public static final ModuleRegistryObject<GoggleModule> GOGGLE_MODULE =
            MODULES.register("goggle_unit", GoggleModule::new, CGItemsNeoforge.GOGGLE_UNIT::getDelegate, builder -> builder.maxStackSize(1));

    public static void register(IEventBus bus) {
        MODULES.register(bus);
    }
}
