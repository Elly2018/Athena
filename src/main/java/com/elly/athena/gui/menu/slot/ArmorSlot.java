package com.elly.athena.gui.menu.slot;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import javax.annotation.Nullable;

public class ArmorSlot extends Slot {
    private final LivingEntity owner;
    private final EquipmentSlot slot;
    @Nullable
    private final ResourceLocation emptyIcon;

    public ArmorSlot(
            Container container, LivingEntity owner, EquipmentSlot slot, int slotIndex, int x, int y, @Nullable ResourceLocation emptyIcon
    ) {
        super(container, slotIndex, x, y);
        this.owner = owner;
        this.slot = slot;
        this.emptyIcon = emptyIcon;
    }

    @Override
    public void setByPlayer(ItemStack newItem, ItemStack oldItem) {
        this.owner.onEquipItem(this.slot, oldItem, newItem);
        super.setByPlayer(newItem, oldItem);
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public boolean mayPlace(ItemStack p_345029_) {
        return p_345029_.canEquip(slot, owner);
    }

    @Override
    public boolean mayPickup(Player p_345575_) {
        ItemStack itemstack = this.getItem();
        return !itemstack.isEmpty() && !p_345575_.isCreative() && EnchantmentHelper.has(itemstack, EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE)
                ? false
                : super.mayPickup(p_345575_);
    }

    @Nullable
    @Override
    public ResourceLocation getNoItemIcon() {
        return this.emptyIcon;
    }
}