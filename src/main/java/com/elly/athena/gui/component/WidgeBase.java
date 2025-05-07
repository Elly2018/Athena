package com.elly.athena.gui.component;

import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.IPlayerStatus;
import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Difficulty;

import static com.elly.athena.gui.Hud.*;
import static com.elly.athena.gui.Utility.*;

public class WidgeBase {
    public static void getWidgetBase(LocalPlayer player, GuiGraphics gui) {
        GameProfile profile = player.getGameProfile();
        IPlayerStatus status = player.getData(Attachment_Register.PLAYER_STATUS);
        PlayerSkin map = Minecraft.getInstance().getSkinManager().getInsecureSkin(profile);
        ResourceLocation playerSkin = map.texture();

        //RenderSystem.setShaderTexture(0, ReignitedHudID.TEX_HUD_BAR);
        gui.blit(RenderType::guiTextured, TEX_HUD_BAR, 13, 13, 227, 0, 5, 25, 256, 256);
        //gui.blit(TEX_HUD_BAR, 13, 13, 227, 0, 5, 25);

        // Render dynamic HUD element based on player's experience progress
        gui.blit(RenderType::guiTextured, TEX_HUD_BAR, 14, 14, 223, 1, 3, 23 - (int)(status.getExpProgress(status.getLevel())  * 23.0F), 256, 256);

        // Render HUD base
        //RenderSystem.setShaderTexture(0, ReignitedHudID.TEX_HUD_BASE);
        gui.blit(RenderType::guiTextured, TEX_HUD_BASE, 15, 11, 0, 0, 29, 29, 256, 256);

        // Render player's name on the HUD
        drawFontWithShadow(gui, player.getName().getString(), 48, 13, 16777215);

        // Bind player's skin texture and render player icon on HUD
        RenderSystem.setShaderTexture(0, playerSkin);
        drawPlayerIcon(21, 17, 17);

        // Display the player's experience level
        String enchantedPoints = String.valueOf(status.getLevel());
        drawFontBoldCentered(gui, enchantedPoints, 30, 35, 13172623, 2957570);

        // Check if the game level exists and is set to hard difficulty

        if (Difficulty.HARD == player.level().getDifficulty()) {
            // Display a specific icon for hard difficulty
            //RenderSystem.setShaderTexture(0, ReignitedHudID.TEX_HUD_ICON);
            drawIcon(TEX_HUD_ICON, gui, 25, 11, 4, 2);
        }
    }
}
