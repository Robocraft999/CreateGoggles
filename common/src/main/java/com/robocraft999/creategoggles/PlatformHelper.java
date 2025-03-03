package com.robocraft999.creategoggles;

import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class PlatformHelper {
    @ExpectPlatform
    public static <B extends Block, P> NonNullUnaryOperator<BlockBuilder<B, P>> backtank(@NotNull Supplier<ItemLike> drop){
        throw new AssertionError();
    }
}
