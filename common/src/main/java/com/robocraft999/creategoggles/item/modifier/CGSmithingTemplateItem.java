package com.robocraft999.creategoggles.item.modifier;

import com.robocraft999.creategoggles.CreateGoggles;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class CGSmithingTemplateItem extends SmithingTemplateItem {
    private static final ChatFormatting TITLE_FORMAT;
    private static final ChatFormatting DESCRIPTION_FORMAT;
    private static final ResourceLocation EMPTY_SLOT_HELMET;
    private static final ResourceLocation EMPTY_SLOT_GOGGLES;
    public CGSmithingTemplateItem(Component component, Component component2, Component component3, Component component4, Component component5, List<ResourceLocation> list, List<ResourceLocation> list2) {
        super(component, component2, component3, component4, component5, list, list2);
    }

    private static Component[] genUpgradeDescriptions(String name){
        return new Component[]{
                Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(CreateGoggles.MOD_ID, "smithing_template." + name + ".applies_to"))).withStyle(DESCRIPTION_FORMAT),
                Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(CreateGoggles.MOD_ID, "smithing_template." + name + ".ingredients"))).withStyle(DESCRIPTION_FORMAT),
                //Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation(CreateGoggles.MOD_ID, "goggle_upgrade"))).withStyle(TITLE_FORMAT),
                Component.translatable(Util.makeDescriptionId("trim_pattern", new ResourceLocation(CreateGoggles.MOD_ID, name))).withStyle(TITLE_FORMAT),
                Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(CreateGoggles.MOD_ID, "smithing_template." + name + ".base_slot_description"))),
                Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(CreateGoggles.MOD_ID, "smithing_template." + name + ".additions_slot_description")))
        };
    }

    public static CGSmithingTemplateItem createGoggleArmorTrimTemplateItem(){
        Component[] upgradeDescriptions = genUpgradeDescriptions("goggle");
        return new CGSmithingTemplateItem(
                upgradeDescriptions[2],
                upgradeDescriptions[0],
                upgradeDescriptions[1],
                upgradeDescriptions[3],
                upgradeDescriptions[4],
                List.of(EMPTY_SLOT_HELMET),
                List.of(EMPTY_SLOT_GOGGLES)
        );
    }

    static {
        TITLE_FORMAT = ChatFormatting.GRAY;
        DESCRIPTION_FORMAT = ChatFormatting.BLUE;

        EMPTY_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
        EMPTY_SLOT_GOGGLES = new ResourceLocation("item/empty_armor_slot_helmet");
        //EMPTY_SLOT_GOGGLES = new ResourceLocation(CreateGoggles.MOD_ID, "item/empty_armor_slot_goggles");
    }
}


