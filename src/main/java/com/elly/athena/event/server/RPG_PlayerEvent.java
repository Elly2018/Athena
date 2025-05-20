package com.elly.athena.event.server;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.system.BattleSystem;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = Athena.MODID)
public class RPG_PlayerEvent {

    @SubscribeEvent
    public static void respawn(PlayerEvent.PlayerRespawnEvent event){
        BattleSystem.ApplyChange(event.getEntity());
    }

    @SubscribeEvent
    public static void playerClone(PlayerEvent.Clone event){
        event.getEntity().setData(Attachment_Register.PLAYER_STATUS, event.getOriginal().getData(Attachment_Register.PLAYER_STATUS));
    }
}
