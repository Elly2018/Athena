package com.elly.athena.data.types;

import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.DataComponent_Register;
import com.elly.athena.data.component.ItemEquip;
import com.elly.athena.data.implementation.BattleHotbar;
import com.elly.athena.data.implementation.PlayerEquipment;
import com.elly.athena.item.equipment.RPGEquip_Base;
import com.elly.athena.item.potion.RPGPotion_Base;
import com.elly.athena.item.skill.RPGSkill_Base;
import com.elly.athena.system.BattleSystem;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.NeoForge;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModContainer implements Container, Nameable {

    public static final int INVENTORY_SIZE = 12;
    public static final int SELECTION_SIZE = 9;

    public final PlayerEquipment playerEquipment;
    public final BattleHotbar battleHotbar;
    public final Inventory playerInventory;

    private final Player player;
    private NonNullList<ItemStack> equips;
    private NonNullList<ItemStack> hotbar;
    private List<NonNullList<ItemStack>> compartments;
    private int timesChanged;

    public ModContainer(Player _player){
        player = _player;
        playerEquipment = _player.getData(Attachment_Register.PLAYER_EQUIPMENT);
        battleHotbar = _player.getData(Attachment_Register.BATTLE_HOTBAR);
        playerInventory = _player.getInventory();
        equips = playerEquipment.getList();
        hotbar = battleHotbar.getList();
        compartments = ImmutableList.of(equips, hotbar);
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        if(slot <= 11) return equipmentChecker(slot, stack);
        return stack.getItem() instanceof RPGPotion_Base ||
                stack.getItem() instanceof RPGSkill_Base;
    }

    @Override
    public boolean canTakeItem(Container target, int slot, ItemStack stack) {
        return true;
    }

    public ItemStack getSelected(){
        return Inventory.isHotbarSlot(playerInventory.selected) ? hotbar.get(playerInventory.selected) : ItemStack.EMPTY;
    }

    @Override
    public int getContainerSize() {
        return INVENTORY_SIZE + SELECTION_SIZE;
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : equips) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }
        for(ItemStack itemstack1 : hotbar) {
            if (!itemstack1.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int index) {
        List<ItemStack> list = null;

        for(NonNullList<ItemStack> nonnulllist : compartments) {
            if (index < nonnulllist.size()) {
                list = nonnulllist;
                break;
            }

            index -= nonnulllist.size();
        }

        return list == null ? ItemStack.EMPTY : (ItemStack)list.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        List<ItemStack> list = null;

        for(NonNullList<ItemStack> nonnulllist : compartments) {
            if (index < nonnulllist.size()) {
                list = nonnulllist;
                break;
            }

            index -= nonnulllist.size();
        }

        return list != null && !((ItemStack)list.get(index)).isEmpty() ? ContainerHelper.removeItem(list, index, count) : ItemStack.EMPTY;
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int index) {
        NonNullList<ItemStack> nonnulllist = null;

        for(NonNullList<ItemStack> nonnulllist1 : this.compartments) {
            if (index < nonnulllist1.size()) {
                nonnulllist = nonnulllist1;
                break;
            }

            index -= nonnulllist1.size();
        }

        if (nonnulllist != null && !((ItemStack)nonnulllist.get(index)).isEmpty()) {
            ItemStack itemstack = (ItemStack)nonnulllist.get(index);
            nonnulllist.set(index, ItemStack.EMPTY);
            return itemstack;
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public void setItem(int i, @NotNull ItemStack itemStack) {
        if(i >= getContainerSize()) return;
        if (i == 0) {playerEquipment.setMain(itemStack);}
        else if (i == 1) playerEquipment.setSecondary(itemStack);
        else if (i == 2) playerEquipment.setRing0(itemStack);
        else if (i == 3) playerEquipment.setRing1(itemStack);
        else if (i == 4) playerEquipment.setRing2(itemStack);
        else if (i == 5) playerEquipment.setRing3(itemStack);
        else if (i == 6) playerEquipment.setCape(itemStack);
        else if (i == 7) playerEquipment.setBelt(itemStack);
        else if (i == 8) playerEquipment.setFaceWear(itemStack);
        else if (i == 9) playerEquipment.setNecklace(itemStack);
        else if (i == 10) playerEquipment.setGlove(itemStack);
        else if (i == 11) playerEquipment.setOrb(itemStack);
        else {
            battleHotbar.setSlot(i - 12, itemStack);
        }
        setChanged();
    }

    @Override
    public void setChanged() {
        ++this.timesChanged;
        changeState();
    }
    public int getTimesChanged() {
        return this.timesChanged;
    }

    private void changeState(){
        player.setData(Attachment_Register.PLAYER_EQUIPMENT, playerEquipment);
        player.setData(Attachment_Register.BATTLE_HOTBAR, battleHotbar);
        equips = playerEquipment.getList();
        hotbar = battleHotbar.getList();
        compartments = ImmutableList.of(equips, hotbar);
    }


    @Override
    public boolean stillValid(Player player) {
        return player.canInteractWithEntity(this.player, (double)4.0F);
    }

    @Override
    public void clearContent() {
        for(int i = 0; i < getContainerSize(); i++){
            setItem(i, ItemStack.EMPTY);
        }
    }

    @Override
    public Component getName() {
        return Component.translatable("athena.container.inventory");
    }

    private boolean equipmentChecker(int slot, ItemStack stack){
        Item item = stack.getItem();
        if(slot == 0){
            return item instanceof SwordItem || item instanceof BowItem;
        }
        else if (slot == 1){
            return item instanceof ShieldItem;
        }else if (slot < 12 && item instanceof RPGEquip_Base record) {
            return ModEquipmentSlot.checkEquipable(slot, record.slot.index);
        }else{
            return false;
        }
    }
}
