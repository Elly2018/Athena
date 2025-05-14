package com.elly.athena.event.server;

import com.elly.athena.Athena;
import com.elly.athena.Config;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.Attribute_Register;
import com.elly.athena.data.implementation.BattleHotbar;
import com.elly.athena.data.implementation.PlayerEquipment;
import com.elly.athena.data.implementation.PlayerSkill;
import com.elly.athena.data.implementation.PlayerStatus;
import com.elly.athena.entity.HealEvent;
import com.elly.athena.event.ServerHandler;
import com.elly.athena.gui.menu.Equipment_Menu;
import com.elly.athena.gui.menu.Skill_Menu;
import com.elly.athena.network.general.EquipmentPayload;
import com.elly.athena.network.general.HotbarPayload;
import com.elly.athena.network.general.SkillPayload;
import com.elly.athena.network.general.StatusPayload;
import com.elly.athena.sound.Sound_Register;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.SimpleMenuProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import static com.elly.athena.keymap.KeyMap_Register.EQUIPMENT_MAPPING;
import static com.elly.athena.keymap.KeyMap_Register.SKILL_MAPPING;

@EventBusSubscriber(modid = Athena.MODID)
public class RPG_ServerTickEvent {

    @SubscribeEvent
    public static void update(ServerTickEvent.Pre event){
        HealEvent.onUpdate(event.getServer().getPlayerList().getPlayers());
        event.getServer().getPlayerList().getPlayers().forEach( player -> {
            PlayerStateUpdate(player);
            PlayerMenuUpdate(player);
            PlayerNetworkUpdate(player);
        });
        while (!ServerHandler.event_worker.isEmpty()){
            try {
                ServerHandler.event_worker.take().run();
            } catch (InterruptedException e) {
                Athena.LOGGER.error(e.getLocalizedMessage());
            }
        }
    }

    private static void PlayerNetworkUpdate(ServerPlayer player){
        PlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
        PlayerSkill pss = player.getData(Attachment_Register.PLAYER_SKILL);
        PlayerEquipment pe = player.getData(Attachment_Register.PLAYER_EQUIPMENT);
        BattleHotbar bh = player.getData(Attachment_Register.BATTLE_HOTBAR);
        PacketDistributor.sendToPlayer(player, new StatusPayload.StatusData(ps.serializeNBT(player.registryAccess())));
        PacketDistributor.sendToPlayer(player, new SkillPayload.SkillData(pss.serializeNBT(player.registryAccess())));
        PacketDistributor.sendToPlayer(player, new EquipmentPayload.EquipmentData(pe.serializeNBT(player.registryAccess())));
        PacketDistributor.sendToPlayer(player, new HotbarPayload.HotbarData(bh.serializeNBT(player.registryAccess())));
    }

    private static void PlayerMenuUpdate(ServerPlayer player){
        while (EQUIPMENT_MAPPING.get().consumeClick()){
            player.openMenu(new SimpleMenuProvider(
                    Equipment_Menu::new,
                    Component.empty()
            ));
        }
        while (SKILL_MAPPING.get().consumeClick()) {
            com.elly.athena.Athena.LOGGER.debug(String.format("%s is trying to check skill", player.getName().getString()));
            player.openMenu(new SimpleMenuProvider(
                    Skill_Menu::new,
                    Component.empty()
            ));
        }
    }

    private static void PlayerStateUpdate(ServerPlayer player){
        PlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
        PlayerSkill pss = player.getData(Attachment_Register.PLAYER_SKILL);

        if(ps.isLevelUp(ps.getLevel())){
            ps.setExp(0);
            ps.addLevel(1);
            ps.addPoint(Config.level_point);
            ps.addSkillPoint(Config.level_skill);
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), Sound_Register.LEVELUP.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            Attribute_Register.ApplyChange(player);
        }
        pss.UpdateCooldown();
    }
}
