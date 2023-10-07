package com.robocraft999.creategoggles.forge.mixin;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.item.armortrim.TrimPatterns;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmithingTrimRecipe;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

import static com.robocraft999.creategoggles.CreateGoggles.LOGGER;

@Mixin(SmithingTrimRecipe.class)
public class SmithingTrimRecipeMixin {

    @Shadow
    private ResourceLocation id;
    @Shadow
    Ingredient template;
    @Shadow
    Ingredient base;
    @Shadow
    Ingredient addition;

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
        LOGGER.info(this.id + ": " + Arrays.toString(this.template.getItems()) + " =? " + arg.getItem(0) + "-> " + this.template.test(arg.getItem(0)));
        LOGGER.info(this.id + ": " + Arrays.toString(this.base.getItems()) + " =? " + arg.getItem(1) + "-> " + this.base.test(arg.getItem(1)));
        LOGGER.info(this.id + ": " + Arrays.toString(this.addition.getItems()) + " =? " + arg.getItem(2) + "-> " + this.addition.test(arg.getItem(2)));
    }
}
