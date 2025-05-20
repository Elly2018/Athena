package com.elly.athena.event.common;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.implementation.PlayerStatus;
import com.elly.athena.data.interfaceType.attachment.IDamageRecord;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

import java.util.HashMap;
import java.util.UUID;

import static com.elly.athena.event.ServerHandler.m_Server;

@EventBusSubscriber(modid = Athena.MODID)
public class RPG_LivingDeathEvent {

    @SubscribeEvent
    public static void onLivingKill(LivingDeathEvent event){
        LivingEntity le = event.getEntity();
        IDamageRecord record = le.getData(Attachment_Register.DAMAGE_SOURCE);
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
}
