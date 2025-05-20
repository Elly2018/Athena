package com.elly.athena.event.server;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.Attribute_Register;
import com.elly.athena.data.interfaceType.attachment.IPlayerStatus;
import com.elly.athena.event.ServerHandler;
import com.elly.athena.system.BattleSystem;
import com.elly.athena.system.SkillSystem;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

import java.util.Objects;

@EventBusSubscriber(modid = Athena.MODID)
public class RPG_EntityJoinLevelEvent {

    @SubscribeEvent
    public static void entityJoin(EntityJoinLevelEvent event){
        if (event.getEntity() instanceof Player player){
            IPlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);

            SkillSystem.initCheck(player);
            SkillSystem.ApplyChange(player);
            Attribute_Register.ApplyChange(player);
            BattleSystem.ApplyChange(player);

            int hp = ps.getLastLoginHP();
            int mp = ps.getLastLoginMP();
            player.setHealth(hp);
            Objects.requireNonNull(player.getAttribute(Attribute_Register.MANA)).setBaseValue(mp);
            ServerHandler.event_worker.add(() -> {
                BattleSystem.ApplyChange(player);
            });
        }
    }
}
