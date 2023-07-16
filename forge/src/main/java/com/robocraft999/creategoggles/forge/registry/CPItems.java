package com.robocraft999.creategoggles.forge.registry;

import com.robocraft999.creategoggles.registry.ModCompat;
import com.tterrag.registrate.util.entry.ItemEntry;
import mekanism.api.MekanismAPI;
import mekanism.api.providers.IModuleDataProvider;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import static com.robocraft999.creategoggles.CreateGoggles.REGISTRATE;

public class CPItems {

	static {
		REGISTRATE.creativeModeTab(() -> CreativeModeTab.TAB_COMBAT);
	}

    public static final ItemEntry<Item>
			GOGGLE_UNIT = registerGoggleModule();

	public static void register() {
	}

	private static ItemEntry<Item> registerGoggleModule(){
		if (ModCompat.MEKANISM.isLoaded()) {
			return REGISTRATE
					.item("module_goggle_unit", p -> MekanismAPI.getModuleHelper().createModuleItem((IModuleDataProvider) CGModules.GOGGLE_MODULE::get, p.tab(CreativeModeTab.TAB_SEARCH)))
					.lang("Goggle Unit")
					.register();
		}
		return null;
	}
}
