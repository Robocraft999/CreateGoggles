package com.robocraft999.creategoggles.mixin;

import com.robocraft999.creategoggles.item.backtank.BacktankArmor;
import com.robocraft999.creategoggles.item.goggle.DivingGoggleArmor;
import com.simibubi.create.content.curiosities.armor.CopperArmorItem;
import com.simibubi.create.content.curiosities.armor.CopperBacktankItem;
import com.simibubi.create.content.curiosities.armor.DivingHelmetItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CopperArmorItem.class)
public class MixinCopperArmorItem {

    @Inject(
            method = "isWornBy",
            at = @At("RETURN"),
            cancellable = true
    )
    public void isWornByProxy(Entity entity, CallbackInfoReturnable<Boolean> cir){
        if ((Object)this instanceof DivingHelmetItem)
            cir.setReturnValue(cir.getReturnValueZ() || DivingGoggleArmor.isWornBy((LivingEntity) entity));
        else if((Object)this instanceof CopperBacktankItem){
            cir.setReturnValue(cir.getReturnValueZ() || BacktankArmor.isWornBy((LivingEntity) entity));
        }
    }
}
