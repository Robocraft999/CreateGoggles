package com.robocraft999.creategoggles.item.goggle;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.robocraft999.creategoggles.CGConfig;
import com.robocraft999.creategoggles.compat.CuriosCompatDummy;
import com.simibubi.create.AllItems;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class GoggleArmorLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {

    public GoggleArmorLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack ms, MultiBufferSource buffer, int light, LivingEntity entity, float yaw, float pitch, float pt, float p_117356_, float p_117357_, float p_117358_) {
        if (entity.getPose() == Pose.SLEEPING)
            return;
        if (!IGoggleHelmet.isGoggleHelmet(entity) || CuriosCompatDummy.predicate.test(entity))
            return;

        M entityModel = getParentModel();
        if (!(entityModel instanceof HumanoidModel<?> model))
            return;

        ItemStack stack = new ItemStack(AllItems.GOGGLES.get());

        // Translate and rotate with our head
        ms.pushPose();
        ms.translate(model.head.x / 16.0, model.head.y / 16.0, model.head.z / 16.0);
        ms.mulPose(Axis.YP.rotation(model.head.yRot));
        ms.mulPose(Axis.XP.rotation(model.head.xRot));

        // Translate and scale to our head
        ms.translate(0, -0.25, -0.05);
        ms.mulPose(Axis.ZP.rotationDegrees(180.0f));
        ms.scale(0.625f, 0.625f, 0.625f);

        if(CGConfig.CLIENT.moveGoggleToEyes.get()) {
            ms.mulPose(Axis.ZP.rotationDegrees(180.0f));
            ms.translate(0, -0.25, 0);
        }

        // Render
        Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.HEAD, light, OverlayTexture.NO_OVERLAY, ms, buffer, Minecraft.getInstance().level, 0);
        //Minecraft.getInstance().getItemRenderer().render(stack, ItemDisplayContext.HEAD, false, ms, buffer, light, OverlayTexture.NO_OVERLAY, AllPartialModels.GOGGLES.get());
        ms.popPose();
    }

    public static void registerOn(EntityRenderer<?> entityRenderer, LivingEntityFeatureRendererRegistrationCallback.RegistrationHelper helper) {
        if (!(entityRenderer instanceof LivingEntityRenderer<?, ?> livingRenderer))
            return;

        EntityModel<?> model = livingRenderer.getModel();

        if (!(model instanceof HumanoidModel))
            return;

        GoggleArmorLayer<?, ?> layer = new GoggleArmorLayer<>(livingRenderer);
        helper.register(layer);
    }
}
