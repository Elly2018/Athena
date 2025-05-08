package com.elly.athena.gui.menu;

import com.elly.athena.gui.GUI_Register;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class Skill_Menu extends AbstractContainerMenu {
    private final Inventory inventory;
    private final Player player;

    public Skill_Menu(int containerId, Inventory _inventory) {
        super(GUI_Register.SKILL_MENU.get(), containerId);
        this.inventory = _inventory;
        this.player = _inventory.player;
        init();
    }

    public Skill_Menu(int containerId, Inventory _inventory, Player player) {
        super(GUI_Register.SKILL_MENU.get(), containerId);
        this.inventory = _inventory;
        this.player = player;
        init();
    }

    private void init(){
        this.addInventoryHotbarSlots(inventory, 8, 84 + 58);
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
