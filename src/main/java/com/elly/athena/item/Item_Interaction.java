package com.elly.athena.item;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.attachment.IPlayerStatus;
import com.elly.athena.data.types.ModContainer;
import com.elly.athena.entity.RPGNPC;
import com.elly.athena.item.potion.RPGPotion_Base;
import com.elly.athena.item.skill.RPGSkill_Base;
import com.elly.athena.item.special.setting.NPCStaff;
import com.elly.athena.network.general.LootPayload;
import com.elly.athena.network.general.RightClickPayload;
import com.elly.athena.sound.Sound_Register;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = Athena.MODID)
public class Item_Interaction {

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
            PacketDistributor.sendToServer(new RightClickPayload.RightClickPayloadData(0));
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

    @SubscribeEvent
    public static void onTossItem(ItemTossEvent event){

    }

    @SubscribeEvent
    public static void onItemPickup(ItemEntityPickupEvent.Pre event){
        ItemEntity ie = event.getItemEntity();
        ItemStack is = ie.getItem();
        Item item = is.getItem();
        ServerPlayer player = (ServerPlayer) event.getPlayer();

        PickupGoldenHelper(player, ie, is);
        if(!ie.hasPickUpDelay()){
            var tag = LootPayload.Generate(item.getName().getString(), 16777215, is.getCount());
            PacketDistributor.sendToPlayer(player, new LootPayload.LootData(tag));
        }
    }

    private static void PickupGoldenHelper(ServerPlayer player, ItemEntity ie, ItemStack is){
        Item cointype = Item_Register.RegisterDict.get("coin").get();
        Item goldencointype = Item_Register.RegisterDict.get("coin_golden").get();
        Item coinbagtype = Item_Register.RegisterDict.get("coin_bag").get();
        Item goldencoinbagtype = Item_Register.RegisterDict.get("coin_golden_bag").get();

        int count = 0;
        if(!ie.hasPickUpDelay()){
            if(is.getItem() == cointype){
                count = is.getCount();
                player.playSound(Sound_Register.Meso.get());
            }
            else if(is.getItem() == goldencointype){
                count = is.getCount() * 100;
                player.playSound(Sound_Register.Meso.get());
            }
            else if(is.getItem() == coinbagtype){
                count = is.getCount() * 1000;
                player.playSound(Sound_Register.Meso.get());
            }
            else if(is.getItem() == goldencoinbagtype){
                count = is.getCount() * 10000;
                player.playSound(Sound_Register.Meso.get());
            }
        }
        if(count > 0){
            ie.setDefaultPickUpDelay();
            IPlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
            ps.addCoin(count);
            ie.remove(Entity.RemovalReason.KILLED);
            var tag = LootPayload.Generate(cointype.getName().getString(), 16777215, is.getCount());
            PacketDistributor.sendToPlayer(player, new LootPayload.LootData(tag));
        }
    }
}
