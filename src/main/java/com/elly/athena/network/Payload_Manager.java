package com.elly.athena.network;

import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.implementation.PlayerStatus;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class Payload_Manager {

    public void register_payload(final RegisterPayloadHandlersEvent event) {
        // Sets the current network version
        final PayloadRegistrar registrar = event.registrar("1")
                .executesOn(HandlerThread.NETWORK);
        registrar.playBidirectional(
                StatusPayload.StatusData.TYPE,
                StatusPayload.StatusData.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        StatusPayload.ClientPayloadHandler::handleDataOnMain,
                        StatusPayload.ServerPayloadHandler::handleDataOnMain
                )
        );
    }

    public void update(ServerTickEvent.Pre event){
        event.getServer().getPlayerList().getPlayers().forEach( player -> {
            PlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
            PacketDistributor.sendToPlayer(player, new StatusPayload.StatusData(ps.serializeNBT(null)));
        });
    }
}
