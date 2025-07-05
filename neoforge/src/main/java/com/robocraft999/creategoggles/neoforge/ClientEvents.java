package com.robocraft999.creategoggles.neoforge;

import com.robocraft999.creategoggles.CreateGoggles;
import com.robocraft999.creategoggles.item.ArmorColor;
import com.robocraft999.creategoggles.item.modifier.ArmorTrimHelper;
import com.robocraft999.creategoggles.neoforge.item.goggle.GoggleArmorLayerNeoforge;
import com.robocraft999.creategoggles.registry.CGItems;
import com.robocraft999.creategoggles.registry.CGTrimPatterns;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

@EventBusSubscriber(value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        List<Component> components = event.getToolTip();
        if (ArmorTrimHelper.hasMaterialAndPattern(stack, CGTrimPatterns.GOGGLE_MATERIAL.location(), CGTrimPatterns.GOGGLE_PATTERN.location())) {
            components.add(1, Component.translatable("hint.creategoggles.modifier.goggle_modifier").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
            if(stack.isEnchanted()){
                components.add(2, Component.empty());
            }
        }
    }

    @EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
    public static class ModBusEvents {

        @SubscribeEvent
        public static void addEntityRendererLayers(EntityRenderersEvent.AddLayers event){
            EntityRenderDispatcher dispatcher = Minecraft.getInstance().getEntityRenderDispatcher();

            GoggleArmorLayerNeoforge.registerOnAll(dispatcher);
        }

        @SubscribeEvent
        public static void onModelRegister(ModelEvent.RegisterAdditional event) {
            event.register(ModelResourceLocation.standalone(CreateGoggles.asResource("item/goggle")));
        }

        @SubscribeEvent
        public static void onColorRegister(RegisterColorHandlersEvent.Item event) {
            event.register(new ArmorColor(), CGItems.LEATHER_GOGGLE_HELMET, CGItems.LEATHER_BACKTANK);
        }
    }
}
