package com.robocraft999.creategoggles.neoforge.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.robocraft999.creategoggles.registry.CGTrimPatterns;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.core.Holder;
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
    public void onRenderArmorTrim(Holder<ArmorMaterial> armorMaterial, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, ArmorTrim armorTrim, HumanoidModel humanoidModel, boolean bl, CallbackInfo ci){
        if (armorTrim.material().is(CGTrimPatterns.GOGGLE_MATERIAL)){
            ci.cancel();
        }
    }
}
