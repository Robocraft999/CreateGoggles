package com.robocraft999.creategoggles.data;

import com.robocraft999.creategoggles.registry.CGItems;
import com.tterrag.registrate.util.entry.ItemEntry;

import static com.robocraft999.creategoggles.CreateGoggles.REGISTRATE;

public class CGLang {

    private static void addTooltips() {
        backtankTooltip(CGItems.CHAINMAIL_BACKTANK);
        backtankTooltip(CGItems.DIAMOND_BACKTANK);
        backtankTooltip(CGItems.GOLDEN_BACKTANK);
        backtankTooltip(CGItems.IRON_BACKTANK);
        backtankTooltip(CGItems.LEATHER_BACKTANK);

        goggleTooltip(CGItems.CHAINMAIL_GOGGLE_HELMET);
        goggleTooltip(CGItems.DIAMOND_GOGGLE_HELMET);
        goggleTooltip(CGItems.GOLDEN_GOGGLE_HELMET);
        goggleTooltip(CGItems.IRON_GOGGLE_HELMET);
        goggleTooltip(CGItems.TURTLE_GOGGLE_HELMET);
        goggleTooltip(CGItems.NETHERITE_GOGGLE_HELMET);
        goggleTooltip(CGItems.LEATHER_GOGGLE_HELMET);
        goggleTooltip(CGItems.DIVING_GOGGLE_HELMET);
    }

    private static void tooltip(ItemEntry<?> item, String category, String value) {
        if(!category.isEmpty())
            REGISTRATE.addLang("item", item.getId(), "tooltip."+category, value);
        else
            REGISTRATE.addLang("item", item.getId(), "tooltip", value);
    }

    private static void backtankTooltip(ItemEntry<?> item){
        tooltip(item, "", "item." + item.getId().getPath().toUpperCase());
        tooltip(item, "summary", "A _Wearable_ _Tank_ for carrying Pressurized Air that is plated with armor to protect the wearer.");
        tooltip(item, "condition1", "When worn");
        tooltip(item, "behaviour1", "Provides _Pressurized_ _Air_ to Equipment that requires it.");
        tooltip(item, "condition2", "When placed, Powered by Kinetics");
        tooltip(item, "behaviour2", "_Collects_ _Pressurized_ _Air_ at a rate depending on the Rotational Speed.");
    }

    private static void goggleTooltip(ItemEntry<?> item){
        tooltip(item, "", "item." + item.getId().getPath().toUpperCase());
        tooltip(item, "summary", "Augments your HUD with _miscellaneous information_ about placed components and provides armor.");
        tooltip(item, "condition1", "When looking at blocks");
        tooltip(item, "behaviour1", "_Kinetic components_ show added _Stress Impact_ or _Capacity_. _Stressometers_ show statistics of their _attached kinetic network_. Some other blocks reveal information such as item and fluid content.");
    }

    public static void register(){
        addTooltips();
    }
}
