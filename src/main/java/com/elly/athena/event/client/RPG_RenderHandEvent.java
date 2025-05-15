package com.elly.athena.event.client;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.attachment.IPlayerStatus;
import com.elly.athena.data.types.ModContainer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderHandEvent;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@EventBusSubscriber(modid = Athena.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class RPG_RenderHandEvent {
    private static Field heightField;
    private static Method renderArmWithItemMethod;

    @SubscribeEvent
    public static void renderHand(RenderHandEvent event) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = Minecraft.getInstance().player;
        ItemInHandRenderer renderer = minecraft.gameRenderer.itemInHandRenderer;
        IPlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
        if(ps.getMode() == 0) return;
        event.setCanceled(true);
        float partialTicks = event.getPartialTick();
        ModContainer mdC = new ModContainer(player);
        ItemStack itemstack = event.getHand() == InteractionHand.MAIN_HAND ?
                mdC.playerEquipment.getMain() : mdC.playerEquipment.getSecondary();

        float f = player.getAttackAnim(partialTicks);
        float f1 = player.getXRot(partialTicks);
        float f4 = event.getHand() == InteractionHand.MAIN_HAND ? f : 0.0F;

        heightField = ItemInHandRenderer.class.getDeclaredField(event.getHand() == InteractionHand.MAIN_HAND ? "oMainHandHeight" : "oOffHandHeight");
        heightField.setAccessible(true);
        float height = heightField.getFloat(renderer);
        float f5 = 1.0F - Mth.lerp(partialTicks, height, height);

        renderArmWithItemMethod = ItemInHandRenderer.class.getDeclaredMethod("renderArmWithItem",
                AbstractClientPlayer.class,
                float.class,
                float.class,
                InteractionHand.class,
                float.class,
                ItemStack.class,
                float.class,
                PoseStack.class,
                MultiBufferSource.class,
                int.class
        );
        renderArmWithItemMethod.setAccessible(true);

        renderArmWithItemMethod.invoke(renderer,
                player,
                partialTicks,
                f1,
                event.getHand(),
                f4,
                itemstack,
                0,
                event.getPoseStack(),
                event.getMultiBufferSource(),
                event.getPackedLight()
        );
    }

}
