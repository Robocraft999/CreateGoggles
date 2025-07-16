package com.robocraft999.creategoggles.data;

import com.mojang.serialization.MapCodec;
import com.robocraft999.creategoggles.registry.CGRecipeTypes;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map.Entry;

public record NBTCraftingRecipe(ShapedRecipe recipe) implements CraftingRecipe {

	@Override
	public boolean matches(@Nonnull CraftingInput input, @Nonnull Level worldIn) {
		return recipe().matches(input, worldIn);
	}

	@Nonnull
	@Override
	public NonNullList<Ingredient> getIngredients() {
		return recipe.getIngredients();
	}

	@Nonnull
	@Override
	public ItemStack assemble(CraftingInput input, HolderLookup.Provider provider) {
		HashMap<Holder<Enchantment>, Integer> allEnchants = new HashMap<>();
		ItemStack nbtItemResult = ItemStack.EMPTY;
		for (int slot = 0; slot < input.size(); slot++) {
			ItemStack nbtItem = input.getItem(slot).copy();
			//TODO consider using getComponentMap to copy all components
			if(nbtItem.isEmpty() || (!nbtItem.isEnchantable() && !nbtItem.isEnchanted()))continue;

			if(nbtItemResult.isEmpty()) {
				nbtItemResult = new ItemStack(getResultItem(provider).getItem());
			}
			var ench = nbtItem.get(DataComponents.ENCHANTMENTS);
			if (ench != null) {
				ench.entrySet().forEach(entry -> allEnchants.put(entry.getKey(), entry.getIntValue()));
			}
		}
		if(!allEnchants.isEmpty() || !nbtItemResult.isEmpty()){
			for (Entry<Holder<Enchantment>, Integer> entry : allEnchants.entrySet()) {
				Holder<Enchantment> enchantment = entry.getKey();
				if (nbtItemResult.getEnchantmentLevel(enchantment) == 0) {
					nbtItemResult.enchant(enchantment, entry.getValue());
				}
			}
		}
		return nbtItemResult;
	}

	@Override
	public ItemStack getResultItem(HolderLookup.Provider provider) {
		return recipe().getResultItem(provider);
	}

	@Nonnull
	@Override
	public RecipeSerializer<?> getSerializer() {
		return CGRecipeTypes.CRAFTING_NBT.get();
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return recipe().canCraftInDimensions(width, height);
	}


	@Override
	public CraftingBookCategory category() {
		return CraftingBookCategory.EQUIPMENT;
	}


	public static class Serializer implements RecipeSerializer<NBTCraftingRecipe> {
		public static final MapCodec<NBTCraftingRecipe> CODEC = RecipeSerializer.SHAPED_RECIPE.codec().xmap(NBTCraftingRecipe::new, NBTCraftingRecipe::recipe);
		public static final StreamCodec<RegistryFriendlyByteBuf, NBTCraftingRecipe> STREAM_CODEC = RecipeSerializer.SHAPED_RECIPE.streamCodec().map(NBTCraftingRecipe::new, NBTCraftingRecipe::recipe);

		@Override
		public MapCodec<NBTCraftingRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, NBTCraftingRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}


}
