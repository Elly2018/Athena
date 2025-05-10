package com.elly.athena.entity;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.IPlayerEquipment;
import com.elly.athena.data.interfaceType.IPlayerStatus;
import com.elly.athena.item.Item_Register;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

@EventBusSubscriber(modid = Athena.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class PlayerRender {

    private static Field itemModelResolverField;

    @SubscribeEvent
    public static void onPlayerRender(RenderPlayerEvent.Pre event) throws NoSuchFieldException, IllegalAccessException {
        Minecraft minecraft = Minecraft.getInstance();
        PlayerRenderer render = event.getRenderer();
        PlayerRenderState renderState = event.getRenderState();

        itemModelResolverField = Minecraft.class.getDeclaredField("itemModelResolver");
        itemModelResolverField.setAccessible(true);
        ItemModelResolver solver = (ItemModelResolver) itemModelResolverField.get(minecraft);

        if(minecraft.level == null) return;;
        List<AbstractClientPlayer> players = minecraft.level.players();
        LivingEntity target = null;
        for (AbstractClientPlayer player : players) {
            if (Objects.equals(player.getGameProfile().getName(), renderState.name)) {
                target = player;
            }
        }
        if(target == null) return;

        extractArmedEntityRenderState(target, renderState, solver);
    }

    private static void extractArmedEntityRenderState(LivingEntity entity, ArmedEntityRenderState reusedState, ItemModelResolver itemModelResolver) {
        IPlayerStatus ps = entity.getData(Attachment_Register.PLAYER_STATUS);
        ItemStack main_stack = ItemStack.EMPTY;
        ItemStack secondary_stack = ItemStack.EMPTY;
        if(ps.getMode() == 0){
            main_stack = entity.getMainHandItem();
            secondary_stack = entity.getOffhandItem();
        }else{
            IPlayerEquipment pe = entity.getData(Attachment_Register.PLAYER_EQUIPMENT);
            main_stack = pe.getMain();
            secondary_stack = pe.getSecondary();
        }
        reusedState.mainArm = entity.getMainArm();
        itemModelResolver.updateForLiving(reusedState.rightHandItem, main_stack, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, false, entity);
        itemModelResolver.updateForLiving(reusedState.leftHandItem, secondary_stack, ItemDisplayContext.THIRD_PERSON_LEFT_HAND, true, entity);
    }
}
