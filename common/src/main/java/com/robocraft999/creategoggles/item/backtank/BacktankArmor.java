package com.robocraft999.creategoggles.item.backtank;

import com.simibubi.create.content.equipment.armor.BacktankItem;
import com.simibubi.create.content.equipment.armor.BacktankUtil;
import com.simibubi.create.content.equipment.armor.CapacityEnchantment.ICapacityEnchantable;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.robocraft999.creategoggles.CreateGoggles.REGISTRATE;
public class BacktankArmor extends ArmorItem implements ICapacityEnchantable {
    private String blockItemId;

    public BacktankArmor(ArmorMaterial material, String id, Properties properties) {
        super(material, EquipmentSlot.CHEST, properties);
        this.blockItemId = id;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        return REGISTRATE.get(blockItemId, Registry.ITEM_REGISTRY).get().useOn(context);
    }

    @Override
    public boolean canBeDepleted() {
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> stacks) {
        if (this.allowedIn(tab)) {
            ItemStack stack = new ItemStack(this);
            CompoundTag nbt = new CompoundTag();
            nbt.putInt("Air", BacktankUtil.maxAirWithoutEnchants());
            stack.setTag(nbt);
            stacks.add(stack);
        }
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return BacktankItem.BAR_COLOR;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.round(13.0F * Mth.clamp(BacktankUtil.getAir(stack) / ((float) BacktankUtil.maxAir(stack)), 0, 1));
    }

    public static boolean isWornBy(LivingEntity entity) {
        AtomicBoolean isWorn = new AtomicBoolean(entity.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof BacktankArmor);
        //TODO move to separate function
        /*if (ModCompat.CURIOS.isLoaded()) {
            entity.getCapability(CuriosCapability.INVENTORY).ifPresent(handler -> {
                ICurioStacksHandler stacksHandler = handler.getCurios().get("body");
                if (stacksHandler != null) {
                    for (int i = 0; i < stacksHandler.getSlots(); i++) {
                        if (AllItems.COPPER_BACKTANK.isIn(stacksHandler.getStacks().getStackInSlot(i))) {
                            isWorn.set(true);
                            break;
                        }
                    }
                }
            });
        }*/
        return isWorn.get();
    }

    public static class ArmoredBacktankBlockItem extends BlockItem {
        public ArmoredBacktankBlockItem(Block pBlock, Item.Properties pProperties) {
            super(pBlock, pProperties);
        }

        public void fillItemCategory(CreativeModeTab pGroup, NonNullList<ItemStack> pItems) {
        }

        public String getDescriptionId() {
            return this.getOrCreateDescriptionId();
        }
    }

}
