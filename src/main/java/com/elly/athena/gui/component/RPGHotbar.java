package com.elly.athena.gui.component;

import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.attachment.IBattleHotbar;
import com.elly.athena.data.interfaceType.attachment.IPlayerEquipment;
import com.elly.athena.data.interfaceType.attachment.IPlayerSkill;
import com.elly.athena.data.types.ModContainer;
import com.elly.athena.event.ClientGameHandler;
import com.elly.athena.item.skill.RPGSkill_Base;
import com.elly.athena.system.skill.SkillData;
import net.minecraft.ChatFormatting;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.util.profiling.Profiler;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.lang.reflect.Field;

public class RPGHotbar {
    private static final ResourceLocation HOTBAR_OFFHAND_LEFT_SPRITE = ResourceLocation.withDefaultNamespace("hud/hotbar_offhand_left");
    private static final ResourceLocation HOTBAR_OFFHAND_RIGHT_SPRITE = ResourceLocation.withDefaultNamespace("hud/hotbar_offhand_right");
    private static final ResourceLocation HOTBAR_SPRITE = ResourceLocation.withDefaultNamespace("hud/hotbar");
    private static final ResourceLocation HOTBAR_SELECTION_SPRITE = ResourceLocation.withDefaultNamespace("hud/hotbar_selection");
    private static ItemStack Last = ItemStack.EMPTY;
    private static int toolHighlightTimer = 0;

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

        ModContainer mod = new ModContainer(player);
        maybeRenderSelectedItemName(gui, mod.getSelected());

        gui.pose().popPose();
    }

    private static void render_items(LocalPlayer player, GuiGraphics gui, DeltaTracker deltaTracker){
        IPlayerEquipment equip_int = player.getData(Attachment_Register.PLAYER_EQUIPMENT);
        IBattleHotbar hotbar_int = player.getData(Attachment_Register.BATTLE_HOTBAR);
        HumanoidArm humanoidarm = player.getMainArm();
        NonNullList<ItemStack> hotbar = hotbar_int.getList();
        ItemStack main = equip_int.getMain();
        ItemStack secondary = equip_int.getSecondary();

        int half_width = gui.guiWidth() / 2;
        int seed = 1;
        for(int i = 0; i < 9; i++){
            int x = half_width - 90 + i * 20 + 2;
            int y = gui.guiHeight() - 19;
            ItemStack item = hotbar.get(i);
            renderSlot(gui, x, y, deltaTracker, player, item, seed++);
        }

        if (!main.isEmpty()) {
            int i2 = gui.guiHeight() - 19;
            if (humanoidarm == HumanoidArm.RIGHT) {
                renderSlot(gui, half_width + 91 + 10, i2, deltaTracker, player, main, seed++);
            } else {
                renderSlot(gui, half_width - 91 - 26 , i2, deltaTracker, player, main, seed++);
            }
        }

        if (!secondary.isEmpty()) {
            int i2 = gui.guiHeight() - 19;
            if (humanoidarm == HumanoidArm.RIGHT) {
                renderSlot(gui, half_width - 91 - 26, i2, deltaTracker, player, secondary, seed++);
            } else {
                renderSlot(gui, half_width + 91 + 10, i2, deltaTracker, player, secondary, seed++);
            }
        }
    }

    private static void renderSlot(GuiGraphics gui, int x, int y, DeltaTracker deltaTracker, Player player, ItemStack stack, int seed) {
        if (!stack.isEmpty()) {
            ItemStack target = stack.copy();
            if(target.getItem() instanceof RPGSkill_Base rpg_skill){
                IPlayerSkill skill = player.getData(Attachment_Register.PLAYER_SKILL);
                String itemName = rpg_skill.getDescriptionId();
                String id = itemName.replace("item.athena.", "");
                SkillData sd = skill.getData(rpg_skill.Category, id);
                int total = rpg_skill.cooldown(sd.Point);
                float ratio = (float)sd.Cooldown / (float)total;
                if(sd.Cooldown > 0){
                    target.setDamageValue((int) (ratio * 100));
                }
            }

            float f = (float)target.getPopTime() - deltaTracker.getGameTimeDeltaPartialTick(false);
            if (f > 0.0F) {
                float f1 = 1.0F + f / 5.0F;
                gui.pose().pushPose();
                gui.pose().translate((float)(x + 8), (float)(y + 12), 0.0F);
                gui.pose().scale(1.0F / f1, (f1 + 1.0F) / 2.0F, 1.0F);
                gui.pose().translate((float)(-(x + 8)), (float)(-(y + 12)), 0.0F);
            }
            gui.renderItem(player, target, x, y, seed);
            if (f > 0.0F) {
                gui.pose().popPose();
            }

            Font font = Minecraft.getInstance().font;
            gui.renderItemDecorations(font, target, x, y);
        }
    }

    private static void maybeRenderSelectedItemName(GuiGraphics gui, ItemStack stack) {
        assert ClientGameHandler.minecraft.gameMode != null;
        if (ClientGameHandler.minecraft.gameMode.getPlayerMode() != GameType.SPECTATOR) {
            renderSelectedItemName(gui, stack);
        }
    }

    private static void renderSelectedItemName(GuiGraphics guiGraphics, ItemStack stack) {
        Profiler.get().push("selectedItemName");

        if (stack.isEmpty()) {
            toolHighlightTimer = 0;
        } else if (!Last.isEmpty() && stack.is(Last.getItem()) && stack.getHoverName().equals(Last.getHoverName()) && stack.getHighlightTip(stack.getHoverName()).equals(Last.getHighlightTip(Last.getHoverName()))) {
            if (toolHighlightTimer > 0) {
                --toolHighlightTimer;
            }
        } else {
            toolHighlightTimer = (int)((double)40.0F * (Double)ClientGameHandler.minecraft.options.notificationDisplayTime().get());
        }

        if (toolHighlightTimer > 0 && !stack.isEmpty()) {
            MutableComponent mutablecomponent = Component.empty().append(stack.getHoverName()).withStyle(stack.getRarity().getStyleModifier());
            if (stack.has(DataComponents.CUSTOM_NAME)) {
                mutablecomponent.withStyle(ChatFormatting.ITALIC);
            }

            Component highlightTip = stack.getHighlightTip(mutablecomponent);
            int i = ClientGameHandler.minecraft.font.width(highlightTip);
            int j = (guiGraphics.guiWidth() - i) / 2;
            int k = guiGraphics.guiHeight() - 59;
            assert ClientGameHandler.minecraft.gameMode != null;
            if (!ClientGameHandler.minecraft.gameMode.canHurtPlayer()) {
                k += 14;
            }

            int l = (int)((float)toolHighlightTimer * 256.0F / 10.0F);
            if (l > 255) {
                l = 255;
            }

            if (l > 0) {
                Font font = IClientItemExtensions.of(stack).getFont(stack, IClientItemExtensions.FontContext.SELECTED_ITEM_NAME);
                if (font == null) {
                    guiGraphics.drawStringWithBackdrop(ClientGameHandler.minecraft.font, highlightTip, j, k, i, ARGB.color(l, -1));
                } else {
                    j = (guiGraphics.guiWidth() - font.width(highlightTip)) / 2;
                    guiGraphics.drawStringWithBackdrop(font, highlightTip, j, k, i, ARGB.color(l, -1));
                }
            }
        }

        Profiler.get().pop();
    }
}
