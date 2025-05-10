package com.elly.athena.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class RenderUtility {
    public static void drawMediumBar(ResourceLocation icon, GuiGraphics gui, int posX, int posY, int bar, float fill) {
        int barNumber = bar * 10 - 10;
        int barNumberBG = bar * 10 - 4;

        gui.blit(RenderType::guiTextured, icon, posX, posY, 0, barNumber, 91, 5, 255, 255);
        gui.blit(RenderType::guiTextured, icon, posX + 1, posY + 1, 1, barNumberBG, (int)(fill * 89.0F), 3, 255, 255);
    }

    public static void drawFont(GuiGraphics gui, String string, int posX, int posY, int color) {
        // Get the font renderer from the Minecraft instance
        Font font = Minecraft.getInstance().font;
        // Draw the string at the specified position with the specified color
        gui.drawString(font, string, posX, posY, color, false);
        // Reset the blend color
        GlStateManager._clearColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void drawFontWithShadow(GuiGraphics gui, String string, int posX, int posY, int color, int shadow) {
        // Create a new pose stack
        PoseStack pose = new PoseStack();
        // Get the font renderer instance
        Font font = Minecraft.getInstance().font;
        // Draw the shadow of the string at an offset position
        gui.drawString(font, string, (float)(posX + 1), (float)(posY + 1), shadow, false);
        // Draw the main text at the specified position
        gui.drawString(font, string, (float)posX, (float)posY, color, false);
        // Reset the blend color
        GlStateManager._clearColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void drawFontWithShadow(GuiGraphics gui, String text, int posX, int posY, int color) {
        // Get the font renderer instance
        Font font = Minecraft.getInstance().font;
        // Draw the text with shadow using the specified parameters
        gui.drawString(font, text, (float)posX, (float)posY, color, true);
        // Reset the blend color
        GlStateManager._clearColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void drawPlayerIcon(int posX, int posY, int size) {
        // Draw the player icon with custom sized textures
        drawCustomSizedTexture(posX, posY, (float)size, (float)size, size, size, (float)(size * 8), (float)(size * 8));
        drawCustomSizedTexture(posX, posY, (float)(size * 5), (float)size, size, size, (float)(size * 8), (float)(size * 8));
    }

    public static void drawIcon(ResourceLocation icon, GuiGraphics gui, int posX, int posY, int row, int pos) {
        gui.blit(RenderType::guiTextured, icon, posX, posY, pos * 10 - 10, row * 10 - 10, 10, 10, 256, 256);
    }

    public static void drawCustomSizedTexture(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight) {
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
        BufferUploader.drawWithShader(bufferbuilder.buildOrThrow());
    }

    public static void drawFontBoldCentered(GuiGraphics gui, String text, int posX, int posY, int color, int shadow) {
        // Calculate the new x-coordinate to center the text
        int newX = posX - getStringWidth(text) / 2;
        // Draw the text with bold font at the new x-coordinate and specified y-coordinate
        drawFontBold(gui, text, newX, posY, color, shadow);
    }

    public static int getStringWidth(String string) {
        Font fontrenderer = Minecraft.getInstance().font;
        return fontrenderer.width(string);
    }

    public static void drawFontBold(GuiGraphics gui, String text, int posX, int posY, int color, int shadow) {
        // Get the font renderer instance
        Font font = Minecraft.getInstance().font;

        // Draw shadows around the text
        gui.drawString(font, text, (float)(posX + 1), (float)posY, shadow, false);
        gui.drawString(font, text, (float)(posX - 1), (float)posY, shadow, false);
        gui.drawString(font, text, (float)posX, (float)(posY + 1), shadow, false);
        gui.drawString(font, text, (float)posX, (float)(posY - 1), shadow, false);

        // Draw the main text
        gui.drawString(font, text, (float)posX, (float)posY, color, false);

        // Reset the blend color
        GlStateManager._clearColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
