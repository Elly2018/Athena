package com.elly.athena.event.server;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.Attribute_Register;
import com.elly.athena.data.implementation.PlayerStatus;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@EventBusSubscriber(modid = Athena.MODID)
public class RPG_EntityJoinLevelEvent {

    @SubscribeEvent
    public static void entityJoin(EntityJoinLevelEvent event){
        if (event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            if(!player.hasData(Attachment_Register.PLAYER_STATUS))
                player.setData(Attachment_Register.PLAYER_STATUS, new PlayerStatus());
            Attribute_Register.ApplyChange(player);
        }
    }
}
