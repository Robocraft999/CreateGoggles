package com.robocraft999.creategoggles.forge.mixin;

import com.simibubi.create.content.equipment.armor.DivingHelmetItem;
import com.simibubi.create.foundation.events.ClientEvents;
import com.simibubi.create.foundation.fluid.FluidHelper;
import net.minecraft.client.Camera;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static com.simibubi.create.content.equipment.armor.NetheriteDivingHandler.isNetheriteArmor;

@Mixin(ClientEvents.class)
public class ClientEventsMixin {

    // doesn't work for now. Probably best to just make a PR to Create
    /*
    @Redirect(
            method = "getFogDensity",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/tterrag/registrate/util/entry/ItemProviderEntry;isIn(Lnet/minecraft/world/item/ItemStack;)Z"
            )
    )
    private boolean getFogDensityProxy(ItemEntry<DivingHelmetItem> itemEntry, ItemStack stack) {
        return itemEntry.isIn(stack);
    }*/

    //TODO make a PR to Create because this is bad
    @Inject(
            method = "getFogDensity",
            at = @At("TAIL"),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILHARD,
            remap = false
    )
    private static void onGetFogDensity(EntityViewRenderEvent.RenderFogEvent event, CallbackInfo ci, Camera camera, Level level,
                                        BlockPos blockPos, FluidState fluidState, Fluid fluid, Entity entity, ItemStack divingHelmet
    ){
        if (!divingHelmet.isEmpty() && FluidHelper.isLava(fluid) && divingHelmet.getItem() instanceof DivingHelmetItem && isNetheriteArmor(divingHelmet)){
            event.setNearPlaneDistance(-4.0f);
            event.setFarPlaneDistance(20.0f);
            event.setCanceled(true);
            ci.cancel();
        }
    }
}
