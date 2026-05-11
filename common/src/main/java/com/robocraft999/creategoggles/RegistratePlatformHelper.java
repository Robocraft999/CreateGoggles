package com.robocraft999.creategoggles;

import com.robocraft999.creategoggles.item.modifier.ItemModifier;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.foundation.data.CreateBlockEntityBuilder;
import com.simibubi.create.foundation.data.CreateRegistrate;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class RegistratePlatformHelper {
    @ExpectPlatform
    public static ResourceKey<Registry<ItemModifier>> itemModifierRegistryKey() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <BE extends KineticBlockEntity> CreateBlockEntityBuilder<BE, CreateRegistrate> visualUnfucker(CreateBlockEntityBuilder<BE, CreateRegistrate> builder) { throw new AssertionError(); }
}
