package com.elly.athena.event.common;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.attachment.IPlayerStatus;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = Athena.MODID)
public class RPG_BlockEvent {

    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event){
        if(event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            IPlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
            if(ps.getMode() == 1) event.setCanceled(true);
        }
    }
}
