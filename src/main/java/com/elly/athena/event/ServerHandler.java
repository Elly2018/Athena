package com.elly.athena.event;

import com.elly.athena.Athena;
import com.elly.athena.Config;
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
