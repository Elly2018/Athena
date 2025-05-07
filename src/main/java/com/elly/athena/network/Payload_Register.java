package com.elly.athena.network;

import com.elly.athena.Athena;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = Athena.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Payload_Register {

    @SubscribeEvent
    public static void register_payload(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1")
            .executesOn(HandlerThread.NETWORK);
        registrar.playBidirectional(
                LootPayload.LootData.TYPE,
                LootPayload.LootData.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        LootPayload.ClientPayloadHandler::handleDataOnMain,
                        LootPayload.ServerPayloadHandler::handleDataOnMain
                )
        );
        registrar.playBidirectional(
                StatusApplyPayload.StatusApplyData.TYPE,
                StatusApplyPayload.StatusApplyData.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        StatusApplyPayload.ClientPayloadHandler::handleDataOnMain,
                        StatusApplyPayload.ServerPayloadHandler::handleDataOnMain
                )
        );
        registrar.playBidirectional(
                StatusPayload.StatusData.TYPE,
                StatusPayload.StatusData.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        StatusPayload.ClientPayloadHandler::handleDataOnMain,
                        StatusPayload.ServerPayloadHandler::handleDataOnMain
                )
        );
    }
}
