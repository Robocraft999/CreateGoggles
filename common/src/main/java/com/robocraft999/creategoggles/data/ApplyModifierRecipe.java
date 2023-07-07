package com.robocraft999.creategoggles.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.robocraft999.creategoggles.CGConfig;
import com.robocraft999.creategoggles.item.modifier.ItemModifier;
import com.robocraft999.creategoggles.item.modifier.ItemModifierManager;
import com.robocraft999.creategoggles.registry.CGItemModifiers;
import com.robocraft999.creategoggles.registry.CGRecipeTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.UpgradeRecipe;
import net.minecraft.world.level.Level;

import static com.robocraft999.creategoggles.CreateGoggles.REGISTRATE;

public class ApplyModifierRecipe extends UpgradeRecipe {
    private final ItemModifier modifier;
    private final Ingredient addition;

    public ApplyModifierRecipe(ResourceLocation id, Ingredient addition, ItemModifier modifier) {
        super(id, Ingredient.EMPTY, addition, ItemStack.EMPTY);
        this.addition = addition;
        this.modifier = modifier;
    }

    @Override
    public boolean matches(Container inv, Level level) {
        if (!this.modifier.isItemValid(inv.getItem(0))) {
            return false;
        }
        if (!CGConfig.COMMON.enableExperimentalFeatures.get()) {
            return false;
        }
        return this.addition.test(inv.getItem(1));
    }


    @Override
    public ItemStack assemble(Container inv) {
        ItemStack stack = inv.getItem(0).copy();

        ItemModifierManager.setModifier(stack, this.modifier);

        return stack;
    }


    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CGRecipeTypes.APPLY_MODFIER.get();
    }

    public ItemModifier getModifier() {
        return this.modifier;
    }

    public Ingredient getAddition() {
        return this.addition;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public static class Serializer implements RecipeSerializer<ApplyModifierRecipe> {

        @Override
        public ApplyModifierRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            Ingredient addition = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "addition"));

            ResourceLocation resourceLocation = ResourceLocation.tryParse(GsonHelper.getAsString(json, "modifier"));
            String name = resourceLocation.getPath();

            if (!REGISTRATE.getOptional(name, CGItemModifiers.ITEM_MODIFIER_REGISTRY).isPresent()) {
                throw new JsonSyntaxException("Unknown item modifier '" + resourceLocation + "'");
            }
            ItemModifier modifier = REGISTRATE.get(name, CGItemModifiers.ITEM_MODIFIER_REGISTRY).get();
            return new ApplyModifierRecipe(recipeId, addition, modifier);
        }

        @Override
        public ApplyModifierRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            Ingredient addition = Ingredient.fromNetwork(buffer);
            ItemModifier modifier = REGISTRATE.get(buffer.readResourceLocation().getPath(), CGItemModifiers.ITEM_MODIFIER_REGISTRY).get();
            return new ApplyModifierRecipe(recipeId, addition, modifier);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ApplyModifierRecipe recipe) {
            recipe.addition.toNetwork(buffer);
            buffer.writeResourceLocation(recipe.getModifier().getRegistryName());
        }
    }
}
