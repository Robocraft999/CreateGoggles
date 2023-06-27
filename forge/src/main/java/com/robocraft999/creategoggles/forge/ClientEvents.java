package com.robocraft999.creategoggles.forge;

import com.robocraft999.creategoggles.forge.item.goggle.GoggleArmorLayerForge;
import com.robocraft999.creategoggles.item.modifier.ItemModifierManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.List;

@EventBusSubscriber(Dist.CLIENT)
public class ClientEvents {

	@SubscribeEvent
	public static void onTooltip(ItemTooltipEvent event) {
		ItemStack stack = event.getItemStack();
		List<Component> components = event.getToolTip();
		if (ItemModifierManager.hasModifier(stack)) {
			components.add(1, ItemModifierManager.getModifier(stack).getHintComponent());
		}
	}
	@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
	public static class ModBusEvents {

		@SubscribeEvent
		public static void addEntityRendererLayers(EntityRenderersEvent.AddLayers event){
			EntityRenderDispatcher dispatcher = Minecraft.getInstance().getEntityRenderDispatcher();

			GoggleArmorLayerForge.registerOnAll(dispatcher);
		}
	}
}
