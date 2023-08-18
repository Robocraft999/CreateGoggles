package com.robocraft999.creategoggles.data;

import com.google.gson.JsonObject;
import com.robocraft999.creategoggles.item.modifier.ItemModifier;
import com.robocraft999.creategoggles.registry.CGRecipeTypes;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * Code from Forbidden and Arcanus
 * @author stal111
 *
 * edited by Robocraft999
 */
public record ApplyModifierRecipeBuilder(Ingredient addition, ItemModifier modifier) implements RecipeBuilder {
    public static ApplyModifierRecipeBuilder of(ItemLike addition, ItemModifier modifier) {
        return new ApplyModifierRecipeBuilder(Ingredient.of(addition), modifier);
    }

    public static ApplyModifierRecipeBuilder of(Ingredient addition, ItemModifier modifier) {
        return new ApplyModifierRecipeBuilder(addition, modifier);
    }

    @Override
    public RecipeBuilder unlockedBy(String string, CriterionTriggerInstance criterionTriggerInstance) {
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String string) {
        return this;
    }

    @Override
    public Item getResult() {
        return null;
    }

    @Override
    public void save(Consumer<FinishedRecipe> consumer) {
        ResourceLocation key = this.modifier.getRegistryName();
        this.save(consumer, new ResourceLocation(key.getNamespace(), "smithing/apply_" + key.getPath()));
    }

    @Override
    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation) {
        consumer.accept(new ApplyModifierRecipeBuilder.Result(resourceLocation, this.addition, this.modifier));
    }

    private record Result(ResourceLocation recipeId, Ingredient addition, ItemModifier modifier) implements FinishedRecipe {

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.add("addition", this.addition.toJson());
            json.addProperty("modifier", this.modifier.getRegistryName().toString());
        }

        public RecipeSerializer<?> getType() {
            return CGRecipeTypes.APPLY_MODFIER.get();
        }

        public ResourceLocation getId() {
            return this.recipeId;
        }

        @Nullable
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Nullable
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}
