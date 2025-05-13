package com.elly.athena.gui.screen;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class PlayerInteract_Screen extends Screen {
    Player source;
    Player target;

    Button trade;

    public PlayerInteract_Screen(Player _source, Player _target) {
        super(Component.empty());
        this.source = _source;
        this.target = _target;
    }

    @Override
    protected void init() {
        super.init();
        int Init_X = (this.width - 56) / 2;
        int Init_Y = this.height / 3;
        trade = Button.builder(Component.translatable("player.interact.menu.trade"),
                (e) -> {TradeSubmit();})
                .size(56, 18)
                .pos(Init_X, Init_Y)
                .build();
        addRenderableWidget(trade);
    }

    private void TradeSubmit(){
        this.onClose();
    }
}
