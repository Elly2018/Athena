package com.elly.athena.gui.menu;

import com.elly.athena.Athena;
import com.elly.athena.data.types.ModContainer;
import com.elly.athena.data.types.ModEquipmentSlot;
import com.elly.athena.gui.GUI_Register;
import com.elly.athena.gui.menu.slot.ArmorSlot;
import com.elly.athena.gui.menu.slot.ModArmorSlot;
import com.elly.athena.gui.menu.slot.RPGSlot;
import com.elly.athena.item.equipment.RPGEquip_Base;
import com.elly.athena.item.potion.RPGPotion_Base;
import com.elly.athena.item.skill.RPGSkill_Base;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.SwordItem;

import java.util.Map;

public class Equipment_Menu extends AbstractContainerMenu {
    private static final Map<EquipmentSlot, ResourceLocation> TEXTURE_EMPTY_SLOTS = Map.of(
            EquipmentSlot.FEET, InventoryMenu.EMPTY_ARMOR_SLOT_BOOTS,
            EquipmentSlot.LEGS, InventoryMenu.EMPTY_ARMOR_SLOT_LEGGINGS,
            EquipmentSlot.CHEST, InventoryMenu.EMPTY_ARMOR_SLOT_CHESTPLATE,
            EquipmentSlot.HEAD, InventoryMenu.EMPTY_ARMOR_SLOT_HELMET);
    private static final EquipmentSlot[] SLOT_IDS = new EquipmentSlot[]{
            EquipmentSlot.HEAD, EquipmentSlot.CHEST,
            EquipmentSlot.LEGS, EquipmentSlot.FEET};

