package com.robocraft999.creategoggles.forge.registry;

import com.robocraft999.creategoggles.forge.CreateGogglesForge;
import com.robocraft999.creategoggles.forge.item.goggle.DivingGoggleArmorWrapper;
import com.robocraft999.creategoggles.item.goggle.GoggleArmor;
import com.robocraft999.creategoggles.registry.ModCompat;
import com.simibubi.create.content.AllSections;
import com.simibubi.create.content.curiosities.armor.AllArmorMaterials;
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

	static {
		REGISTRATE.startSection(AllSections.KINETICS);
	}

	//-------------------Goggle Helmets-------------------
	public static final ItemEntry<? extends GoggleArmor>
			DIVING_GOGGLE_HELMET = REGISTRATE
					.item("goggle_diving_helmet", p -> new DivingGoggleArmorWrapper(AllArmorMaterials.COPPER, p))
					.register();

    public static final ItemEntry<Item>
			GOGGLE_UNIT = registerGoggleModule();

	public static void register() {
		CreateGogglesForge.logger.info("Registering Items");
	}

	private static ItemEntry<Item> registerGoggleModule(){
		if (ModCompat.MEKANISM.isLoaded()) {
			return REGISTRATE
					.item("module_goggle_unit", p -> MekanismAPI.getModuleHelper().createModuleItem((IModuleDataProvider) CPModules.GOGGLE_MODULE::get, p.tab(CreativeModeTab.TAB_SEARCH)))
					.lang("Goggle Unit")
					.register();
		}
		return null;
	}
}
