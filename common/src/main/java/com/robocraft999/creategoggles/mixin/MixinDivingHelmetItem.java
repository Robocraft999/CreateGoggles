package com.robocraft999.creategoggles.mixin;


import com.simibubi.create.content.equipment.armor.DivingHelmetItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(DivingHelmetItem.class)
public abstract class MixinDivingHelmetItem {
    /*@Redirect(
            method = "breatheUnderwater",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/simibubi/create/content/curiosities/armor/CopperArmorItem;isWornBy(Lnet/minecraft/world/entity/Entity;)Z"
            )
    )
    private static boolean isWornByProxy(CopperArmorItem item, Entity entity){
        return item.isWornBy(entity) || DivingGoggleArmor.isWornBy((LivingEntity) entity);
    }*/

    /*public boolean isWornBy(Entity entity){
        return ((DivingHelmetItem)(Object)this).isWornBy(entity) || DivingGoggleArmor.isWornBy((LivingEntity) entity);
    }*/

    /*
    @Inject(
            target = @Desc(
                    value = "isWornBy",
                    owner = CopperArmorItem.class,
                    args=Entity.class,
                    ret=boolean.class
            ),
            at = @At("RETURN")
    )
    public void onIsWornByReturn(CallbackInfoReturnable<Boolean> cir, Entity entity){
        cir.setReturnValue(cir.getReturnValueZ() || DivingGoggleArmor.isWornBy((LivingEntity) entity));
    }*/
}
