package com.elly.athena.event;

import com.elly.athena.Athena;
import com.elly.athena.data.LevelData_Register;
import net.minecraft.server.MinecraftServer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

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
