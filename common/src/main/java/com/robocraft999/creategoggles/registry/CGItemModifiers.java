package com.robocraft999.creategoggles.registry;

import com.robocraft999.creategoggles.CreateGoggles;
import com.robocraft999.creategoggles.item.modifier.ItemModifier;
import com.robocraft999.creategoggles.item.modifier.ItemModifierManager;
import com.robocraft999.creategoggles.item.modifier.RemovalItemModifier;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.Registries;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;

public class CGItemModifiers {

    public static final Registrar<ItemModifier> ITEM_MODIFIERS = Registries.get(CreateGoggles.MOD_ID).<ItemModifier>builder(CreateGoggles.asResource("item_modifier")).syncToClients().build();

    public static final RegistrySupplier<ItemModifier>
            GOGGLE_MODIFIER = ITEM_MODIFIERS.register(
                    CreateGoggles.asResource("goggle_modifier"),
                    () -> new ItemModifier(
                            "goggle_modifier",
                            stack -> stack.getItem() instanceof ArmorItem armorItem && armorItem.getSlot() == EquipmentSlot.HEAD,
                            CGTags.Items.GOGGLE_MODIFIER_INCOMPATIBLE
                    )
            ),
            REMOVEL_MODIFIER = ITEM_MODIFIERS.register(
                    CreateGoggles.asResource("removel_modifier"),
                    () -> new RemovalItemModifier(
                            "removel_modifier",
                            stack -> stack.getItem() instanceof ArmorItem && ItemModifierManager.hasModifier(stack),
                            CGTags.Items.REMOVAL_MODIFIER_INCOMPATIBLE
                    )
            );

    public static void register() {
    }
}
