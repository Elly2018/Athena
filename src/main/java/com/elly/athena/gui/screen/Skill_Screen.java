package com.elly.athena.gui.screen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class Skill_Screen extends Screen {

    private final Player player;

    public Skill_Screen(Player _player) {
        super(Component.empty());
        this.player = _player;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void onClose() {
        super.onClose();
    }
}
