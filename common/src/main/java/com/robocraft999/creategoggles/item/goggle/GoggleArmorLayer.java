package com.robocraft999.creategoggles.item.goggle;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.robocraft999.creategoggles.CGConfig;
import com.robocraft999.creategoggles.compat.CuriosCompatDummy;
import com.simibubi.create.AllItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import com.simibubi.create.AllPartialModels;

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

        ms.pushPose();

        // Translate and rotate to our head
        model.head.translateAndRotate(ms);

        // Translate offset
        double zOffset = IGoggleHelmet.getGoggleZOffset(entity);
        ms.translate(0.0D, -0.25D, (double) zOffset);
        
        ms.mulPose(Axis.ZP.rotationDegrees(180.0f));
        ms.scale(0.625f, 0.625f, 0.625f);

        if(CGConfig.CLIENT.moveGoggleToEyes.get()) {
            ms.mulPose(Axis.ZP.rotationDegrees(180.0f));
            ms.translate(0, -0.25, 0);
        }

        Minecraft.getInstance().getItemRenderer().render(stack, ItemDisplayContext.HEAD, false, ms, buffer, light, OverlayTexture.NO_OVERLAY, AllPartialModels.GOGGLES.get());
        
        ms.popPose();
    }
}
