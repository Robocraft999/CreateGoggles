package com.robocraft999.creategoggles.neoforge.registry;

import com.tterrag.registrate.util.entry.ItemEntry;
import mekanism.common.content.gear.ModuleHelper;
import net.minecraft.world.item.Item;

import static com.robocraft999.creategoggles.CreateGoggles.REGISTRATE;

public class CGItemsNeoforge {
    public static final ItemEntry<? extends Item> GOGGLE_UNIT = REGISTRATE
            .item("module_goggle_unit", p -> ModuleHelper.get().createModuleItem(CGModules.GOGGLE_MODULE::getDelegate, p))
            .lang("Goggle Unit")
            .register();

    public static void register() {
    }
}
