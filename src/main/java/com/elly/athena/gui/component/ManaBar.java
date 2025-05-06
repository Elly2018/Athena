package com.elly.athena.gui.component;

import com.elly.athena.data.interfaceType.status.IMana;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.profiling.Profiler;

import static com.elly.athena.ClientGameHandler.LocalPlayerStatus;
import static com.elly.athena.gui.Hud.TEX_HUD_BAR;
import static com.elly.athena.gui.Utility.drawFontWithShadow;
import static com.elly.athena.gui.Utility.drawMediumBar;

public class ManaBar {
    public static void getManaValue(LocalPlayer player, GuiGraphics gui) {
        Profiler.get().push("mana");
        IMana mana = LocalPlayerStatus;

        float fill = Math.min(1.0F, (float)mana.getMana() / (float)mana.getManaMaximum());

        RenderSystem.setShaderTexture(0, TEX_HUD_BAR);
        drawMediumBar(TEX_HUD_BAR, gui, 48, 45, 6, fill);

        String mana_text = String.format("%d / %d", mana.getMana(), mana.getManaMaximum());

        int color = 10862842;
        int shadow = 726832;

        drawFontWithShadow(gui, mana_text, 80, 35, color, shadow);
        Profiler.get().pop();
    }
}
