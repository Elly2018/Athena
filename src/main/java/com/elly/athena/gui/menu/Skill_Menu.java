package com.elly.athena.gui.menu;

import com.elly.athena.gui.GUI_Register;
import com.elly.athena.system.BattleSystem;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class Skill_Menu extends AbstractContainerMenu {
    Player player;

    public Skill_Menu(int containerId, Inventory inventory) {
        super(GUI_Register.SKILL.get(), containerId);
        player = inventory.player;
    }

    protected Skill_Menu(@Nullable MenuType<?> typeInstance, int containerId, Inventory inventory) {
        super(typeInstance, containerId);
        player = inventory.player;
    }

    public static Skill_Menu defaultSkill(int containerId, Inventory inventory) {
        return new Skill_Menu(MenuType.GENERIC_9x1, containerId, inventory);
    }

    public BattleSystem.BattleSystemProvider getBattleSystem(){
        return new BattleSystem.BattleSystemProvider(player);
    }

    @Override
    public ItemStack quickMoveStack(Player p_38941_, int p_38942_) {
        return null;
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return false;
    }
}
