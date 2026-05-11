package com.robocraft999.creategoggles.forge;

import com.robocraft999.creategoggles.CreateGoggles;
import com.robocraft999.creategoggles.item.modifier.ItemModifier;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.foundation.data.CreateBlockEntityBuilder;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.registries.RegistryBuilder;

public class RegistratePlatformHelperImpl {
    public static ResourceKey<Registry<ItemModifier>> itemModifierRegistryKey() {
        return CreateGoggles.REGISTRATE.makeRegistry("item_modifier", RegistryBuilder::new);
    }

    public static <BE extends KineticBlockEntity> CreateBlockEntityBuilder<BE, CreateRegistrate> visualUnfucker(CreateBlockEntityBuilder<BE, CreateRegistrate> builder) {
        return builder.visual(() -> SingleAxisRotatingVisual::backtank);
    }
}
