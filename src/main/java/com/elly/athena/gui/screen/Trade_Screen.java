package com.elly.athena.gui.screen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class Trade_Screen extends Screen {

    private final Player player;
    private final Player target;

    public Trade_Screen(Player _player, Player _target) {
        super(Component.empty());
        this.player = _player;
        this.target = _target;
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
