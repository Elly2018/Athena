package com.elly.athena.gui;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.IPlayerStatus;
import com.elly.athena.data.interfaceType.status.IExp;
import com.elly.athena.data.interfaceType.status.ILevel;
import com.elly.athena.data.interfaceType.status.IMana;
import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.Profiler;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.neoforge.client.event.CustomizeGuiOverlayEvent;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;

import java.awt.*;

/**
 * Inspire by https://github.com/heria-zone/reignited-hud/blob/release/reignitedhud-forge-1.20.6/src/main/java/net/msymbios/reignitedhud/gui/GuiWidget.java
 */

public class Hud {

    public static final ResourceLocation TEX_HUD_BASE = ResourceLocation.fromNamespaceAndPath(com.elly.athena.Athena.MODID, "textures/gui/hud_base.png");
    public static final ResourceLocation TEX_HUD_BAR = ResourceLocation.fromNamespaceAndPath(com.elly.athena.Athena.MODID,"textures/gui/hud_bar.png");
    public static final ResourceLocation TEX_HUD_ICON = ResourceLocation.fromNamespaceAndPath(com.elly.athena.Athena.MODID,"textures/gui/hud_icon.png");
    public static final ResourceLocation TEX_HUD_EFFECT = ResourceLocation.fromNamespaceAndPath(com.elly.athena.Athena.MODID,"textures/gui/hud_effects.png");

    private static final ResourceLocation EXPERIENCE_BAR_BACKGROUND_SPRITE = ResourceLocation.withDefaultNamespace("hud/experience_bar_background");
    private static final ResourceLocation EXPERIENCE_BAR_PROGRESS_SPRITE = ResourceLocation.withDefaultNamespace("hud/experience_bar_progress");

    Minecraft minecraft = Minecraft.getInstance();

    public void renderGUI(RenderGuiLayerEvent.Pre event){
        if(event.getName().toString().equals("minecraft:experience_bar")) event.setCanceled(true);
        if(event.getName().toString().equals("minecraft:experience_level")) event.setCanceled(true);
        if(event.getName().toString().equals("minecraft:food_level")) event.setCanceled(true);
        if(event.getName().toString().equals("minecraft:player_health")) event.setCanceled(true);
        if(event.getName().toString().equals("minecraft:air_level")) event.setCanceled(true);
    }

    public void renderOverlay(CustomizeGuiOverlayEvent.Chat event){
        Window scaled = minecraft.getWindow();

        if (this.minecraft.player != null) {
            event.getGuiGraphics().flush();

            GlStateManager._clearColor(1.0F, 1.0F, 1.0F, 1.0F);

            this.getWidgetBase(this.minecraft.player, event);
            this.getPlayerHealthBar(this.minecraft.player, event);
            this.getManaValue(this.minecraft.player, event);
            this.renderExperienceBar(this.minecraft.player, event);
        }
    }

    private void getWidgetBase(LocalPlayer player, CustomizeGuiOverlayEvent event) {
        // Get the player's game profile
        GameProfile profile = player.getGameProfile();

        IPlayerStatus status = player.getData(Attachment_Register.PLAYER_STATUS);

        // Initialize the player's skin with the default skin
        ResourceLocation playerSkin = DefaultPlayerSkin.getDefaultTexture();

        // Check if the player's profile is not null
        if (!profile.equals((Object)null)) {
            // Get the player's skin information
            PlayerSkin map = this.minecraft.getSkinManager().getInsecureSkin(profile);

            // Check if the skin map contains the player's skin
            if (map.texture() != null) {
                // Update the player's skin with the retrieved skin
                playerSkin = map.texture();
            }
        }

        // Render HUD elements

        // Render HUD bar
        //RenderSystem.setShaderTexture(0, ReignitedHudID.TEX_HUD_BAR);
        event.getGuiGraphics().blit(RenderType::guiTextured, TEX_HUD_BAR, 13, 13, 227, 0, 5, 25, 256, 256);
        //event.getGuiGraphics().blit(TEX_HUD_BAR, 13, 13, 227, 0, 5, 25);

        // Render dynamic HUD element based on player's experience progress
        event.getGuiGraphics().blit(RenderType::guiTextured, TEX_HUD_BAR, 14, 14, 223, 1, 3, 23 - (int)(status.getExpProgress(status.getLevel())  * 23.0F), 256, 256);

        // Render HUD base
        //RenderSystem.setShaderTexture(0, ReignitedHudID.TEX_HUD_BASE);
        event.getGuiGraphics().blit(RenderType::guiTextured, TEX_HUD_BASE, 15, 11, 0, 0, 29, 29, 256, 256);

        // Render player's name on the HUD
        drawFontWithShadow(event, player.getName().getString(), 48, 13, 16777215);

        // Bind player's skin texture and render player icon on HUD
        RenderSystem.setShaderTexture(0, playerSkin);
        drawPlayerIcon(21, 17, 17);

        // Display the player's experience level
        String enchantedPoints = String.valueOf(status.getLevel());
        drawFontBoldCentered(event, enchantedPoints, 30, 35, 13172623, 2957570);

        // Check if the game level exists and is set to hard difficulty
        if(this.minecraft.level != null) {
            if (this.minecraft.level.getDifficulty() == Difficulty.HARD) {
                // Display a specific icon for hard difficulty
                //RenderSystem.setShaderTexture(0, ReignitedHudID.TEX_HUD_ICON);
                drawIcon(TEX_HUD_ICON, event, 25, 11, 4, 2);
            }
        }
    }

