package com.robocraft999.creategoggles.forge.mixin;

import net.minecraft.core.RegistryAccess;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.item.armortrim.TrimPatterns;
import net.minecraft.world.item.crafting.SmithingTrimRecipe;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.robocraft999.creategoggles.CreateGoggles.LOGGER;

@Mixin(SmithingTrimRecipe.class)
public class SmithingTrimRecipeMixin {

    @Inject(
            method = "assemble",
            at = @At("HEAD")
    )
    public void onAssemble(Container container, RegistryAccess registryAccess, CallbackInfoReturnable<ItemStack> cir){
        LOGGER.info(String.valueOf(TrimMaterials.getFromIngredient(registryAccess, container.getItem(2))));
        LOGGER.info(String.valueOf(TrimPatterns.getFromTemplate(registryAccess, container.getItem(0))));
        LOGGER.info(String.valueOf(ArmorTrim.getTrim(registryAccess, container.getItem(1))));
    }

    @Inject(
            method = "matches",
            at = @At("RETURN")
    )
    public void onMatches(Container arg, Level arg2, CallbackInfoReturnable<Boolean> cir){
        LOGGER.info(String.valueOf(cir.getReturnValue()));
    }
}
