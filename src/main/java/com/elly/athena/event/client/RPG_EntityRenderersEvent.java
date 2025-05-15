package com.elly.athena.event.client;

import com.elly.athena.Athena;
import com.elly.athena.entity.Entity_Register;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = Athena.MODID, bus = EventBusSubscriber.Bus.GAME)
public class RPG_EntityRenderersEvent {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        Entity_Register.registerEntityRenderers(event);
    }
}
