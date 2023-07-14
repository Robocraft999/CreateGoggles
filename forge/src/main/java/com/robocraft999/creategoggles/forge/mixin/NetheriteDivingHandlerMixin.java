package com.robocraft999.creategoggles.forge.mixin;

import com.simibubi.create.content.equipment.armor.BacktankItem;
import com.simibubi.create.content.equipment.armor.BacktankUtil;
import com.simibubi.create.content.equipment.armor.DivingHelmetItem;
import com.simibubi.create.content.equipment.armor.NetheriteDivingHandler;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static com.simibubi.create.content.equipment.armor.NetheriteDivingHandler.*;

@Mixin(NetheriteDivingHandler.class)
public class NetheriteDivingHandlerMixin {

    @Inject(
            method = "onLivingEquipmentChange",
            at = @At("TAIL"),
            locals = LocalCapture.CAPTURE_FAILHARD,
            remap = false
    )
    private static void onLivingEquipmentChangeProxy(LivingEquipmentChangeEvent event, CallbackInfo ci, EquipmentSlot slot, LivingEntity entity, ItemStack to){
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
