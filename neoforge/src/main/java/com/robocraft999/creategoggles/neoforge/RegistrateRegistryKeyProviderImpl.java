package com.robocraft999.creategoggles.neoforge;

import com.robocraft999.creategoggles.CreateGoggles;
import com.robocraft999.creategoggles.item.modifier.ItemModifier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.RegistryBuilder;

public class RegistrateRegistryKeyProviderImpl {
    public static ResourceKey<Registry<ItemModifier>> itemModifierRegistryKey() {
        return CreateGoggles.REGISTRATE.makeRegistry("item_modifier", RegistryBuilder::new);
    }
}
