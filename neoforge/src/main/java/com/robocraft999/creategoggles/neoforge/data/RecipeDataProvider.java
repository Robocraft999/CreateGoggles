package com.robocraft999.creategoggles.neoforge.data;

import com.robocraft999.creategoggles.CreateGoggles;
import com.robocraft999.creategoggles.data.NBTCraftingRecipeBuilder;
import com.robocraft999.creategoggles.neoforge.registry.CGItemsNeoforge;
import com.robocraft999.creategoggles.registry.CGItems;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.kinetics.crusher.CrushingRecipe;
import com.simibubi.create.content.processing.recipe.StandardProcessingRecipe;
import mekanism.common.registries.MekanismItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.data.recipes.RecipeBuilder.getDefaultRecipeId;

public class RecipeDataProvider extends RecipeProvider {
    public RecipeDataProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(packOutput, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput consumer) {
        helmetRecipe(CGItems.CHAINMAIL_GOGGLE_HELMET.get(), Items.CHAINMAIL_HELMET, consumer);
        helmetRecipe(CGItems.DIAMOND_GOGGLE_HELMET.get(), Items.DIAMOND_HELMET, consumer);
        helmetRecipe(CGItems.GOLDEN_GOGGLE_HELMET.get(), Items.GOLDEN_HELMET, consumer);
        helmetRecipe(CGItems.IRON_GOGGLE_HELMET.get(), Items.IRON_HELMET, consumer);
        helmetRecipe(CGItems.LEATHER_GOGGLE_HELMET.get(), Items.LEATHER_HELMET, consumer);
        helmetRecipe(CGItems.TURTLE_GOGGLE_HELMET.get(), Items.TURTLE_HELMET, consumer);
        helmetRecipe(CGItems.NETHERITE_GOGGLE_HELMET.get(), Items.NETHERITE_HELMET, consumer);
        helmetRecipe(CGItems.DIVING_GOGGLE_HELMET.get(), AllItems.COPPER_DIVING_HELMET.get(), consumer);
        helmetRecipe(CGItems.NETHERITE_DIVING_GOGGLE_HELMET.get(), AllItems.NETHERITE_DIVING_HELMET.get(), consumer);
        helmetRecipe(CGItems.CARDBOARD_GOGGLE_HELMET.get(), AllItems.CARDBOARD_HELMET.get(), consumer);

        backtankRecipe(CGItems.CHAINMAIL_BACKTANK.get(), Items.CHAINMAIL_CHESTPLATE, consumer);
        backtankRecipe(CGItems.DIAMOND_BACKTANK.get(), Items.DIAMOND_CHESTPLATE, consumer);
        backtankRecipe(CGItems.GOLDEN_BACKTANK.get(), Items.GOLDEN_CHESTPLATE, consumer);
        backtankRecipe(CGItems.IRON_BACKTANK.get(), Items.IRON_CHESTPLATE, consumer);
        backtankRecipe(CGItems.LEATHER_BACKTANK.get(), Items.LEATHER_CHESTPLATE, consumer);

        mekModule(CGItemsNeoforge.GOGGLE_UNIT.get(), CGItems.NETHERITE_GOGGLE_HELMET.get(), consumer);

        /*SmithingTrimRecipeBuilder
                .smithingTrim(Ingredient.of(CGItems.GOGGLE_ARMOR_TRIM_SMITHING_TEMPLATE.get()), Ingredient.of(Tags.Items.), Ingredient.of(AllItems.GOGGLES.get()), RecipeCategory.COMBAT)
                .unlocks("has_trim", has(CGItems.GOGGLE_ARMOR_TRIM_SMITHING_TEMPLATE.get()))
                .save(consumer, CreateGoggles.asResource("goggle_armor_trimming"));*/
        SmithingTrimRecipeBuilder
                .smithingTrim(Ingredient.of(CGItems.GOGGLE_ARMOR_TRIM_SMITHING_TEMPLATE.get()), Ingredient.of(ItemTags.HEAD_ARMOR), Ingredient.of(AllItems.GOGGLES.get()), RecipeCategory.COMBAT)
                .unlocks("has_trim", has(CGItems.GOGGLE_ARMOR_TRIM_SMITHING_TEMPLATE.get()))
                .save(consumer, CreateGoggles.asResource("goggle_armor_trimming_c"));

        ShapedRecipeBuilder
                .shaped(RecipeCategory.COMBAT, CGItems.MODIFIER_REMOVER.get())
                .pattern(" B")
                .pattern("B ")
                .define('B', Ingredient.of(AllItems.BRASS_INGOT.get()))
                .unlockedBy("has_brass", has(AllItems.BRASS_INGOT.get()))
                .save(consumer);

        ShapedRecipeBuilder
                .shaped(RecipeCategory.COMBAT, CGItems.GOGGLE_ARMOR_TRIM_SMITHING_TEMPLATE.get())
                .pattern("DGD")
                .pattern("DCD")
                .pattern("DDD")
                .define('D', Ingredient.of(Items.DIAMOND))
                .define('C', Ingredient.of(Items.COBBLESTONE))
                .define('G', Ingredient.of(AllItems.GOGGLES.get()))
                .unlockedBy("has_goggles", has(AllItems.GOGGLES.get()))
                .save(consumer, CreateGoggles.asResource("smithing_template_single"));

        ShapedRecipeBuilder
                .shaped(RecipeCategory.COMBAT, CGItems.GOGGLE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), 2)
                .pattern("DTD")
                .pattern("DCD")
                .pattern("DDD")
                .define('D', Ingredient.of(Items.DIAMOND))
                .define('C', Ingredient.of(Items.COBBLESTONE))
                .define('T', Ingredient.of(CGItems.GOGGLE_ARMOR_TRIM_SMITHING_TEMPLATE.get()))
                .unlockedBy("has_goggles", has(AllItems.GOGGLES.get()))
                .save(consumer, CreateGoggles.asResource("smithing_template_duplicate"));

