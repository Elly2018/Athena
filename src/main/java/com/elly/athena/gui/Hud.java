package com.elly.athena.gui;

import com.elly.athena.gui.data.LootData;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;

import java.util.ArrayList;

import static com.elly.athena.gui.component.Effects.getEffects;
import static com.elly.athena.gui.component.ExpBar.getExperienceBar;
import static com.elly.athena.gui.component.HealthBar.getPlayerHealthBar;
import static com.elly.athena.gui.component.ManaBar.getManaValue;
import static com.elly.athena.gui.component.PickMessage.getPickupMessage;
import static com.elly.athena.gui.component.WidgeBase.getWidgetBase;

/**
 * Inspire by https://github.com/heria-zone/reignited-hud/blob/release/reignitedhud-forge-1.20.6/src/main/java/net/msymbios/reignitedhud/gui/GuiWidget.java
 */

public class Hud {

    public static final ResourceLocation TEX_HUD_BASE = ResourceLocation.fromNamespaceAndPath(com.elly.athena.Athena.MODID, "textures/gui/hud_base.png");
    public static final ResourceLocation TEX_HUD_BAR = ResourceLocation.fromNamespaceAndPath(com.elly.athena.Athena.MODID,"textures/gui/hud_bar.png");
    public static final ResourceLocation TEX_HUD_ICON = ResourceLocation.fromNamespaceAndPath(com.elly.athena.Athena.MODID,"textures/gui/hud_icon.png");
    public static final ResourceLocation TEX_HUD_EFFECT = ResourceLocation.fromNamespaceAndPath(com.elly.athena.Athena.MODID,"textures/gui/hud_effects.png");

    // Update by payload manager, server -> client to update the render data
    public static final int LOOTDATA_SIZE = 5;
    public static ArrayList<LootData> LootDatas = new ArrayList<>(LOOTDATA_SIZE);

    Minecraft minecraft = Minecraft.getInstance();

    public static void AddLoot(String name, int color, int amount){
        LootDatas.add(new LootData(name, color, amount, 30F));
        if(LootDatas.size() >= LOOTDATA_SIZE) LootDatas.removeFirst();
    }

    public void renderGUI(RenderGuiLayerEvent.Pre event){
        if(event.getName().toString().equals("minecraft:experience_bar")) event.setCanceled(true);
        if(event.getName().toString().equals("minecraft:experience_level")) event.setCanceled(true);
        if(event.getName().toString().equals("minecraft:food_level")) event.setCanceled(true);
        if(event.getName().toString().equals("minecraft:player_health")) event.setCanceled(true);
        if(event.getName().toString().equals("minecraft:air_level")) event.setCanceled(true);
        if(event.getName().toString().equals("minecraft:hotbar")){
            renderOverlay(event);
        }
    }

    public void renderOverlay(RenderGuiLayerEvent.Pre event){
        if (minecraft.player != null) {
            GlStateManager._clearColor(1.0F, 1.0F, 1.0F, 1.0F);
            getWidgetBase(minecraft.player, event.getGuiGraphics());
            getPlayerHealthBar(minecraft.player, event.getGuiGraphics());
            getManaValue(minecraft.player, event.getGuiGraphics());
            getExperienceBar(minecraft.player, event.getGuiGraphics());
            getEffects(minecraft.player, event.getGuiGraphics(), minecraft.screen);
            getPickupMessage(minecraft.player, event.getGuiGraphics(), event.getPartialTick().getGameTimeDeltaTicks());
        }
    }
}
