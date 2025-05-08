package com.elly.athena.gui.screen;

import com.elly.athena.Athena;
import com.elly.athena.gui.menu.Skill_Menu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

public class Skill_Screen extends AbstractContainerScreen<Skill_Menu> {

    private static final ResourceLocation CONTAINER_BACKGROUND = ResourceLocation.fromNamespaceAndPath(Athena.MODID,"textures/gui/container/skill.png");

    private static final WidgetSprites PLUS_BUTTON_SPRITES = new WidgetSprites(ResourceLocation.fromNamespaceAndPath(Athena.MODID,"plus_button"),
            ResourceLocation.fromNamespaceAndPath(Athena.MODID,"plus_button_darker"));
    private static final WidgetSprites MINUS_BUTTON_SPRITES = new WidgetSprites(ResourceLocation.fromNamespaceAndPath(Athena.MODID,"minus_button"),
            ResourceLocation.fromNamespaceAndPath(Athena.MODID,"minus_button_darker"));

    private final Player player;

    public Skill_Screen(Skill_Menu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        player = playerInventory.player;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {

    }

    @Override
    public void onClose() {
        super.onClose();
    }
}
