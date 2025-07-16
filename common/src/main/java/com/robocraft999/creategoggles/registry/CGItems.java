package com.robocraft999.creategoggles.registry;

import com.robocraft999.creategoggles.CreateGoggles;
import com.robocraft999.creategoggles.item.ArmorColor;
import com.robocraft999.creategoggles.item.backtank.DyableBacktankItem;
import com.robocraft999.creategoggles.item.goggle.*;
import com.robocraft999.creategoggles.item.modifier.CGSmithingTemplateItem;
import com.simibubi.create.AllTags;
import com.simibubi.create.Create;
import com.simibubi.create.content.equipment.armor.AllArmorMaterials;
import com.simibubi.create.content.equipment.armor.BacktankBlock;
import com.simibubi.create.content.equipment.armor.BacktankItem;
import com.simibubi.create.content.equipment.goggles.GogglesItem;
import com.simibubi.create.foundation.data.AssetLookup;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.*;

import java.util.function.Supplier;

import static com.robocraft999.creategoggles.CreateGoggles.REGISTRATE;

public class CGItems {
    static {
        //TODO remove
        CreateGoggles.LOGGER.info("cgitems");
    }

    public static final ItemEntry<? extends IGoggleHelmet>
            CHAINMAIL_GOGGLE_HELMET = goggleHelmet("goggle_chainmail_helmet", ArmorMaterials.CHAIN, 15),
            DIAMOND_GOGGLE_HELMET = goggleHelmet("goggle_diamond_helmet", ArmorMaterials.DIAMOND, 33),
            GOLDEN_GOGGLE_HELMET = goggleHelmet("goggle_golden_helmet", ArmorMaterials.GOLD, 7),
            IRON_GOGGLE_HELMET = goggleHelmet("goggle_iron_helmet", ArmorMaterials.IRON, 15),
            TURTLE_GOGGLE_HELMET = goggleHelmet("goggle_turtle_helmet", ArmorMaterials.TURTLE, 25),
            NETHERITE_GOGGLE_HELMET = goggleHelmet("goggle_netherite_helmet", p ->
                    new GoggleHelmet(ArmorMaterials.NETHERITE, p.fireResistant().durability(ArmorItem.Type.HELMET.getDurability(37)), vanillaArmorLoc(ArmorMaterials.NETHERITE)))
                    .register(),
            LEATHER_GOGGLE_HELMET = goggleHelmet("goggle_leather_helmet", p ->
                    new DyableGoggleHelmet(ArmorMaterials.LEATHER, p.durability(ArmorItem.Type.HELMET.getDurability(5)), vanillaArmorLoc(ArmorMaterials.LEATHER)))
                    /*.model((ctx, p) -> p.generated(
                            ctx::getEntry,
                            new ResourceLocation(CreateGoggles.MOD_ID, "item/goggle_leather_helmet"),
                            new ResourceLocation(CreateGoggles.MOD_ID,"item/goggle_leather_helmet_overlay")))*/
                    .model(AssetLookup.existingItemModel())
                    .tag(ItemTags.DYEABLE)
                    .color(() -> ArmorColor::new)
                    .register(),
            DIVING_GOGGLE_HELMET = goggleHelmet("goggle_diving_helmet", p ->
                    new DivingGoggleHelmet(AllArmorMaterials.COPPER, p.durability(ArmorItem.Type.HELMET.getDurability(7)), Create.asResource("copper_diving"))).register(),
            NETHERITE_DIVING_GOGGLE_HELMET = goggleHelmet("goggle_netherite_diving_helmet", p ->
                    new DivingGoggleHelmet(ArmorMaterials.NETHERITE, p.fireResistant().durability(ArmorItem.Type.HELMET.getDurability(37)), Create.asResource("netherite_diving"))).register(),
            CARDBOARD_GOGGLE_HELMET = goggleHelmet("goggle_cardboard_helmet", p -> new CardboardGoggleHelmet(p.durability(ArmorItem.Type.HELMET.getDurability(4)))).register();
    public static final ItemEntry<BacktankItem.BacktankBlockItem>
            CHAINMAIL_BACKTANK_PLACEABLE = backtank_placable("chainmail_backtank", () -> CGItems.CHAINMAIL_BACKTANK, () -> CGBlocks.CHAINMAIL_BACKTANK_BLOCK),
            DIAMOND_BACKTANK_PLACABLE = backtank_placable("diamond_backtank", () -> CGItems.DIAMOND_BACKTANK, () -> CGBlocks.DIAMOND_BACKTANK_BLOCK),
            GOLDEN_BACKTANK_PLACEABLE = backtank_placable("golden_backtank", () -> CGItems.GOLDEN_BACKTANK, () -> CGBlocks.GOLDEN_BACKTANK_BLOCK),
            IRON_BACKTANK_PLACEABLE = backtank_placable("iron_backtank", () -> CGItems.IRON_BACKTANK, () -> CGBlocks.IRON_BACKTANK_BLOCK),
            LEATHER_BACKTANK_PLACEABLE = backtank_placable("leather_backtank", () -> CGItems.LEATHER_BACKTANK, () -> CGBlocks.LEATHER_BACKTANK_BLOCK);

