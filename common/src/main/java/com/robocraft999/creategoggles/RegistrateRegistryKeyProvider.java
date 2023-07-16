package com.robocraft999.creategoggles;

import com.robocraft999.creategoggles.item.modifier.ItemModifier;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class RegistrateRegistryKeyProvider {
    @ExpectPlatform
    public static ResourceKey<Registry<ItemModifier>> itemModifierRegistryKey() {
        throw new AssertionError();
    }
}
