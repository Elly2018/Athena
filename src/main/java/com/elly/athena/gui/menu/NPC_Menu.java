package com.elly.athena.gui.menu;

import com.elly.athena.data.types.ModContainer;
import com.elly.athena.gui.GUI_Register;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class NPC_Menu extends AbstractContainerMenu {

    private final ModContainer inventory;
    private final Player player;

    public NPC_Menu(int containerId, Inventory _inventory) {
        super(GUI_Register.NPC_MENU.get(), containerId);
        player = _inventory.player;
        inventory = new ModContainer(player);
        init();
    }

    public NPC_Menu(int containerId, Inventory _inventory, Player _player) {
        super(GUI_Register.NPC_MENU.get(), containerId);
        player = _player;
        inventory = new ModContainer(player);
        init();
    }

    void init(){

    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return false;
    }
}