        SmithingTransformRecipeBuilder
                .smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(CGItems.DIAMOND_GOGGLE_HELMET.get()),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.MISC,
                        CGItems.NETHERITE_GOGGLE_HELMET.asItem()
                ).unlocks("has_helmet", has(CGItems.DIAMOND_GOGGLE_HELMET.get()))
                .save(consumer, ResourceLocation.fromNamespaceAndPath(CreateGoggles.MOD_ID, "goggle_netherite_helmet_smithing"));
    }

    private void helmetRecipe(ItemLike result, ItemLike helmet, RecipeOutput writer){
        NBTCraftingRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("hg")
                .define('h', helmet)
                .define('g', AllItems.GOGGLES.get())
                .unlockedBy(getHasName(helmet), has(helmet))
                .save(writer);
        crushingGoggle(result, helmet, writer);
    }

    private void backtankRecipe(ItemLike result, ItemLike chestplate, RecipeOutput writer){
        NBTCraftingRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .pattern("cb")
                .define('c', chestplate)
                .define('b', AllItems.COPPER_BACKTANK.get())
                .unlockedBy(getHasName(chestplate), has(chestplate))
                .save(writer);
        crushing(result, chestplate, AllItems.COPPER_BACKTANK.get(), writer);
    }

    private void crushingGoggle(ItemLike input, ItemLike output, RecipeOutput writer){
        crushing(input, output, AllItems.GOGGLES.get(), writer);
    }

    private void crushing(ItemLike input, ItemLike output1, ItemLike output2, RecipeOutput writer){
        StandardProcessingRecipe.Builder<CrushingRecipe> crushingRecipeBuilder =
                new StandardProcessingRecipe.Builder<>(CrushingRecipe::new, CreateGoggles.asResource(getSimpleRecipeName(output1.asItem())));
        crushingRecipeBuilder.withItemIngredients(Ingredient.of(input))
                .output(output1)
                .output(output2)
                .duration(150)
                .build(writer);
    }

    private void mekModule(ItemLike module, ItemLike input, RecipeOutput writer){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, module)
                .pattern("A#A")
                .pattern("ABA")
                .pattern("HHH")
                .define('A', MekanismItems.REINFORCED_ALLOY)
                .define('#', input)
                .define('B', MekanismItems.MODULE_BASE)
                .define('H', MekanismItems.HDPE_SHEET)
                .unlockedBy(getHasName(input), has(input))
                .save(writer.withConditions(new ModLoadedCondition("mekanism")), getDefaultRecipeId(module));
    }
}
