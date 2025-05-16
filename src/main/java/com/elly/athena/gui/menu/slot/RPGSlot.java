package com.elly.athena.gui.menu.slot;

import com.elly.athena.item.use.potion.RPGPotion_Base;
import com.elly.athena.item.skill.RPGSkill_Base;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Container;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.jetbrains.annotations.NotNull;

public class RPGSlot extends Slot {
    private final LivingEntity owner;

    public RPGSlot(Container container, LivingEntity owner, int slot, int x, int y) {
        super(container, slot, x, y);
        this.owner = owner;
    }

    public void setByPlayer(@NotNull ItemStack newItem, @NotNull ItemStack oldItem) {
        onEquipItem(oldItem, newItem);
        super.setByPlayer(newItem, oldItem);
    }

    public boolean mayPlace(ItemStack stack) {
        if(stack.isEmpty()) return false;
        Item item = stack.getItem();
        if(item instanceof RPGSkill_Base || item instanceof RPGPotion_Base){
            return true;
        }
        return false;
    }

    public boolean mayPickup(Player stack) {
        ItemStack itemstack = this.getItem();
        return (itemstack.isEmpty() ||
                stack.isCreative() ||
                !EnchantmentHelper.has(itemstack, EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE)) &&
                super.mayPickup(stack);
    }

    private void onEquipItem(ItemStack oldItem, ItemStack newItem){
        if (owner.level().isClientSide() || owner.isSpectator()) return;
        boolean flag = newItem.isEmpty() && oldItem.isEmpty();
        if(flag) return;
        if(ItemStack.isSameItemSameComponents(oldItem, newItem)) return;
        boolean canEquip = mayPlace(newItem);
        if(!owner.isSilent())
            owner.level().playSeededSound((Player)null, owner.getX(), owner.getY(), owner.getZ(), SoundEvents.ARMOR_EQUIP_CHAIN, owner.getSoundSource(), 1.0F, 1.0F, owner.getRandom().nextLong());
    }
}