    public static final ItemEntry<? extends BacktankItem>
            CHAINMAIL_BACKTANK = backtank("chainmail_backtank", ArmorMaterials.CHAIN, CHAINMAIL_BACKTANK_PLACEABLE),
            DIAMOND_BACKTANK = backtank("diamond_backtank", ArmorMaterials.DIAMOND, DIAMOND_BACKTANK_PLACABLE),
            GOLDEN_BACKTANK = backtank("golden_backtank", ArmorMaterials.GOLD, GOLDEN_BACKTANK_PLACEABLE),
            IRON_BACKTANK = backtank("iron_backtank", ArmorMaterials.IRON, IRON_BACKTANK_PLACEABLE),
            LEATHER_BACKTANK = REGISTRATE
                    .item("leather_backtank", p ->
                            new DyableBacktankItem(ArmorMaterials.LEATHER, p, ResourceLocation.withDefaultNamespace("leather"), CGItems.LEATHER_BACKTANK_PLACEABLE))
                    .model(AssetLookup.customGenericItemModel("_", "item"))
                    //.color(() -> ArmorColor::new)
                    .tag(AllTags.AllItemTags.PRESSURIZED_AIR_SOURCES.tag)
                    .tag(ItemTags.CHEST_ARMOR)
                    .tag(ItemTags.TRIMMABLE_ARMOR)
                    .tag(ItemTags.DYEABLE)
                    .register();

    public static final ItemEntry<Item> MODIFIER_REMOVER = REGISTRATE
            .item("modifier_remover", Item::new)
            .register();

    public static final ItemEntry<SmithingTemplateItem> GOGGLE_ARMOR_TRIM_SMITHING_TEMPLATE = REGISTRATE
            .item("goggle_armor_trim_smithing_template", p -> CGSmithingTemplateItem.createGoggleArmorTrimTemplateItem())
            .tag(ItemTags.TRIM_TEMPLATES)
            .lang("Smithing Template")
            .register();


    private static ItemEntry<? extends GoggleHelmet> goggleHelmet(String name, Holder<ArmorMaterial> material, int durability){
        return goggleHelmet(name, p ->  new GoggleHelmet(material, p.durability(ArmorItem.Type.HELMET.getDurability(durability)), vanillaArmorLoc(material))).register();
    }

    private static <T extends Item & IGoggleHelmet> ItemBuilder<T, ?> goggleHelmet(String name, NonNullFunction<Item.Properties, T> builder){
        return REGISTRATE
                .item(name, builder)
                .tag(CGTags.Items.GOGGLE)
                .tag(ItemTags.TRIMMABLE_ARMOR)
                .tag(ItemTags.HEAD_ARMOR);
    }

    private static ItemEntry<BacktankItem.BacktankBlockItem> backtank_placable(String name, Supplier<ItemEntry<? extends BacktankItem>> item,
                                                                               Supplier<BlockEntry<? extends BacktankBlock>> block){
        return REGISTRATE
                .item(name + "_placeable", p -> new BacktankItem.BacktankBlockItem(block.get().get(), item.get()::get, p))
                //.model((c, p) -> p.withExistingParent(c.getName(), p.mcLoc("item/barrier")))
                .model(AssetLookup.existingItemModel())
                .register();
    }

    private static ItemEntry<? extends BacktankItem> backtank(String name, Holder<ArmorMaterial> material, ItemEntry<BacktankItem.BacktankBlockItem> placable){
        return REGISTRATE
                .item(name, p -> new BacktankItem(material, p, vanillaArmorLoc(material), placable))
                .model(AssetLookup.customGenericItemModel("_", "item"))
                .tag(AllTags.AllItemTags.PRESSURIZED_AIR_SOURCES.tag)
                .tag(ItemTags.TRIMMABLE_ARMOR)
                .tag(ItemTags.CHEST_ARMOR)
                .register();
    }

    private static ResourceLocation vanillaArmorLoc(Holder<ArmorMaterial> material){
        return ResourceLocation.parse(material.getRegisteredName());
    }

    public static void register() {
        GogglesItem.addIsWearingPredicate(IGoggleHelmet::isGoggleHelmet);
    }
}
