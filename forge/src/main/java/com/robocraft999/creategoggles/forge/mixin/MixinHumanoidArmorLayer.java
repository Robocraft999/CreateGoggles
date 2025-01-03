package com.robocraft999.creategoggles.forge.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.robocraft999.creategoggles.registry.CGTrimPatterns;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.armortrim.ArmorTrim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidArmorLayer.class)
public abstract class MixinHumanoidArmorLayer {

    @Inject(
            method = "renderTrim*",
            at = @At("HEAD"),
            cancellable = true
    )
    public void onRenderArmorTrim(ArmorMaterial arg, PoseStack arg2, MultiBufferSource arg3, int i, ArmorTrim arg4, Model arg5, boolean bl, CallbackInfo ci){
        if (arg4.material().is(CGTrimPatterns.GOGGLE_MATERIAL)){
            ci.cancel();
        }
    }
}
