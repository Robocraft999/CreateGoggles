package com.robocraft999.creategoggles.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.robocraft999.creategoggles.CreateGoggles;
import io.github.fabricators_of_create.porting_lib.client.armor.ArmorRenderer;
import io.github.fabricators_of_create.porting_lib.client.armor.ArmorRendererRegistry;
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

//TODO 0.5.1 change to DivingHelmetItem and BacktankItem
//@Mixin(DivingHelmetItem.class)
@Mixin(value = HumanoidArmorLayer.class)
public abstract class MixinCopperArmorItem{

    /*@Inject(
            method = "renderArmorPiece",
            at = @At("HEAD")
            //at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/HumanoidArmorLayer;renderModel(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/item/ArmorItem;ZLnet/minecraft/client/model/HumanoidModel;ZFFFLjava/lang/String;)V", ordinal = 1)
    )*/
    private<T extends LivingEntity, A extends HumanoidModel<T>> void renderArmorPieceProxy(PoseStack poseStack, MultiBufferSource multiBufferSource, T livingEntity, EquipmentSlot equipmentSlot, int i, A humanoidModel, CallbackInfo ci){
        ItemStack itemStack = livingEntity.getItemBySlot(equipmentSlot);
        ArmorRenderer renderer = ArmorRendererRegistry.get(itemStack.getItem());
        if (itemStack.getItem() instanceof ArmorItem armorItem){
            CreateGoggles.LOGGER.info("renderArmorPieceProxyInfo " + armorItem + ", " + (armorItem instanceof DyeableLeatherItem));
            CreateGoggles.LOGGER.info("renderArmorPieceRenderer " + renderer);
            /*if (armorItem instanceof DyeableLeatherItem){
                int j = ((DyeableLeatherItem)armorItem).getColor(itemStack);
                float f = (float)(j >> 16 & 255) / 255.0F;
                float g = (float)(j >> 8 & 255) / 255.0F;
                float h = (float)(j & 255) / 255.0F;
                CreateGoggles.LOGGER.info("renderArmorPieceColor " + f + ", " + g + ", " + h);
            }*/
        }
    }

    @Shadow
    abstract <T extends LivingEntity, A extends HumanoidModel<T>> void renderModel(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, ArmorItem armorItem, boolean bl, A humanoidModel, boolean bl2, float f, float g, float h, @Nullable String string);

    @Inject(
            method = "renderArmorPiece",
            //at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/HumanoidArmorLayer;renderModel(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/item/ArmorItem;ZLnet/minecraft/client/model/HumanoidModel;ZFFFLjava/lang/String;)V", ordinal = 2),
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;hasFoil()Z",
                    shift = At.Shift.AFTER
            ),
            cancellable = true,
            //locals = LocalCapture.PRINT
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    public<T extends LivingEntity, A extends HumanoidModel<T>> void checkForColoredArmor(PoseStack matrices, MultiBufferSource vertexConsumers, T entity, EquipmentSlot armorSlot, int light, A model, CallbackInfo ci, ItemStack itemStack, ArmorItem armorItem, boolean bl) {
        CreateGoggles.LOGGER.info("renderArmorPieceInject " + itemStack + ", " + (itemStack.getItem() instanceof DyeableLeatherItem));
        boolean bl2 = itemStack.hasFoil();
        if (armorItem instanceof DyeableLeatherItem) {
            int j = ((DyeableLeatherItem)armorItem).getColor(itemStack);
            float f = (float)(j >> 16 & 255) / 255.0F;
            float g = (float)(j >> 8 & 255) / 255.0F;
            float h = (float)(j & 255) / 255.0F;
            CreateGoggles.LOGGER.info("renderArmorPieceColor " + f + ", " + g + ", " + h);
            this.renderModel(matrices, vertexConsumers, light, armorItem, bl2, model, bl, f, g, h, (String)null);
            this.renderModel(matrices, vertexConsumers, light, armorItem, bl2, model, bl, 1.0F, 1.0F, 1.0F, "overlay");
            ci.cancel();
        }
    }

    /*@Inject(
            method = "renderArmorPiece",
            at = @At(value = "CONSTANT", args = "classValue=Lnet/minecraft/world/item/DyeableArmorItem;log=true", ordinal = 0, shift = At.Shift.AFTER)
    )
    private<T extends LivingEntity, A extends HumanoidModel<T>> void renderArmorPieceInject(PoseStack poseStack, MultiBufferSource multiBufferSource, T livingEntity, EquipmentSlot equipmentSlot, int i, A humanoidModel, CallbackInfo ci){

    }*/


    /*@ModifyConstant(
            method = "renderArmorPiece",
            constant = @Constant(classValue = DyeableArmorItem.class, ordinal = 0, log = true)
    )
    private Class<?> renderArmorPieceProxy(Object obj, Class<?> clazz){
        CreateGoggles.LOGGER.info("renderArmorPieceClass " + clazz);
        return DyeableLeatherItem.class;
    }*/

    /*@Inject(
            method = "isWornBy",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void isWornByProxy(Entity entity, boolean fireproof, CallbackInfoReturnable<Boolean> cir){
        if ((Object)this instanceof DivingHelmetItem)
            cir.setReturnValue(cir.getReturnValueZ() || DivingGoggleArmor.isWornBy((LivingEntity) entity));
        else if((Object)this instanceof BacktankItem){
            cir.setReturnValue(cir.getReturnValueZ() || BacktankArmor.isWornBy((LivingEntity) entity));
        }
    }*/
}
