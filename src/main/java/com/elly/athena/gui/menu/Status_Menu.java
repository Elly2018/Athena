package com.elly.athena.gui.menu;

import com.elly.athena.gui.GUI_Register;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class Status_Menu extends AbstractContainerMenu {
    private final Inventory inventory;
    private final Player player;

    public Status_Menu(int containerId, Inventory _inventory, Player player) {
        super(GUI_Register.STATUS_MENU.get(), containerId);
        this.inventory = _inventory;
        this.player = player;
    }

    public Status_Menu(int containerId, Inventory _inventory) {
        super(GUI_Register.STATUS_MENU.get(), containerId);
        this.inventory = _inventory;
        this.player = null;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
