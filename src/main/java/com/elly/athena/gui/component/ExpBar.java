package com.elly.athena.gui.component;

import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.attachment.IPlayerStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.Profiler;

import static com.elly.athena.gui.RenderUtility.drawFont;

public class ExpBar {

    private static final ResourceLocation EXPERIENCE_BAR_BACKGROUND_SPRITE = ResourceLocation.withDefaultNamespace("hud/experience_bar_background");
    private static final ResourceLocation EXPERIENCE_BAR_PROGRESS_SPRITE = ResourceLocation.withDefaultNamespace("hud/experience_bar_progress");

    public static void getExperienceBar(LocalPlayer player, GuiGraphics gui) {
        Profiler.get().push("a_expBar");
        IPlayerStatus status = player.getData(Attachment_Register.PLAYER_STATUS);
        int x = gui.guiWidth() / 2 - 91;
        int k = (int)(status.getExpProgress(status.getLevel()) * 183.0F);
        int l = gui.guiHeight() - 32 + 3;
        gui.blitSprite(RenderType::guiTextured, EXPERIENCE_BAR_BACKGROUND_SPRITE, x, l, 182, 5);
        gui.blitSprite(RenderType::guiTextured, EXPERIENCE_BAR_PROGRESS_SPRITE, 182, 5, 0, 0, x, l, k, 5);

        Font font = Minecraft.getInstance().font;
        String text = String.format("%d / %d", status.getExp(), status.getExpMaximum(status.getLevel()));
        int pos_x = (gui.guiWidth() - font.width(text)) / 2;
        int pos_y = gui.guiHeight() - 31 - 4;
        drawFont(gui, text, pos_x, pos_y, 8453920);
        Profiler.get().pop();
    }
}
