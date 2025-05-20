package com.elly.athena.event.server;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.attachment.IDamageRecord;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = Athena.MODID)
public class RPG_LivingIncomingDamageEvent {

    @SubscribeEvent
    public static void onLivingComingDamage(LivingIncomingDamageEvent event){
        if(event.getSource().getEntity() instanceof Player){
            Player player = (Player)event.getSource().getEntity();
            LivingEntity le = event.getEntity();
            IDamageRecord record = event.getEntity().getData(Attachment_Register.DAMAGE_SOURCE);
            if(record != null && player != null){
                record.addPlayerSource(player, (int)event.getAmount());
                Athena.LOGGER.debug(String.format("Added player %s to damage table of the %s %d", player.getUUID(), le.getName().getString(), (int)event.getAmount()));
            }else{
                Athena.LOGGER.warn(String.format("Failed assign to damage table: %b", record != null));
            }
        }
    }
}
