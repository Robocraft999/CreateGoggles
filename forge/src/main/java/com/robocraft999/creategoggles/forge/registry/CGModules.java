package com.robocraft999.creategoggles.forge.registry;

import com.robocraft999.creategoggles.CreateGoggles;
import com.robocraft999.creategoggles.forge.item.goggle.module.GoggleModule;
import mekanism.common.registration.impl.ModuleDeferredRegister;
import mekanism.common.registration.impl.ModuleRegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class CGModules {

    public static final ModuleDeferredRegister MODULES = new ModuleDeferredRegister(CreateGoggles.MOD_ID);
    public static final ModuleRegistryObject<GoggleModule> GOGGLE_MODULE =
            MODULES.register("goggle_unit", GoggleModule::new, () -> CGItemsForge.GOGGLE_UNIT.asItem(), builder -> builder.maxStackSize(1));

    public static void register(IEventBus bus) {
        MODULES.register(bus);
    }
}
