package com.elly.athena.gui.component;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffects;

import static com.elly.athena.gui.Hud.TEX_HUD_BAR;
import static com.elly.athena.gui.Utility.drawFontWithShadow;
import static com.elly.athena.gui.Utility.drawMediumBar;

public class HealthBar {
    public static void getPlayerHealthBar(LocalPlayer player, GuiGraphics gui) {
        float fill = Math.min(1.0F, player.getHealth() / player.getMaxHealth());

        int bar = 1;
        if (player.hasEffect(MobEffects.ABSORPTION)) bar = 2;
        if (player.hasEffect(MobEffects.REGENERATION)) bar = 3;
        if (player.hasEffect(MobEffects.DAMAGE_BOOST)) bar = 4;

        RenderSystem.setShaderTexture(0, TEX_HUD_BAR);
        drawMediumBar(TEX_HUD_BAR, gui, 48, 25, bar, fill);

        String hp_text = String.format("%d / %d", (int)player.getHealth(), (int)player.getMaxHealth());

        int color = 16227998;
        int shadow = 4854290;

        drawFontWithShadow(gui, hp_text, 80, 15, color, shadow);
    }
}
