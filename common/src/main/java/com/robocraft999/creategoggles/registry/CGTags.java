package com.robocraft999.creategoggles.registry;

import com.robocraft999.creategoggles.CreateGoggles;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class CGTags {

   public static class Items{
       @Deprecated
       public static final TagKey<Item> GOGGLE = modItemTag("goggle");

       public static final TagKey<Item> GOGGLE_MODIFIER_INCOMPATIBLE = modItemTag("modifier/goggle_incompatible");
       public static final TagKey<Item> REMOVAL_MODIFIER_INCOMPATIBLE = modItemTag("modifier/removal_incompatible");

       public static final TagKey<Item> ARMORS_HELMETS = commonItemTag("helmets");
   }

    private static TagKey<Item> forgeItemTag(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("forge", name));
    }

    private static TagKey<Item> commonItemTag(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", name));
    }

    private static TagKey<Item> modItemTag(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(CreateGoggles.MOD_ID, name));
    }
}
