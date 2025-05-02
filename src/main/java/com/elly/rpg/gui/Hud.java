package com.elly.rpg.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;

public class Hud {

    Minecraft minecraft = Minecraft.getInstance();

    public void renderOverlay(CustomizeGuiOverlayEvent event){
        LocalPlayer player = this.minecraft.player;
        Window scaled = minecraft.getWindow();
        if (player == null) return;

        if (this.minecraft.player != null) {
            GlStateManager._clearColor(1.0F, 1.0F, 1.0F, 1.0F);
            this.getPlayerHealthBar(player, event);
        }
    }

    private void getPlayerHealthBar(LocalPlayer player, CustomizeGuiOverlayEvent event) {
        // Calculate the fill amount of current health to max health
        float fill = Math.min(1.0F, player.getHealth() / player.getMaxHealth());

        // Determine the type of health bar based on player effects
        int bar = 1;
        if (player.hasEffect(MobEffects.ABSORPTION)) bar = 2;
        if (player.hasEffect(MobEffects.REGENERATION)) bar = 3;
        if (player.hasEffect(MobEffects.DAMAGE_BOOST)) bar = 4;

        // Bind the health bar texture and render the bar
        //RenderSystem.setShaderTexture(0, ReignitedHudID.TEX_HUD_BAR);
        RenderDrawCallback.drawMediumBar(ReignitedHudID.TEX_HUD_BAR, event, 48, 24, bar, fill);
    }
}
