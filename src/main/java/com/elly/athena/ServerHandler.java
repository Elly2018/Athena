package com.elly.athena;

import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.Attribute_Register;
import com.elly.athena.data.LevelData_Register;
import com.elly.athena.data.implementation.BattleHotbar;
import com.elly.athena.data.implementation.PlayerEquipment;
import com.elly.athena.data.implementation.PlayerSkill;
import com.elly.athena.data.implementation.PlayerStatus;
import com.elly.athena.data.interfaceType.attachment.IPlayerStatus;
import com.elly.athena.entity.HealEvent;
import com.elly.athena.gui.menu.Equipment_Menu;
import com.elly.athena.gui.menu.Skill_Menu;
import com.elly.athena.network.general.EquipmentPayload;
import com.elly.athena.network.general.HotbarPayload;
import com.elly.athena.network.general.SkillPayload;
import com.elly.athena.network.general.StatusPayload;
import com.elly.athena.sound.Sound_Register;
import com.elly.athena.system.BattleSystem;
import com.elly.athena.system.SkillSystem;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static com.elly.athena.keymap.KeyMap_Register.EQUIPMENT_MAPPING;
import static com.elly.athena.keymap.KeyMap_Register.SKILL_MAPPING;

@EventBusSubscriber(modid = Athena.MODID)
public class ServerHandler {
    public static MinecraftServer m_Server;
    public static BlockingQueue<Runnable> event_worker = new ArrayBlockingQueue<>(5);

    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event){
        if(event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            IPlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
            if(ps.getMode() == 1) event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void update(ServerTickEvent.Pre event){
        HealEvent.onUpdate(event.getServer().getPlayerList().getPlayers());
        event.getServer().getPlayerList().getPlayers().forEach( player -> {
            PlayerStateUpdate(player);
            PlayerMenuUpdate(player);
            PlayerNetworkUpdate(player);
        });
        while (!event_worker.isEmpty()){
            try {
                event_worker.take().run();
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

    @SubscribeEvent
    public static void playerLoggin(PlayerEvent.PlayerLoggedInEvent event){
        SkillSystem.initCheck(event.getEntity());
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Pre event){
        if(!Config.damage_cooldown) event.getEntity().invulnerableTime = 0;
        Entity entity = event.getSource().getEntity();
        if(entity instanceof Player player){

        }
    }

    @SubscribeEvent
    public static void onEquipment(LivingEquipmentChangeEvent event){
        if(event.getEntity() instanceof Player player){
            BattleSystem.ApplyEquipmentChange(event, player);;
        }
    }

    @SubscribeEvent
    public static void respawn(PlayerEvent.PlayerRespawnEvent event){
        BattleSystem.ApplyChange(event.getEntity());
    }

    @SubscribeEvent
    public static void playerClone(PlayerEvent.Clone event){
        event.getEntity().setData(Attachment_Register.PLAYER_STATUS, event.getOriginal().getData(Attachment_Register.PLAYER_STATUS));
    }

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        Athena.LOGGER.info("HELLO from Athena server handler");
        m_Server = event.getServer();
        LevelData_Register.LoadEvent();
    }

    @SubscribeEvent
    public static void onServerStopped(ServerStoppedEvent event){
        Athena.LOGGER.info("BYE from Athena server handler");
        m_Server = null;
        LevelData_Register.CleanEvent();
    }
}
