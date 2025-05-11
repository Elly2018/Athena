package com.elly.athena.gui.menu.slot;

import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.attachment.IPlayerEquipment;
import com.elly.athena.data.types.ModEquipmentSlot;
import com.elly.athena.item.equipment.RPGEquip_Base;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Container;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ModArmorSlot extends Slot {
    private final LivingEntity owner;
    private final IPlayerEquipment equip;
    private final ModEquipmentSlot slot;
    @Nullable
    private final ResourceLocation emptyIcon;

    public ModArmorSlot(Container container, LivingEntity owner, ModEquipmentSlot slot, int slotIndex, int x, int y, @Nullable ResourceLocation emptyIcon) {
        super(container, slotIndex, x, y);
        this.owner = owner;
        this.slot = slot;
        this.emptyIcon = emptyIcon;
        this.equip = owner.getData(Attachment_Register.PLAYER_EQUIPMENT);
    }

    public void setByPlayer(@NotNull ItemStack newItem, @NotNull ItemStack oldItem) {
        onEquipItem(oldItem, newItem);
        super.setByPlayer(newItem, oldItem);
    }

    public int getMaxStackSize() {
        return 1;
    }

    public boolean mayPlace(ItemStack stack) {
        if(stack.isEmpty()) return false;

        Item item = stack.getItem();
        if(item instanceof RPGEquip_Base equip){
            return equip.canEquip(slot);
        }
        else if(item instanceof SwordItem || item instanceof BowItem){
            return slot == ModEquipmentSlot.MAIN;
        }
        else if(item instanceof ShieldItem){
            return slot == ModEquipmentSlot.SECONDARY;
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

    @Nullable
    public ResourceLocation getNoItemIcon() {
        return this.emptyIcon;
    }

    public ModEquipmentSlot getType(){
        return slot;
    }

    private void onEquipItem(ItemStack oldItem, ItemStack newItem) {
        if (owner.level().isClientSide() || owner.isSpectator()) return;
        boolean flag = newItem.isEmpty() && oldItem.isEmpty();
        if(flag) return;
        if(ItemStack.isSameItemSameComponents(oldItem, newItem)) return;
        boolean canEquip = mayPlace(newItem);
        if(!owner.isSilent())
            owner.level().playSeededSound((Player)null, owner.getX(), owner.getY(), owner.getZ(), SoundEvents.ARMOR_EQUIP_CHAIN, owner.getSoundSource(), 1.0F, 1.0F, owner.getRandom().nextLong());
        owner.gameEvent(canEquip ? GameEvent.EQUIP : GameEvent.UNEQUIP);
    }
}
