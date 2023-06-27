package com.robocraft999.creategoggles.registry;

import com.robocraft999.creategoggles.CreateGoggles;
import com.robocraft999.creategoggles.RegistrateRegistryKeyProvider;
import com.robocraft999.creategoggles.item.modifier.ItemModifier;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

import static com.robocraft999.creategoggles.CreateGoggles.REGISTRATE;

public class CGItemModifiers {
    public static final ResourceKey<Registry<ItemModifier>> ITEM_MODIFIER_REGISTRY = RegistrateRegistryKeyProvider.itemModifierRegistryKey();
            //REGISTRATE.makeRegistry("item_modifier", () -> new RegistryBuilder<>());
            //ResourceKey.createRegistryKey(CreateGoggles.asResource("item_modifier"));

    public static final RegistryEntry<? extends ItemModifier>
            GOGGLE_MODIFIER = modifier(
                    "goggle_modifier", stack -> stack.getItem() instanceof ArmorItem armorItem && armorItem.getSlot() == EquipmentSlot.HEAD, ItemTags.DIRT
            );

    private static RegistryEntry<? extends ItemModifier> modifier(String name, Predicate<ItemStack> predicate, TagKey<Item> incompatibleItems) {
        return REGISTRATE.object(name).simple(name, ITEM_MODIFIER_REGISTRY, () -> new ItemModifier(name, predicate, incompatibleItems));
    }

    public static void register() {
        CreateGoggles.LOGGER.info("CGItemModifiers register " + REGISTRATE.isRegistered(ITEM_MODIFIER_REGISTRY));
    }
}
