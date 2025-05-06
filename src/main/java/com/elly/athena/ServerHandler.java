package com.elly.athena;

import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.implementation.PlayerStatus;
import com.elly.athena.data.interfaceType.IDamage_Record;
import com.elly.athena.data.interfaceType.IPlayerStatus;
import com.elly.athena.item.Item_Register;
import com.elly.athena.network.LootPayload;
import com.elly.athena.network.StatusPayload;
import com.elly.athena.sound.Sound_Register;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingExperienceDropEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.HashMap;
import java.util.UUID;


@EventBusSubscriber(modid = Athena.MODID)
public class ServerHandler {

    private static MinecraftServer m_Server;

    @SubscribeEvent
    public static void onItemPickup(ItemEntityPickupEvent.Pre event){
        ItemEntity ie = event.getItemEntity();
        ItemStack is = ie.getItem();
        Item item = is.getItem();
        ServerPlayer player = (ServerPlayer) event.getPlayer();
        Item cointype = Athena.item_register.RegisterDict.get("coin").get();
        //Item coin_bagtype = Athena.item_register.RegisterDict.get("coin_bag").get();

        if(is.getItem() == cointype && !ie.hasPickUpDelay()){
            ie.setDefaultPickUpDelay();
            IPlayerStatus ps = event.getPlayer().getData(Attachment_Register.PLAYER_STATUS);
            ps.addCoin(is.getCount());
            ie.remove(Entity.RemovalReason.KILLED);
            var tag = LootPayload.Generate(cointype.getName().getString(), 16777215, is.getCount());
            PacketDistributor.sendToPlayer(player, new LootPayload.LootData(tag));
        }
        if(!ie.hasPickUpDelay()){
            var tag = LootPayload.Generate(item.getName().getString(), 16777215, is.getCount());
            PacketDistributor.sendToPlayer(player, new LootPayload.LootData(tag));
        }
    }

    @SubscribeEvent
    public static void update(ServerTickEvent.Pre event){
        event.getServer().getPlayerList().getPlayers().forEach( player -> {
            PlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);

            if(ps.isLevelUp(ps.getLevel())){
                ps.setExp(0);
                ps.addLevel(1);
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), Sound_Register.LEVELUP.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            }

            PacketDistributor.sendToPlayer(player, new StatusPayload.StatusData(ps.serializeNBT(null)));
        });
    }

    @SubscribeEvent
    public static void entityJoin(EntityJoinLevelEvent event){
        if (event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();

            if(!player.hasData(Attachment_Register.PLAYER_STATUS))
                player.setData(Attachment_Register.PLAYER_STATUS, new PlayerStatus());
        }
    }


    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Pre event){

    }

    @SubscribeEvent
    public static void onLivingComingDamage(LivingIncomingDamageEvent event){
        if(event.getSource().getEntity() instanceof Player){
            Player player = (Player)event.getSource().getEntity();
            LivingEntity le = event.getEntity();
            IDamage_Record record = event.getEntity().getData(Attachment_Register.DAMAGE_SOURCE);
            if(record != null && player != null){
                record.addPlayerSource(player, (int)event.getAmount());
                Athena.LOGGER.debug(String.format("Added player %s to damage table of the %s %d", player.getUUID(), le.getName().getString(), (int)event.getAmount()));
            }else{
                Athena.LOGGER.warn(String.format("Failed assign to damage table: %b, %b", record != null, player != null));
            }
        }
    }

    @SubscribeEvent
    public static void onLivingKill(LivingDeathEvent event){
        LivingEntity le = event.getEntity();
        IDamage_Record record = le.getData(Attachment_Register.DAMAGE_SOURCE);
        if(record != null && m_Server != null){
            HashMap<UUID, Float> table = record.getPlayerTable();
            var ref = new Object() {
                float total = 0F;
            };
            table.values().forEach(x -> { ref.total += x; });
            Athena.LOGGER.debug(String.format("Calcuate total damage: %.2f", ref.total));
            Athena.LOGGER.debug(String.format("Calcuate amount of key: %d", table.size()));
            table.keySet().forEach(x -> {
                float weight = table.get(x) / ref.total;
                ServerPlayer player = m_Server.getPlayerList().getPlayer(x);
                PlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
                float baseAmmount = (le.getType().getCategory() == MobCategory.MONSTER ? 40 : 20);
                float a = baseAmmount * weight;
                ps.addExp((int)a);
                Athena.LOGGER.debug(String.format("Given %s player %.2f percentage exp killed", x.toString(), weight));
            });
        }else{
            Athena.LOGGER.warn(String.format("Failed given exp: %b, %b", record != null, m_Server != null));
        }
    }

    @SubscribeEvent
    public static void onLivingExpDrop(LivingExperienceDropEvent event){
        if(!Config.vanilla_exp_drop){
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void playerClone(PlayerEvent.Clone event){
        event.getEntity().setData(Attachment_Register.PLAYER_STATUS, event.getOriginal().getData(Attachment_Register.PLAYER_STATUS));
    }

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        Athena.LOGGER.info("HELLO from Athena server handler");
        m_Server = event.getServer();
    }

    @SubscribeEvent
    public static void onServerStopped(ServerStoppedEvent event){
        Athena.LOGGER.info("BYE from Athena server handler");
        m_Server = null;
    }
}
