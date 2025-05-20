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
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.Objects;

@EventBusSubscriber(modid = Athena.MODID)
public class RPG_PlayerEvent {

    @SubscribeEvent
    public static void playerLoggin(PlayerEvent.PlayerLoggedInEvent event){

    }

    @SubscribeEvent
    public static void playerLoggin(PlayerEvent.PlayerLoggedOutEvent event){
        ServerHandler.event_worker.add(() -> {
            Player player = event.getEntity();
            IPlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
            ps.setLastLoginHP((int)player.getHealth());
            ps.setLastLoginMP((int) Objects.requireNonNull(player.getAttribute(Attribute_Register.MANA)).getBaseValue());
        });
    }

    @SubscribeEvent
    public static void respawn(PlayerEvent.PlayerRespawnEvent event){
        BattleSystem.ApplyChange(event.getEntity());
    }

    @SubscribeEvent
    public static void playerClone(PlayerEvent.Clone event){
        event.getEntity().setData(Attachment_Register.PLAYER_STATUS, event.getOriginal().getData(Attachment_Register.PLAYER_STATUS));
    }
}
