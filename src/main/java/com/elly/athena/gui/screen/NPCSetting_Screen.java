package com.elly.athena.gui.screen;

import com.elly.athena.Athena;
import com.elly.athena.gui.screen.utility.Page;
import com.elly.athena.gui.screen.utility.ScreenManager;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.NotNull;

public class NPCSetting_Screen extends AbstractContainerScreen {
    private static final ResourceLocation MAIN_BACKGROUND = ResourceLocation.fromNamespaceAndPath(Athena.MODID,"textures/gui/container/npc_main.png");
    private static final ResourceLocation SHOP_BACKGROUND = ResourceLocation.fromNamespaceAndPath(Athena.MODID,"textures/gui/container/npc_shop.png");
    private static final ResourceLocation DIALOG_BACKGROUND = ResourceLocation.fromNamespaceAndPath(Athena.MODID,"textures/gui/container/npc_dialog.png");
    private ScreenManager manager;
    private Page main_menu;
    private Page shop_setting;
    private Page dialog_setting;
    LivingEntity NPC;

    public NPCSetting_Screen(AbstractContainerMenu menu, Inventory playerInventory, LivingEntity NPC) {
        super(menu, playerInventory, Component.empty());
        this.NPC = NPC;
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) { }

    @Override
    protected void init() {
        super.init();
        main_menu = new Page(MAIN_BACKGROUND, 252, 199);
        shop_setting = new Page(SHOP_BACKGROUND, 0, 0);
        dialog_setting = new Page(DIALOG_BACKGROUND, 0, 0);
        manager.Add(main_menu);
        manager.Add(shop_setting);
        manager.Add(dialog_setting);

        main_menu.AddRenderCall(this::DrawNPC);
        Button to_shop = Button.builder(Component.translatable("menu.npc_setting.to_shop"), (b) -> {
            manager.Active(shop_setting);
        }).pos(11, 23).size(74, 15).build();
        Button to_dialog = Button.builder(Component.translatable("menu.npc_setting.to_dialog"), (b) -> {
            manager.Active(dialog_setting);
        }).pos(30, 23).size(74, 15).build();

        this.addRenderableWidget(to_shop);
        this.addRenderableWidget(to_dialog);
        main_menu.Add(to_shop);
        main_menu.Add(to_dialog);
    }

    @Override
    protected void containerTick() {
        super.containerTick();
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics guiGraphics, float tick, int mouseX, int mouseY) {
        manager.RenderBackground(guiGraphics, this.width, this.height, mouseX, mouseY);
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int x, int y, float tick) {
        super.render(guiGraphics, x, y, tick);
        manager.render(guiGraphics, x, y, tick);
    }

    public void DrawNPC(GuiGraphics graphics){
        InventoryScreen.renderEntityInInventoryFollowsMouse(graphics, 92, 23, 236, 183, 30, 0.0625F, manager.mouseX, manager.mouseY, NPC);
    }
}
