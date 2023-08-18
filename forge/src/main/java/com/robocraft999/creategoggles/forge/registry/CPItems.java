package com.robocraft999.creategoggles.forge.registry;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

public class CPItems {

	static {

	}

    public static final ItemEntry<Item>
			GOGGLE_UNIT = registerGoggleModule();

	public static void register() {
	}

	private static ItemEntry<Item> registerGoggleModule(){
		/*if (ModCompat.MEKANISM.isLoaded()) {
			return REGISTRATE
					.item("module_goggle_unit", p -> MekanismAPI.getModuleHelper().createModuleItem((IModuleDataProvider) CGModules.GOGGLE_MODULE::get, p.tab(CreativeModeTab.TAB_SEARCH)))
					.lang("Goggle Unit")
					.register();
		}*/
		return null;
	}
}
