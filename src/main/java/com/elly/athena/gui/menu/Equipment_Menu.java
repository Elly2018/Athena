package com.elly.athena.gui.menu;

import com.elly.athena.data.types.ModContainer;
import com.elly.athena.data.types.ModEquipmentSlot;
import com.elly.athena.gui.GUI_Register;
import com.elly.athena.gui.menu.slot.ArmorSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

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
    private static final ModEquipmentSlot[] Mod_SLOT_IDS = new ModEquipmentSlot[]{
            ModEquipmentSlot.RING0, ModEquipmentSlot.RING1,
            ModEquipmentSlot.RING2, ModEquipmentSlot.RING3};

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
        for(int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(inventory, i + 12, 8 + i * 18, 142));
        }

        // custom equipment
        this.addSlot(new Slot(inventory, 0, 117, 62));
        this.addSlot(new Slot(inventory, 1, 137, 62));

        this.addSlot(new Slot(inventory, 2, 77, 8));
        this.addSlot(new Slot(inventory, 3, 77, 8 + 18));
        this.addSlot(new Slot(inventory, 4, 77, 8 + 18 * 2));
        this.addSlot(new Slot(inventory, 5, 77, 8 + 18 * 3));

        this.addSlot(new Slot(inventory, 8, 97, 8));
        this.addSlot(new Slot(inventory, 9, 97, 8 + 18));
        this.addSlot(new Slot(inventory, 7, 97, 8 + 18 * 2));
        this.addSlot(new Slot(inventory, 6, 97, 8 + 18 * 3));

        this.addSlot(new Slot(inventory, 11, 117, 8));
        this.addSlot(new Slot(inventory, 10, 117, 8 + 18));

        addInventoryExtendedSlots(inventory.playerInventory, 8, 84);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        //return inventory.playerInventory.;
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
