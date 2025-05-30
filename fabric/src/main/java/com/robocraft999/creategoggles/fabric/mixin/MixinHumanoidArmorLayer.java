package com.robocraft999.creategoggles.fabric.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.robocraft999.creategoggles.registry.CGTrimPatterns;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(HumanoidArmorLayer.class)
public abstract class MixinHumanoidArmorLayer {
    @Shadow
    protected abstract <T extends LivingEntity, A extends HumanoidModel<T>> void renderModel(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, ArmorItem armorItem, A humanoidModel, boolean bl, float f, float g, float h, @Nullable String string);
    @Shadow
    protected abstract boolean usesInnerModel(EquipmentSlot slot);
    @Shadow
    protected abstract <T extends LivingEntity, A extends HumanoidModel<T>> void renderGlint(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, A humanoidModel);
    @Shadow
    protected abstract <T extends LivingEntity, A extends HumanoidModel<T>> void renderTrim(ArmorMaterial armorMaterial, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, ArmorTrim armorTrim, A humanoidModel, boolean bl);


    @Inject(
            method = "renderArmorPiece",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/layers/HumanoidArmorLayer;usesInnerModel(Lnet/minecraft/world/entity/EquipmentSlot;)Z"
            ),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    public<T extends LivingEntity, A extends HumanoidModel<T>> void checkForColoredArmor(PoseStack poseStack, MultiBufferSource multiBufferSource, T entity, EquipmentSlot armorSlot, int light, A humanoidModel, CallbackInfo ci, ItemStack itemStack, ArmorItem armorItem) {
        if (armorItem instanceof DyeableLeatherItem leatherItem) {
            boolean bl = usesInnerModel(armorSlot);
            int j = leatherItem.getColor(itemStack);
            float f = (float)(j >> 16 & 255) / 255.0F;
            float g = (float)(j >> 8 & 255) / 255.0F;
            float h = (float)(j & 255) / 255.0F;
            this.renderModel(poseStack, multiBufferSource, light, armorItem, humanoidModel, bl, f, g, h, (String)null);
            this.renderModel(poseStack, multiBufferSource, light, armorItem, humanoidModel, bl, 1.0F, 1.0F, 1.0F, "overlay");

            ArmorTrim.getTrim(entity.level().registryAccess(), itemStack).ifPresent((armorTrim) -> this.renderTrim(armorItem.getMaterial(), poseStack, multiBufferSource, light, armorTrim, humanoidModel, bl));
            if (itemStack.hasFoil()) {
                this.renderGlint(poseStack, multiBufferSource, light, humanoidModel);
            }
            ci.cancel();
        }
    }

    @Inject(
            method = "renderTrim*",
            at = @At("HEAD"),
            cancellable = true
    )
    public<T extends LivingEntity, A extends HumanoidModel<T>> void onRenderArmorTrim(ArmorMaterial armorMaterial, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, ArmorTrim armorTrim, A humanoidModel, boolean bl, CallbackInfo ci){
        if (armorTrim.material().is(CGTrimPatterns.GOGGLE_MATERIAL)){
            ci.cancel();
        }
    }
}
