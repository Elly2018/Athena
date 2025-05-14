package com.elly.athena.event.client;

import com.elly.athena.Athena;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;

@EventBusSubscriber(modid = Athena.MODID, bus = EventBusSubscriber.Bus.GAME)
public class RPG_RenderPlayerEvent {

    @SubscribeEvent
    public static void renderPlayer(RenderPlayerEvent.Pre event) {

    }
}
