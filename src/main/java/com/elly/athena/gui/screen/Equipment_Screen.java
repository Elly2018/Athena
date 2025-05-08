package com.elly.athena.gui.screen;

import com.elly.athena.Athena;
import com.elly.athena.gui.menu.Equipment_Menu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

public class Equipment_Screen extends AbstractContainerScreen<Equipment_Menu> {

    private static final ResourceLocation CONTAINER_BACKGROUND = ResourceLocation.fromNamespaceAndPath(Athena.MODID,"textures/gui/container/equipment.png");

    private static final WidgetSprites PLUS_BUTTON_SPRITES = new WidgetSprites(ResourceLocation.fromNamespaceAndPath(Athena.MODID,"plus_button"),
            ResourceLocation.fromNamespaceAndPath(Athena.MODID,"plus_button_darker"));
    private static final WidgetSprites MINUS_BUTTON_SPRITES = new WidgetSprites(ResourceLocation.fromNamespaceAndPath(Athena.MODID,"minus_button"),
            ResourceLocation.fromNamespaceAndPath(Athena.MODID,"minus_button_darker"));

    private final Player player;
    protected int offsetWidth;
    protected int offsetHeight;

    public Equipment_Screen(Equipment_Menu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        player = playerInventory.player;
    }

    @Override
    protected void init() {
        super.init();
        offsetWidth = (this.width - this.imageWidth) / 2; // 40
        offsetHeight = (this.height - this.imageHeight) / 2; // 45
    }

    @Override
    public void render(GuiGraphics guiGraphics, int xMouse, int yMouse, float tick) {
        super.render(guiGraphics, xMouse, yMouse, tick);
        InventoryScreen.renderEntityInInventoryFollowsMouse(guiGraphics,
                offsetWidth + 26, offsetHeight + 8, offsetWidth + 75, offsetHeight + 78, 30, 0.0625F,
                xMouse, yMouse, player);
        this.renderTooltip(guiGraphics, xMouse, yMouse);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        guiGraphics.blit(RenderType::guiTextured, CONTAINER_BACKGROUND,
                offsetWidth, offsetHeight, 0, 0, imageWidth, imageHeight, 256, 256);
    }

    private void RenderItemStack(GuiGraphics guiGraphics){

    }
}
