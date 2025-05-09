package com.elly.athena.gui.component;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.IBattleHotbar;
import com.elly.athena.data.interfaceType.IPlayerEquipment;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class RPGHotbar {
    private static final ResourceLocation HOTBAR_OFFHAND_LEFT_SPRITE = ResourceLocation.withDefaultNamespace("hud/hotbar_offhand_left");
    private static final ResourceLocation HOTBAR_OFFHAND_RIGHT_SPRITE = ResourceLocation.withDefaultNamespace("hud/hotbar_offhand_right");
    private static final ResourceLocation HOTBAR_SPRITE = ResourceLocation.withDefaultNamespace("hud/hotbar");
    private static final ResourceLocation HOTBAR_SELECTION_SPRITE = ResourceLocation.withDefaultNamespace("hud/hotbar_selection");

    public static void getRPGHotBar(LocalPlayer player, GuiGraphics gui, DeltaTracker deltaTracker){
        render_background(player, gui);
        render_items(player, gui, deltaTracker);
    }

    private static void render_background(LocalPlayer player, GuiGraphics gui){
        gui.pose().pushPose();
        gui.pose().translate(0.0F, 0.0F, -90.0F);

        int half_width = gui.guiWidth() / 2;
        gui.blitSprite(RenderType::guiTextured, HOTBAR_OFFHAND_LEFT_SPRITE, half_width - 91 - 29, gui.guiHeight() - 23, 29, 24);
        gui.blitSprite(RenderType::guiTextured, HOTBAR_OFFHAND_RIGHT_SPRITE, half_width + 91, gui.guiHeight() - 23, 29, 24);
        gui.blitSprite(RenderType::guiTextured, HOTBAR_SPRITE, half_width - 91, gui.guiHeight() - 22, 182, 22);
        gui.blitSprite(RenderType::guiTextured, HOTBAR_SELECTION_SPRITE, half_width - 91 - 1 + player.getInventory().selected * 20, gui.guiHeight() - 22 - 1, 24, 23);

        gui.pose().popPose();
    }

    private static void render_items(LocalPlayer player, GuiGraphics gui, DeltaTracker deltaTracker){
        IPlayerEquipment equip_int = player.getData(Attachment_Register.PLAYER_EQUIPMENT);
        IBattleHotbar hotbar_int = player.getData(Attachment_Register.BATTLE_HOTBAR);
        HumanoidArm humanoidarm = player.getMainArm().getOpposite();
        NonNullList<ItemStack> hotbar = hotbar_int.getList();
        ItemStack main = equip_int.getMain();
        ItemStack secondary = equip_int.getSecondary();

        int half_width = gui.guiWidth() / 2;
        int seed = 1;
        for(int i = 0; i < 9; i++){
            int x = half_width - 90 + i * 20 + 2;
            int y = gui.guiHeight() - 16 - 3;
            ItemStack item = hotbar.get(i);
            renderSlot(gui, x, y, deltaTracker, player, item, seed++);
        }

        if (!main.isEmpty()) {
            int i2 = gui.guiHeight() - 16 - 3;
            if (humanoidarm == HumanoidArm.LEFT) {
                renderSlot(gui, half_width - 91 - 26, i2, deltaTracker, player, main, seed++);
            } else {
                renderSlot(gui, half_width + 91 + 10, i2, deltaTracker, player, main, seed++);
            }
        }

        if (!secondary.isEmpty()) {
            int i2 = gui.guiHeight() - 16 - 3;
            if (humanoidarm == HumanoidArm.RIGHT) {
                renderSlot(gui, half_width - 91 - 26, i2, deltaTracker, player, secondary, seed++);
            } else {
                renderSlot(gui, half_width + 91 + 10, i2, deltaTracker, player, secondary, seed++);
            }
        }
    }

    private static void renderSlot(GuiGraphics gui, int x, int y, DeltaTracker deltaTracker, Player player, ItemStack stack, int seed) {
        if (!stack.isEmpty()) {
            float f = (float)stack.getPopTime() - deltaTracker.getGameTimeDeltaPartialTick(false);
            if (f > 0.0F) {
                float f1 = 1.0F + f / 5.0F;
                gui.pose().pushPose();
                gui.pose().translate((float)(x + 8), (float)(y + 12), 0.0F);
                gui.pose().scale(1.0F / f1, (f1 + 1.0F) / 2.0F, 1.0F);
                gui.pose().translate((float)(-(x + 8)), (float)(-(y + 12)), 0.0F);
            }
            gui.renderItem(player, stack, x, y, seed);
            if (f > 0.0F) {
                gui.pose().popPose();
            }

            Font font = Minecraft.getInstance().font;
            gui.renderItemDecorations(font, stack, x, y);
        }
    }
}
