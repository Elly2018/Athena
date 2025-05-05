package com.elly.athena.gui.component;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.Collection;

public class Effects {
    public static void getEffects(LocalPlayer player, GuiGraphics gui, Screen screen){
        Collection<MobEffectInstance> collection = player.getActiveEffects();
        if (!collection.isEmpty() && (screen == null || !screen.showsActiveEffects())) {

        }
    }
}
