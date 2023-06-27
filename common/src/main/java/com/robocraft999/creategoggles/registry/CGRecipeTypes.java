package com.robocraft999.creategoggles.registry;

import com.robocraft999.creategoggles.CreateGoggles;
import com.robocraft999.creategoggles.data.ApplyModifierRecipe;
import com.robocraft999.creategoggles.data.NBTCraftingRecipe;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class CGRecipeTypes {

	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_REGISTER = DeferredRegister.create(CreateGoggles.MOD_ID, Registry.RECIPE_SERIALIZER_REGISTRY);

	public static final RegistrySupplier<RecipeSerializer<?>>
			CRAFTING_NBT = RECIPE_REGISTER.register("crafting_nbt", NBTCraftingRecipe.Serializer::new),
			APPLY_MODFIER = RECIPE_REGISTER.register("apply_modifier", ApplyModifierRecipe.Serializer::new);

	public static void register() {
		RECIPE_REGISTER.register();
	}
}
