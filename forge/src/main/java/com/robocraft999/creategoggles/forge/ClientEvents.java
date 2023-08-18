package com.robocraft999.creategoggles.forge;

import com.robocraft999.creategoggles.CreateGoggles;
import com.robocraft999.creategoggles.forge.item.goggle.GoggleArmorLayerForge;
import com.robocraft999.creategoggles.item.modifier.ItemModifier;
import com.robocraft999.creategoggles.item.modifier.ItemModifierManager;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
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
			RegistryEntry<ItemModifier> optionalModifier = ItemModifierManager.getModifier(stack);
			if (!optionalModifier.isPresent())
				return;
			components.add(1, optionalModifier.get().getHintComponent());
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

			GoggleArmorLayerForge.registerOnAll(dispatcher);
		}

		@SubscribeEvent
		public static void onModelRegister(ModelEvent.RegisterAdditional event) {
			event.register(new ModelResourceLocation(CreateGoggles.MOD_ID, "goggle","inventory"));
		}
	}
}
