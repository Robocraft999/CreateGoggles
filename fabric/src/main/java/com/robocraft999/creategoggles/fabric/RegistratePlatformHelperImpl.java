package com.robocraft999.creategoggles.fabric;

import com.robocraft999.creategoggles.CreateGoggles;
import com.robocraft999.creategoggles.item.modifier.ItemModifier;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.foundation.data.CreateBlockEntityBuilder;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class RegistratePlatformHelperImpl {
    public static ResourceKey<Registry<ItemModifier>> itemModifierRegistryKey() {
        return CreateGoggles.REGISTRATE.makeRegistry("item_modifier");
    }

    public static <BE extends KineticBlockEntity> CreateBlockEntityBuilder<BE, CreateRegistrate> visualUnfucker(CreateBlockEntityBuilder<BE, CreateRegistrate> builder) {
        return builder.visual(() -> SingleAxisRotatingVisual::backtank);
    }
}
