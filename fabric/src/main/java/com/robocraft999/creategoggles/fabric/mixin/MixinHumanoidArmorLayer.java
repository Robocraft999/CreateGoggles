package com.robocraft999.creategoggles.fabric.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
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

    @Inject(
            method = "renderArmorPiece",
            //at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/HumanoidArmorLayer;renderModel(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/item/ArmorItem;ZLnet/minecraft/client/model/HumanoidModel;ZFFFLjava/lang/String;)V", ordinal = 2),
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;hasFoil()Z",
                    shift = At.Shift.AFTER
            ),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    public<T extends LivingEntity, A extends HumanoidModel<T>> void checkForColoredArmor(PoseStack poseStack, MultiBufferSource multiBufferSource, T entity, EquipmentSlot armorSlot, int light, A humanoidModel, CallbackInfo ci, ItemStack itemStack, ArmorItem armorItem, boolean bl) {
        boolean bl2 = itemStack.hasFoil();
        if (armorItem instanceof DyeableLeatherItem leatherItem) {
            int j = leatherItem.getColor(itemStack);
            float f = (float)(j >> 16 & 255) / 255.0F;
            float g = (float)(j >> 8 & 255) / 255.0F;
            float h = (float)(j & 255) / 255.0F;
            this.renderModel(poseStack, multiBufferSource, light, armorItem, humanoidModel, bl, f, g, h, (String)null);
            this.renderModel(poseStack, multiBufferSource, light, armorItem, humanoidModel, bl, 1.0F, 1.0F, 1.0F, "overlay");
            ci.cancel();
        }
    }
}
