package com.elly.athena.event.server;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.Attribute_Register;
import com.elly.athena.data.interfaceType.attachment.IPlayerStatus;
import com.elly.athena.event.ServerHandler;
import com.elly.athena.system.BattleSystem;
import com.elly.athena.system.SkillSystem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;

import java.util.Objects;

@EventBusSubscriber(modid = Athena.MODID)
public class RPG_EntityJoinLevelEvent {

    @SubscribeEvent
    public static void entityJoin(EntityJoinLevelEvent event){
        if (event.getEntity() instanceof Player player){
            Runnable r3 = () -> {
                IPlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
                player.setHealth(ps.getLastLoginHP());
                Objects.requireNonNull(player.getAttribute(Attribute_Register.MANA)).setBaseValue(ps.getLastLoginMP());
            };

            Runnable r2 = () -> {
                BattleSystem.ApplyChange(player);
                ServerHandler.event_worker.add(r3);
            };

            Runnable r1 = () -> {
                SkillSystem.initCheck(player);
                Attribute_Register.ApplyChange(player);
                ServerHandler.event_worker.add(r2);
            };

            ServerHandler.event_worker.add(r1);
        }
    }
}
