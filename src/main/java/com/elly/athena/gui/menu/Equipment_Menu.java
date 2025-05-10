package com.elly.athena.gui.menu;

import com.elly.athena.Athena;
import com.elly.athena.data.types.ModContainer;
import com.elly.athena.data.types.ModEquipmentSlot;
import com.elly.athena.gui.GUI_Register;
import com.elly.athena.gui.menu.slot.ArmorSlot;
import com.elly.athena.gui.menu.slot.ModArmorSlot;
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
        for(int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(inventory, i + 12, 8 + i * 18, 142));
        }

        // custom equipment
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
