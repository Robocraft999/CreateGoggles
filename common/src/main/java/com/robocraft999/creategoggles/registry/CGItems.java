package com.robocraft999.creategoggles.registry;

import com.robocraft999.creategoggles.CreateGoggles;
import com.robocraft999.creategoggles.item.ArmorColor;
import com.robocraft999.creategoggles.item.backtank.DyableBacktankItem;
import com.robocraft999.creategoggles.item.goggle.DivingGoggleHelmet;
import com.robocraft999.creategoggles.item.goggle.DyableGoggleHelmet;
import com.robocraft999.creategoggles.item.goggle.GoggleHelmet;
import com.robocraft999.creategoggles.item.goggle.IGoggleHelmet;
import com.simibubi.create.AllTags;
import com.simibubi.create.Create;
import com.simibubi.create.content.equipment.armor.AllArmorMaterials;
import com.simibubi.create.content.equipment.armor.BacktankBlock;
import com.simibubi.create.content.equipment.armor.BacktankItem;
import com.simibubi.create.content.equipment.goggles.GogglesItem;
import com.simibubi.create.foundation.data.AssetLookup;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Supplier;

import static com.robocraft999.creategoggles.CreateGoggles.REGISTRATE;
import static com.simibubi.create.AllTags.forgeItemTag;

public class CGItems {
    static {
        REGISTRATE.creativeModeTab(() -> CreativeModeTab.TAB_COMBAT);
        //TODO remove
        CreateGoggles.LOGGER.info("cgitems");
    }

    public static final ItemEntry<? extends GoggleHelmet>
            CHAINMAIL_GOGGLE_HELMET = goggleHelmet("goggle_chainmail_helmet", ArmorMaterials.CHAIN),
            DIAMOND_GOGGLE_HELMET = goggleHelmet("goggle_diamond_helmet", ArmorMaterials.DIAMOND),
            GOLDEN_GOGGLE_HELMET = goggleHelmet("goggle_golden_helmet", ArmorMaterials.GOLD),
            IRON_GOGGLE_HELMET = goggleHelmet("goggle_iron_helmet", ArmorMaterials.IRON),
            TURTLE_GOGGLE_HELMET = goggleHelmet("goggle_turtle_helmet", ArmorMaterials.TURTLE),
            NETHERITE_GOGGLE_HELMET = REGISTRATE.item("goggle_netherite_helmet", p -> new GoggleHelmet(ArmorMaterials.NETHERITE, p.fireResistant(), vanillaArmorLoc(ArmorMaterials.NETHERITE))).register(),
            LEATHER_GOGGLE_HELMET = REGISTRATE
                    .item("goggle_leather_helmet", p -> new DyableGoggleHelmet(ArmorMaterials.LEATHER, p, vanillaArmorLoc(ArmorMaterials.LEATHER)))
                    .model((ctx, p) -> p.generated(
                            ctx::getEntry,
                            new ResourceLocation(CreateGoggles.MOD_ID, "item/goggle_leather_helmet"),
                            new ResourceLocation(CreateGoggles.MOD_ID,"item/goggle_leather_helmet_overlay")))
                    .color(() -> ArmorColor::new)
                    .register();

    public static final ItemEntry<DivingGoggleHelmet>
            DIVING_GOGGLE_HELMET = REGISTRATE
                    .item("goggle_diving_helmet", p -> new DivingGoggleHelmet(AllArmorMaterials.COPPER, p, Create.asResource("copper_diving")))
                    .register();
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
                    .item("leather_backtank", p -> new DyableBacktankItem(ArmorMaterials.LEATHER, p, new ResourceLocation("leather"), CGItems.LEATHER_BACKTANK_PLACEABLE))
                    .model(AssetLookup.customGenericItemModel("_", "item"))
                    .color(() -> ArmorColor::new)
                    .tag(AllTags.AllItemTags.PRESSURIZED_AIR_SOURCES.tag)
                    .tag(forgeItemTag("chestplates"))
                    .register();

    private static ItemEntry<? extends GoggleHelmet> goggleHelmet(String name, ArmorMaterial material){
        return REGISTRATE.item(name, p -> new GoggleHelmet(material, p, vanillaArmorLoc(material))).register();
    }

    private static ItemEntry<BacktankItem.BacktankBlockItem> backtank_placable(String name, Supplier<ItemEntry<? extends BacktankItem>> item,
                                                                               Supplier<BlockEntry<? extends BacktankBlock>> block){
        return REGISTRATE
                .item(name + "_placeable", p -> new BacktankItem.BacktankBlockItem(block.get().get(), item.get()::get, p))
                .model((c, p) -> p.withExistingParent(c.getName(), p.mcLoc("item/barrier")))
                .register();
    }

    private static ItemEntry<? extends BacktankItem> backtank(String name, ArmorMaterial material, ItemEntry<BacktankItem.BacktankBlockItem> placable){
        return REGISTRATE
                .item(name, p -> new BacktankItem(material, p, vanillaArmorLoc(material), placable))
                .model(AssetLookup.customGenericItemModel("_", "item"))
                .tag(AllTags.AllItemTags.PRESSURIZED_AIR_SOURCES.tag)
                .tag(forgeItemTag("chestplates"))
                .register();
    }

    private static ResourceLocation vanillaArmorLoc(ArmorMaterial material){
        return new ResourceLocation(material.getName());
    }

    public static void register() {
        GogglesItem.addIsWearingPredicate(IGoggleHelmet::isGoggleHelmet);
    }
}
