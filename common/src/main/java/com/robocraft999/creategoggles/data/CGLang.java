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
        backtankTooltip(CGItems.NETHERITE_BACKTANK);
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
        tooltip(item, "summary", "A helmet with a pair of glasses to augment your vision with useful _kinetic information_.");
        tooltip(item, "condition1", "When worn");
        tooltip(item, "behaviour1", "Shows _colored indicators_ corresponding to the _Speed Level_ of a placed kinetic component as well as _Stress Impact_ and _Capacity_ of individual components.");
        tooltip(item, "condition2", "When looking at gauge");
        tooltip(item, "behaviour2", "Shows detailed information about _Speed_ or _Stress_ of the network to which the gauge is connected.");
        tooltip(item, "condition3", "When looking at fluid containers");
        tooltip(item, "behaviour3", "Shows detailed information about the _Capacity_ of the block and any _Fluids_ stored within.");
    }

    public static void register(){
        addTooltips();
    }
}
