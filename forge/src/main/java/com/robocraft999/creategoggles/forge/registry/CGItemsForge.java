package com.robocraft999.creategoggles.forge.registry;

import com.tterrag.registrate.util.entry.ItemEntry;
import mekanism.common.content.gear.ModuleHelper;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import static com.robocraft999.creategoggles.CreateGoggles.REGISTRATE;

public class CGItemsForge {
    public static final ItemEntry<? extends Item> GOGGLE_UNIT = REGISTRATE
            .item("module_goggle_unit", p -> ModuleHelper.get().createModuleItem(CGModules.GOGGLE_MODULE, p))
            .lang("Goggle Unit")
            .tab(CreativeModeTabs.SEARCH)
            .register();

    public static void register() {
    }
}
