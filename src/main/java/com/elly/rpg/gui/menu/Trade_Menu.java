package com.elly.rpg.gui.menu;

import com.elly.rpg.gui.GUI_Register;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class Trade_Menu extends AbstractContainerMenu {
    private Inventory Sender;
    private Inventory Receiver;

    public Trade_Menu(int containerId, Inventory sender) {
        super(GUI_Register.SKILL.get(), containerId);
        Sender = sender;
    }

    protected Trade_Menu(@Nullable MenuType<?> p_38851_, int containerId, Inventory sender) {
        super(p_38851_, containerId);
        Sender = sender;
    }

    public static Trade_Menu defaultTrande(int containerId, Inventory sender){
        return new Trade_Menu(containerId, sender);
    }

    public void SetReceiver(Inventory receiver){
        Receiver = receiver;
    }

    @Override
    public ItemStack quickMoveStack(Player p_38941_, int p_38942_) {
        return null;
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return false;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
