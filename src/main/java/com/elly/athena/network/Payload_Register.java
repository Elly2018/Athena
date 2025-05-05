package com.elly.athena.network;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.implementation.PlayerStatus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = Athena.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Payload_Register {

    @SubscribeEvent
    public static void register_payload(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playBidirectional(
                LootPayload.LootData.TYPE,
                LootPayload.LootData.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        LootPayload.ClientPayloadHandler::handleDataOnMain,
                        LootPayload.ServerPayloadHandler::handleDataOnMain
                )
        );

        final PayloadRegistrar registrar_network = event.registrar("1")
                .executesOn(HandlerThread.NETWORK);
        registrar_network.playBidirectional(
                StatusPayload.StatusData.TYPE,
                StatusPayload.StatusData.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        StatusPayload.ClientPayloadHandler::handleDataOnMain,
                        StatusPayload.ServerPayloadHandler::handleDataOnMain
                )
        );
    }
}