    public static final ResourceLocation EMPTY_ARMOR_SLOT_MAIN = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "slot/main");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_SECOND = ResourceLocation.fromNamespaceAndPath(Athena.MODID,"slot/second");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_RING = ResourceLocation.fromNamespaceAndPath(Athena.MODID,"slot/ring");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_CAPE = ResourceLocation.fromNamespaceAndPath(Athena.MODID,"slot/cape");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_BELT = ResourceLocation.fromNamespaceAndPath(Athena.MODID,"slot/belt");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_FACE = ResourceLocation.fromNamespaceAndPath(Athena.MODID,"slot/face");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_NECK = ResourceLocation.fromNamespaceAndPath(Athena.MODID,"slot/neck");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_GLOVE = ResourceLocation.fromNamespaceAndPath(Athena.MODID,"slot/glove");
    public static final ResourceLocation EMPTY_ARMOR_SLOT_ORB = ResourceLocation.fromNamespaceAndPath(Athena.MODID,"slot/orb");

    private final ModContainer inventory;
    private final Player player;

    public Equipment_Menu(int containerId, Inventory _inventory) {
        super(GUI_Register.EQUIPMENT_MENU.get(), containerId);
        player = _inventory.player;
        inventory = new ModContainer(player);
        init();
    }

    public Equipment_Menu(int containerId, Inventory _inventory, Player _player) {
        super(GUI_Register.EQUIPMENT_MENU.get(), containerId);
        player = _player;
        inventory = new ModContainer(player);
        init();
    }

    private void init(){
        for(int i = 0; i < 4; ++i) {
            EquipmentSlot equipmentslot = SLOT_IDS[i];
            ResourceLocation resourcelocation = (ResourceLocation)TEXTURE_EMPTY_SLOTS.get(equipmentslot);
            this.addSlot(new ArmorSlot(inventory.playerInventory, player, equipmentslot, 39 - i, 8, 8 + i * 18, resourcelocation));
        }

        // custom equipment 12
        this.addSlot(new ModArmorSlot(inventory, player, ModEquipmentSlot.MAIN,0, 117, 62, EMPTY_ARMOR_SLOT_MAIN));
        this.addSlot(new ModArmorSlot(inventory, player, ModEquipmentSlot.SECONDARY, 1, 137, 62, EMPTY_ARMOR_SLOT_SECOND));

        this.addSlot(new ModArmorSlot(inventory, player, ModEquipmentSlot.RING0, 2, 77, 8, EMPTY_ARMOR_SLOT_RING));
        this.addSlot(new ModArmorSlot(inventory, player, ModEquipmentSlot.RING1, 3, 77, 8 + 18, EMPTY_ARMOR_SLOT_RING));
        this.addSlot(new ModArmorSlot(inventory, player, ModEquipmentSlot.RING2, 4, 77, 8 + 18 * 2, EMPTY_ARMOR_SLOT_RING));
        this.addSlot(new ModArmorSlot(inventory, player, ModEquipmentSlot.RING3, 5, 77, 8 + 18 * 3, EMPTY_ARMOR_SLOT_RING));

        this.addSlot(new ModArmorSlot(inventory, player, ModEquipmentSlot.FACE, 8, 97, 8, EMPTY_ARMOR_SLOT_FACE));
        this.addSlot(new ModArmorSlot(inventory, player, ModEquipmentSlot.NECK, 9, 97, 8 + 18, EMPTY_ARMOR_SLOT_NECK));
        this.addSlot(new ModArmorSlot(inventory, player, ModEquipmentSlot.BELT, 7, 97, 8 + 18 * 2, EMPTY_ARMOR_SLOT_BELT));
        this.addSlot(new ModArmorSlot(inventory, player, ModEquipmentSlot.CAPE, 6, 97, 8 + 18 * 3, EMPTY_ARMOR_SLOT_CAPE));

        this.addSlot(new ModArmorSlot(inventory, player, ModEquipmentSlot.ORB, 11, 117, 8, EMPTY_ARMOR_SLOT_ORB));
        this.addSlot(new ModArmorSlot(inventory, player, ModEquipmentSlot.GLOVE, 10, 117, 8 + 18, EMPTY_ARMOR_SLOT_GLOVE));

        addInventoryExtendedSlots(inventory.playerInventory, 8, 84); // 27
        addInventoryHotbarSlots(inventory.playerInventory, 8, 163); // 9
        for(int i = 0; i < 9; ++i) {
            this.addSlot(new RPGSlot(inventory, player, i + 12, 8 + i * 18, 144));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if(slot.hasItem()){
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            EquipmentSlot equipmentslot = player.getEquipmentSlotForItem(itemstack);
            boolean in_equip = index < 16;
            boolean in_inv = index >= 16 && index < 43;
            boolean in_hotbar = index >= 43 && index < 52;
            boolean in_rpg = index >= 43;
            if(in_equip){ // Click equipment
                if (!this.moveItemStackTo(itemstack1, 16, 52, false)) {
                    return ItemStack.EMPTY;
                }
            }
            else if(in_rpg && itemstack1.getItem() instanceof RPGSkill_Base skill){
                slots.get(index).set(ItemStack.EMPTY);
                return ItemStack.EMPTY;
            }
            else if(in_rpg && itemstack1.getItem() instanceof RPGPotion_Base potion){
                slots.get(index).set(ItemStack.EMPTY);
                if (!this.moveItemStackTo(itemstack1, 16, 52, false)) {
                    return ItemStack.EMPTY;
                }
            }
            else if(equipmentslot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR && !(this.slots.get(3 - equipmentslot.getIndex())).hasItem()){
                int i = 3 - equipmentslot.getIndex();
                if (!this.moveItemStackTo(itemstack1, i, i + 1, false)) {
                    return ItemStack.EMPTY;
                }
            }
            else if(itemstack1.getItem() instanceof SwordItem || itemstack1.getItem() instanceof BowItem){
                if (!this.moveItemStackTo(itemstack1, 4, 5, false)) {
                    return ItemStack.EMPTY;
                }
            }
            else if(itemstack1.getItem() instanceof ShieldItem){
                if (!this.moveItemStackTo(itemstack1, 5, 6, false)) {
                    return ItemStack.EMPTY;
                }
            }
            else if(itemstack1.getItem() instanceof RPGEquip_Base equip){
                for(int i = 4;  i < 16; i++){
                    if(slots.get(i).hasItem()) continue;
                    ModArmorSlot slotArmor = (ModArmorSlot) slots.get(i);
                    if(equip.canEquip(slotArmor.getType())){
                        if (!this.moveItemStackTo(itemstack1, i, i + 1, false)) {
                            return ItemStack.EMPTY;
                        }
                    }
                }
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY, itemstack);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
