package com.elly.athena.event.common;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.attachment.IPlayerStatus;
import com.elly.athena.data.types.ModContainer;
import com.elly.athena.entity.npc.RPGNPC;
import com.elly.athena.item.use.potion.RPGPotion_Base;
import com.elly.athena.item.skill.RPGSkill_Base;
import com.elly.athena.item.special.setting.NPCStaff;
import com.elly.athena.network.general.EventGeneralPayload;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = Athena.MODID)
public class RPG_PlayerInteractEvent {

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.RightClickItem event){
        Player player = event.getEntity();
        IPlayerStatus status = player.getData(Attachment_Register.PLAYER_STATUS);
        if(status.getMode() == 1) {
            onSkillUse(player);
            event.setCanceled(true);
        }
    }

    public static void onEntityInteract(PlayerInteractEvent.EntityInteractSpecific event){
        ItemStack iss = event.getItemStack();
        Entity target = event.getTarget();
        Player source = event.getEntity();
        if(iss.getItem() instanceof NPCStaff.NPCStaff_Item npc_staff &&
                target instanceof RPGNPC npc_target &&
                source.isCreative()){
            npc_staff.useStaff(source, npc_target);
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onEntityInteract(PlayerInteractEvent.RightClickEmpty event){
        Player player = event.getEntity();
        IPlayerStatus status = player.getData(Attachment_Register.PLAYER_STATUS);
        if(status.getMode() == 1) {
            PacketDistributor.sendToServer(EventGeneralPayload.GenerateData_Input(EventGeneralPayload.InputMeta.RIGHT_CLICK));
        }
    }

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.RightClickBlock event){
        Player player = event.getEntity();
        IPlayerStatus status = player.getData(Attachment_Register.PLAYER_STATUS);
        if(status.getMode() == 1) {
            onSkillUse(player);
            event.setCanceled(true);
        }
    }
    public static void onSkillUse(Player player){
        ModContainer container = new ModContainer(player);
        ItemStack iss = container.getSelected();
        if(iss == ItemStack.EMPTY) {
            Athena.LOGGER.debug(String.format("onSkillUse but selected is empty: %s", player.getName().getString()));
            return;
        }
        if(iss.getItem() instanceof RPGPotion_Base){
            Athena.LOGGER.debug(String.format("onSkillUse start use point: %s", player.getName().getString()));
            iss.use(player.level(), player, InteractionHand.MAIN_HAND);
        }
        else if(iss.getItem() instanceof RPGSkill_Base){
            Athena.LOGGER.debug(String.format("onSkillUse start use skill: %s", player.getName().getString()));
            iss.use(player.level(), player, InteractionHand.MAIN_HAND);
        }
    }
}
