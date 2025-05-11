package com.elly.athena.entity;

import com.elly.athena.Config;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.IPlayerStatus;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.Collection;

public class HealEvent {
    private static int tick = 0;

    public static void onUpdate(Collection<ServerPlayer> players){
        tick++;
        if(tick > Config.heal_countdown){
            tick = 0;

            players.forEach(player -> {
                IPlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
                double maxvalue = player.getAttributes().getInstance(Attributes.MAX_HEALTH).getValue();
                float heal = (float) (maxvalue * 0.05F);
                player.heal(heal);

                int maxMana = ps.getManaMaximum();
                heal = (float) (maxMana * 0.05F);
                ps.addMana((int) Math.ceil(heal));
            });
        }
    }
}
