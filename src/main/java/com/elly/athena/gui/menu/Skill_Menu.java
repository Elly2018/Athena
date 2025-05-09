package com.elly.athena.gui.menu;

import com.elly.athena.data.types.ModContainer;
import com.elly.athena.gui.GUI_Register;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class Skill_Menu extends AbstractContainerMenu {
    private final ModContainer inventory;
    private final Player player;

    private Container SlotList;

    public Skill_Menu(int containerId, Inventory _inventory) {
        super(GUI_Register.SKILL_MENU.get(), containerId);
        player = _inventory.player;
        inventory = new ModContainer(player);
        init();
    }

    public Skill_Menu(int containerId, Inventory _inventory, Player _player) {
        super(GUI_Register.SKILL_MENU.get(), containerId);
        player = _player;
        inventory = new ModContainer(player);
        init();
    }

    public void ChangeState(int selected, int page){

    }

    private void init(){
        for(int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(inventory, i + 12, 8 + i * 18, 84 + 58));
        }

        SlotList = new SimpleContainer(5);
        for(int i = 0; i < 5; ++i){
            this.addSlot(new Slot(inventory, i, 8, 18 + i * 19));
        }
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
