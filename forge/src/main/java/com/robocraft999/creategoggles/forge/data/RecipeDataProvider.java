package com.robocraft999.creategoggles.forge.data;

import com.robocraft999.creategoggles.CreateGoggles;
import com.robocraft999.creategoggles.data.ApplyModifierRecipeBuilder;
import com.robocraft999.creategoggles.data.CreateGogglesRecipeBuilder;
import com.robocraft999.creategoggles.forge.registry.CPItems;
import com.robocraft999.creategoggles.item.modifier.ItemModifier;
import com.robocraft999.creategoggles.registry.CGItemModifiers;
import com.robocraft999.creategoggles.registry.CGItems;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.kinetics.crusher.CrushingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class RecipeDataProvider extends RecipeProvider {
    public RecipeDataProvider(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        helmetRecipe(CGItems.CHAINMAIL_GOGGLE_HELMET.get(), Items.CHAINMAIL_HELMET, consumer);
        helmetRecipe(CGItems.DIAMOND_GOGGLE_HELMET.get(), Items.DIAMOND_HELMET, consumer);
        helmetRecipe(CGItems.GOLDEN_GOGGLE_HELMET.get(), Items.GOLDEN_HELMET, consumer);
        helmetRecipe(CGItems.IRON_GOGGLE_HELMET.get(), Items.IRON_HELMET, consumer);
        helmetRecipe(CGItems.LEATHER_GOGGLE_HELMET.get(), Items.LEATHER_HELMET, consumer);
        helmetRecipe(CGItems.TURTLE_GOGGLE_HELMET.get(), Items.TURTLE_HELMET, consumer);
        helmetRecipe(CGItems.NETHERITE_GOGGLE_HELMET.get(), Items.NETHERITE_HELMET, consumer);
        helmetRecipe(CGItems.DIVING_GOGGLE_HELMET.get(), AllItems.COPPER_DIVING_HELMET.get(), consumer);
        helmetRecipe(CGItems.NETHERITE_DIVING_GOGGLE_HELMET.get(), AllItems.NETHERITE_DIVING_HELMET.get(), consumer);

        backtankRecipe(CGItems.CHAINMAIL_BACKTANK.get(), Items.CHAINMAIL_CHESTPLATE, consumer);
        backtankRecipe(CGItems.DIAMOND_BACKTANK.get(), Items.DIAMOND_CHESTPLATE, consumer);
        backtankRecipe(CGItems.GOLDEN_BACKTANK.get(), Items.GOLDEN_CHESTPLATE, consumer);
        backtankRecipe(CGItems.IRON_BACKTANK.get(), Items.IRON_CHESTPLATE, consumer);
        backtankRecipe(CGItems.LEATHER_BACKTANK.get(), Items.LEATHER_CHESTPLATE, consumer);

        mekModule(CPItems.GOGGLE_UNIT.get(), CGItems.NETHERITE_GOGGLE_HELMET.get(), consumer);

        modifier(CGItemModifiers.GOGGLE_MODIFIER.get(), AllItems.GOGGLES.get(), consumer);
        modifier(CGItemModifiers.REMOVEL_MODIFIER.get(), CGItems.MODIFIER_REMOVER.get(), consumer);
    }

    private void helmetRecipe(ItemLike result, ItemLike helmet, Consumer<FinishedRecipe> writer){
        CreateGogglesRecipeBuilder.shaped(result)
                .pattern("hg")
                .define('h', helmet)
                .define('g', AllItems.GOGGLES.get())
                .unlockedBy("has_helmet", has(helmet))
                .save(writer);
        crushingGoggle(result, helmet, writer);
    }

    private void backtankRecipe(ItemLike result, ItemLike chestplate, Consumer<FinishedRecipe> writer){
        CreateGogglesRecipeBuilder.shaped(result)
                .pattern("cb")
                .define('c', chestplate)
                .define('b', AllItems.COPPER_BACKTANK.get())
                .unlockedBy("has_backtank", has(chestplate))
                .save(writer);
        crushing(result, chestplate, AllItems.COPPER_BACKTANK.get(), writer);
    }

    private void crushingGoggle(ItemLike input, ItemLike output, Consumer<FinishedRecipe> writer){
        crushing(input, output, AllItems.GOGGLES.get(), writer);
    }

    private void crushing(ItemLike input, ItemLike output1, ItemLike output2, Consumer<FinishedRecipe> writer){
        ProcessingRecipeBuilder<CrushingRecipe> crushingRecipeBuilder =
                new ProcessingRecipeBuilder<>(CrushingRecipe::new, new ResourceLocation(CreateGoggles.MOD_ID, output1.asItem().toString()));
        crushingRecipeBuilder.withItemIngredients(Ingredient.of(input))
                .output(output1)
                .output(output2)
                .duration(150)
                .build(writer);
    }

    private void mekModule(ItemLike module, ItemLike input, Consumer<FinishedRecipe> writer){
        ShapedRecipeBuilder.shaped(module)
                .pattern("A#A")
                .pattern("ABA")
                .pattern("HHH")
                .define('A', Ingredient.fromJson(GsonHelper.parse("{\"item\": \"mekanism:alloy_reinforced\"}")))
                .define('#', input)
                .define('B', Ingredient.fromJson(GsonHelper.parse("{\"item\": \"mekanism:module_base\"}")))
                .define('H', Ingredient.fromJson(GsonHelper.parse("{\"item\": \"mekanism:hdpe_sheet\"}")))
                .unlockedBy("has_module", has(input))
                .save(writer);
    }

    private void modifier(ItemModifier modifier, ItemLike addition, Consumer<FinishedRecipe> writer){
        ApplyModifierRecipeBuilder.of(addition, modifier).save(writer);
    }
}
