package com.elly.athena.event.client;

import com.elly.athena.Athena;
import com.elly.athena.network.general.EventGeneralPayload;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = Athena.MODID, bus = EventBusSubscriber.Bus.GAME)
public class RPG_InputEvent {

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onClientsScroll(InputEvent.MouseScrollingEvent event){
        PacketDistributor.sendToServer(EventGeneralPayload.GenerateData_Input(EventGeneralPayload.InputMeta.SELECTION));
    }
}
