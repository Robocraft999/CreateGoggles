package com.robocraft999.creategoggles.forge.compat.mekanism;

import com.robocraft999.creategoggles.forge.registry.CGModules;
import com.simibubi.create.content.equipment.goggles.GogglesItem;
import mekanism.api.MekanismAPI;
import net.minecraft.world.entity.EquipmentSlot;

import static com.robocraft999.creategoggles.CreateGoggles.REGISTRATE;

public class CompatMekanism {


    public static void init(){
        REGISTRATE.addRawLang("description.creategoggles.goggle_unit", "Grants advanced information about create contraptions");

        GogglesItem.addIsWearingPredicate((player) -> MekanismAPI.getModuleHelper().isEnabled(
                player.getItemBySlot(EquipmentSlot.HEAD),
                CGModules.GOGGLE_MODULE.get()
        ));
    }
}
