package com.elly.athena.event.common;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.attachment.IPlayerStatus;
import com.elly.athena.item.Item_Register;
import com.elly.athena.network.general.LootPayload;
import com.elly.athena.sound.Sound_Register;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = Athena.MODID)
public class RPG_ItemEntityPickupEvent {

    @SubscribeEvent
    public static void onItemPickup(ItemEntityPickupEvent event){
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