    private void getPlayerHealthBar(LocalPlayer player, CustomizeGuiOverlayEvent event) {
        float fill = Math.min(1.0F, player.getHealth() / player.getMaxHealth());

        int bar = 1;
        if (player.hasEffect(MobEffects.ABSORPTION)) bar = 2;
        if (player.hasEffect(MobEffects.REGENERATION)) bar = 3;
        if (player.hasEffect(MobEffects.DAMAGE_BOOST)) bar = 4;

        RenderSystem.setShaderTexture(0, TEX_HUD_BAR);
        drawMediumBar(TEX_HUD_BAR, event, 48, 25, bar, fill);

        String hp_text = String.format("%d / %d", (int)player.getHealth(), (int)player.getMaxHealth());

        int color = 16227998;
        int shadow = 4854290;

        drawFontWithShadow(event, hp_text, 80, 15, color, shadow);
    }

    private void getManaValue(LocalPlayer player, CustomizeGuiOverlayEvent event) {
        Profiler.get().push("mana");
        IMana mana = player.getData(Attachment_Register.PLAYER_STATUS);

        float fill = Math.min(1.0F, (float)mana.getMana() / (float)mana.getManaMaximum());

        RenderSystem.setShaderTexture(0, TEX_HUD_BAR);
        drawMediumBar(TEX_HUD_BAR, event, 48, 45, 6, fill);

        String mana_text = String.format("%d / %d", mana.getMana(), mana.getManaMaximum());

        int color = 10862842;
        int shadow = 726832;

        drawFontWithShadow(event, mana_text, 80, 35, color, shadow);
        Profiler.get().pop();
    }

    private void renderExperienceBar(LocalPlayer player, CustomizeGuiOverlayEvent event) {
        Profiler.get().push("expBar");
        IPlayerStatus status = player.getData(Attachment_Register.PLAYER_STATUS);
        int x = event.getGuiGraphics().guiWidth() / 2 - 91;
        int k = (int)(status.getExpProgress(status.getLevel()) * 183.0F);
        int l = event.getGuiGraphics().guiHeight() - 32 + 3;
        Athena.LOGGER.info(String.format("exp ratio: %.2f %d", status.getExpProgress(status.getLevel()), k));
        event.getGuiGraphics().blitSprite(RenderType::guiTextured, EXPERIENCE_BAR_BACKGROUND_SPRITE, x, l, 182, 5);
        event.getGuiGraphics().blitSprite(RenderType::guiTextured, EXPERIENCE_BAR_PROGRESS_SPRITE, 182, 5, 0, 0, x, l, k, 5);
        Profiler.get().pop();
    }

    private void drawMediumBar(ResourceLocation icon, CustomizeGuiOverlayEvent event, int posX, int posY, int bar, float fill) {
        int barNumber = bar * 10 - 10;
        int barNumberBG = bar * 10 - 4;

        event.getGuiGraphics().blit(RenderType::guiTextured, icon, posX, posY, 0, barNumber, 91, 5, 255, 255);
        event.getGuiGraphics().blit(RenderType::guiTextured, icon, posX + 1, posY + 1, 1, barNumberBG, (int)(fill * 89.0F), 3, 255, 255);
    }

