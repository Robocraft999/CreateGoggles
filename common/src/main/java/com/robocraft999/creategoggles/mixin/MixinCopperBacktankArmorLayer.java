package com.robocraft999.creategoggles.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.robocraft999.creategoggles.CreateGoggles;
import com.simibubi.create.content.equipment.armor.BacktankArmorLayer;
import com.simibubi.create.content.equipment.armor.BacktankBlock;
import com.simibubi.create.content.equipment.armor.BacktankUtil;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static com.robocraft999.creategoggles.CreateGoggles.REGISTRATE;

@Mixin(BacktankArmorLayer.class)
public class MixinCopperBacktankArmorLayer {

    /*@Redirect(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/simibubi/create/content/curiosities/armor/CopperArmorItem;isWornBy(Lnet/minecraft/world/entity/Entity;)Z"
            )
    )
    public boolean isWornByProxy(CopperArmorItem item, Entity entity){
        return item.isWornBy(entity) || BacktankArmor.isWornBy((LivingEntity) entity);
    }*/
    //TODO 0.5.1 left out because the item is get from backtankitem which has mixin for isWornBy
    // https://github.com/Creators-of-Create/Create/blob/mc1.19/0.5.1/src/main/java/com/simibubi/create/content/equipment/armor/BacktankArmorLayer.java#L37
    @ModifyVariable(method = "render", at=@At("STORE"))
    protected BlockState onRenderedBlockstate(BlockState renderedState, PoseStack ms, MultiBufferSource buffer, int light, LivingEntity entity){
        if(!BacktankUtil.get(entity).is(Blocks.AIR.asItem())) {
            String s = BacktankUtil.get(entity).getDescriptionId();
            if(s.split("[.]")[1].equals(CreateGoggles.MOD_ID)){

                return ((BlockEntry<? extends Block>)REGISTRATE.get(s.split("[.]")[2], Registry.BLOCK_REGISTRY)).getDefaultState().setValue(BacktankBlock.HORIZONTAL_FACING, Direction.SOUTH);
            }
        }
        return renderedState;
    }
}
