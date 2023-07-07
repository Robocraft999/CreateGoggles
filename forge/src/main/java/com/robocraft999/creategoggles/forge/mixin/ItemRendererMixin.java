package com.robocraft999.creategoggles.forge.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.robocraft999.creategoggles.item.modifier.ItemModifierManager;
import com.robocraft999.creategoggles.registry.CGItemModifiers;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Final
    @Shadow
    private ItemModelShaper itemModelShaper;

    @Shadow
    protected abstract void renderModelLists(BakedModel bakedModel, ItemStack itemStack, int i, int j, PoseStack poseStack, VertexConsumer vertexConsumer);

    @Inject(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;renderModelLists(Lnet/minecraft/client/resources/model/BakedModel;Lnet/minecraft/world/item/ItemStack;IILcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;)V"
                    , shift = At.Shift.AFTER
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    public void onRender(ItemStack itemStack, ItemTransforms.TransformType transformType, boolean bl, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j, BakedModel bakedModel, CallbackInfo ci, boolean bl2, boolean bl3, Iterator iter, BakedModel bakedModel2, Iterator iter2, RenderType renderType, VertexConsumer vertexConsumer){
        if (ItemModifierManager.hasSpecificModifier(itemStack, CGItemModifiers.GOGGLE_MODIFIER.get())){
            BakedModel model = this.itemModelShaper.getModelManager().getModel(new ModelResourceLocation("creategoggles:goggle#inventory"));
            this.renderModelLists(model, itemStack, i, j, poseStack, vertexConsumer);
        }
    }
}