    private void drawFont(CustomizeGuiOverlayEvent event, String string, int posX, int posY, int color) {
        // Get the font renderer from the Minecraft instance
        Font font = Minecraft.getInstance().font;
        // Draw the string at the specified position with the specified color
        event.getGuiGraphics().drawString(font, string, posX, posY, color);
        // Reset the blend color
        GlStateManager._clearColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void drawFontWithShadow(CustomizeGuiOverlayEvent event, String string, int posX, int posY, int color, int shadow) {
        // Create a new pose stack
        PoseStack pose = new PoseStack();
        // Get the font renderer instance
        Font font = Minecraft.getInstance().font;
        // Draw the shadow of the string at an offset position
        event.getGuiGraphics().drawString(font, string, (float)(posX + 1), (float)(posY + 1), shadow, false);
        // Draw the main text at the specified position
        event.getGuiGraphics().drawString(font, string, (float)posX, (float)posY, color, false);
        // Reset the blend color
        GlStateManager._clearColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void drawFontWithShadow(CustomizeGuiOverlayEvent event, String text, int posX, int posY, int color) {
        // Get the font renderer instance
        Font font = Minecraft.getInstance().font;
        // Draw the text with shadow using the specified parameters
        event.getGuiGraphics().drawString(font, text, (float)posX, (float)posY, color, true);
        // Reset the blend color
        GlStateManager._clearColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void drawPlayerIcon(int posX, int posY, int size) {
        // Draw the player icon with custom sized textures
        drawCustomSizedTexture(posX, posY, (float)size, (float)size, size, size, (float)(size * 8), (float)(size * 8));
        drawCustomSizedTexture(posX, posY, (float)(size * 5), (float)size, size, size, (float)(size * 8), (float)(size * 8));
    }

    private void drawIcon(ResourceLocation icon, CustomizeGuiOverlayEvent event, int posX, int posY, int row, int pos) {
        event.getGuiGraphics().blit(RenderType::guiTextured, icon, posX, posY, pos * 10 - 10, row * 10 - 10, 10, 10, 256, 256);
    }

    private static void drawCustomSizedTexture(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight) {
        // Get the tessellator instance
        Tesselator tesselator = RenderSystem.renderThreadTesselator();
        // Calculate the texture coordinate factors
        float f = 1.0F / textureWidth;
        float f1 = 1.0F / textureHeight;

        // Enable blending for transparency
        GlStateManager._enableBlend();
        // Set the blend function
        GlStateManager._blendFunc(GlStateManager.SourceFactor.SRC_ALPHA.value, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA.value); // Set blend function    // Start drawing the texture

        // Start drawing the texture
        // Get the buffer builder from the tessellator
        BufferBuilder bufferbuilder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.addVertex(x, y + height, -1000.0F).setUv(u * f, (v + (float)height) * f1);
        bufferbuilder.addVertex(x + width, y + height, -1000.0F).setUv((u + (float)width) * f, (v + (float)height) * f1);
        bufferbuilder.addVertex((x + width), (y), -1000.0F).setUv((u + (float)width) * f, v * f1);
        bufferbuilder.addVertex((x), (y), -1000.0F).setUv(u * f, v * f1);
        bufferbuilder.build();
    }

    private void drawFontBoldCentered(CustomizeGuiOverlayEvent event, String text, int posX, int posY, int color, int shadow) {
        // Calculate the new x-coordinate to center the text
        int newX = posX - getStringWidth(text) / 2;
        // Draw the text with bold font at the new x-coordinate and specified y-coordinate
        drawFontBold(event, text, newX, posY, color, shadow);
    }

    private int getStringWidth(String string) {
        Font fontrenderer = Minecraft.getInstance().font;
        return fontrenderer.width(string);
    }

    private void drawFontBold(CustomizeGuiOverlayEvent event, String text, int posX, int posY, int color, int shadow) {
        // Get the font renderer instance
        Font font = Minecraft.getInstance().font;

        // Draw shadows around the text
        event.getGuiGraphics().drawString(font, text, (float)(posX + 1), (float)posY, shadow, false);
        event.getGuiGraphics().drawString(font, text, (float)(posX - 1), (float)posY, shadow, false);
        event.getGuiGraphics().drawString(font, text, (float)posX, (float)(posY + 1), shadow, false);
        event.getGuiGraphics().drawString(font, text, (float)posX, (float)(posY - 1), shadow, false);

        // Draw the main text
        event.getGuiGraphics().drawString(font, text, (float)posX, (float)posY, color, false);

        // Reset the blend color
        GlStateManager._clearColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
