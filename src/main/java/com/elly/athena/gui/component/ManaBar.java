package com.elly.athena.gui.component;

import com.elly.athena.data.Attribute_Register;
import com.elly.athena.event.ClientGameHandler;
import com.elly.athena.system.BattleSystem;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.profiling.Profiler;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

import static com.elly.athena.gui.Hud.TEX_HUD_BAR;
import static com.elly.athena.gui.RenderUtility.drawFontWithShadow;
import static com.elly.athena.gui.RenderUtility.drawMediumBar;

public class ManaBar {
    public static void getManaValue(LocalPlayer player, GuiGraphics gui) {
        Profiler.get().push("mana");
        AttributeMap map = player.getAttributes();
        int MP = (int)map.getValue(Attribute_Register.MANA);
        int MaxMP = (int)map.getValue(Attribute_Register.MANA_MAX);

        float fill = Math.min(1.0F, (float)MP / (float)MaxMP);

        RenderSystem.setShaderTexture(0, TEX_HUD_BAR);
        drawMediumBar(TEX_HUD_BAR, gui, 48, 45, 6, fill);

        String mana_text = String.format("%d / %d", MP, MaxMP);

        int color = 10862842;
        int shadow = 726832;

        drawFontWithShadow(gui, mana_text, 80, 35, color, shadow);
        Profiler.get().pop();
    }
}
