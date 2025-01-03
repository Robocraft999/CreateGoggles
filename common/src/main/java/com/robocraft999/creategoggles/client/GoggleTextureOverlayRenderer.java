package com.robocraft999.creategoggles.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.robocraft999.creategoggles.CreateGoggles;
import com.robocraft999.creategoggles.item.modifier.ArmorTrimHelper;
import com.robocraft999.creategoggles.item.modifier.ItemModifierManager;
import com.robocraft999.creategoggles.registry.CGItemModifiers;
import com.robocraft999.creategoggles.registry.CGTrimPatterns;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.ItemStack;

public interface GoggleTextureOverlayRenderer {
    void renderModelLists(BakedModel bakedModel, ItemStack itemStack, int i, int j, PoseStack poseStack, VertexConsumer vertexConsumer);

    default void renderGoggleTexture(ItemModelShaper itemModelShaper, ItemStack itemStack, PoseStack poseStack, int i, int j, VertexConsumer vertexConsumer){
        BakedModel model = itemModelShaper.getModelManager().getModel(new ModelResourceLocation(CreateGoggles.MOD_ID,"goggle", "inventory"));
        this.renderModelLists(model, itemStack, i, j, poseStack, vertexConsumer);
    }

    default void renderInjection(ItemModelShaper itemModelShaper, ItemStack itemStack, PoseStack poseStack, int i, int j, VertexConsumer vertexConsumer){
        if (itemStack == null || itemStack.isEmpty())
            return;

        if (ItemModifierManager.hasSpecificModifier(itemStack, CGItemModifiers.GOGGLE_MODIFIER.get())){
            renderGoggleTexture(itemModelShaper, itemStack, poseStack, i, j, vertexConsumer);
        }

        if (ArmorTrimHelper.hasMaterialAndPattern(itemStack, CGTrimPatterns.GOGGLE_MATERIAL.location(), CGTrimPatterns.GOGGLE_PATTERN.location())){
            renderGoggleTexture(itemModelShaper, itemStack, poseStack, i, j, vertexConsumer);
        }

        /*if (itemStack.getTag() != null && itemStack.getTag().contains("Trim")){
            CompoundTag trimTag = itemStack.getTag().getCompound("Trim");
            ResourceLocation material = new ResourceLocation(trimTag.getString("material"));
            ResourceLocation pattern = new ResourceLocation(trimTag.getString("pattern"));

            if (material.equals(CGTrimPatterns.GOGGLE_MATERIAL.location()) && pattern.equals(CGTrimPatterns.GOGGLE_PATTERN.location())){
                renderGoggleTexture(itemModelShaper, itemStack, poseStack, i, j, vertexConsumer);
            }
        }*/
    }
}
