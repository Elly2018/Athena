package com.elly.athena.event.client;

import com.elly.athena.Athena;
import com.elly.athena.command.Command_Register;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = Athena.MODID, bus = EventBusSubscriber.Bus.GAME)
public class RPG_RegisterCommandsEvent {

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event){
        Command_Register.register(event.getDispatcher());
    }
}
