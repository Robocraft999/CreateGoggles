package com.robocraft999.creategoggles.mixin;

import com.simibubi.create.content.equipment.armor.BacktankItem;
import com.simibubi.create.content.equipment.armor.BacktankUtil;
import com.simibubi.create.content.equipment.armor.DivingHelmetItem;
import com.simibubi.create.content.equipment.armor.NetheriteDivingHandler;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.simibubi.create.content.equipment.armor.NetheriteDivingHandler.*;

@Mixin(NetheriteDivingHandler.class)
public class NetheriteDivingHandlerMixin {

    @Inject(
            method = "onLivingEquipmentChange",
            at = @At("TAIL")
    )
    private static void onLivingEquipmentChangeProxy(LivingEntity entity, EquipmentSlot slot, @NotNull ItemStack from, @NotNull ItemStack to, CallbackInfo ci){
        if (slot == EquipmentSlot.HEAD) {
            if (to.getItem() instanceof DivingHelmetItem && isNetheriteArmor(to)) {
                setBit(entity, slot);
            } else {
                clearBit(entity, slot);
            }
        } else if (slot == EquipmentSlot.CHEST) {
            if (to.getItem() instanceof BacktankItem && isNetheriteArmor(to) && BacktankUtil.hasAirRemaining(to)) {
                setBit(entity, slot);
            } else {
                clearBit(entity, slot);
            }
        }
    }

}
