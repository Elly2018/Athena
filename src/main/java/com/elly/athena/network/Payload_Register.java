package com.elly.athena.network;

import com.elly.athena.Athena;
import com.elly.athena.network.general.*;
import com.elly.athena.network.menu.SkillMenuPayload;
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
        register_general(registrar);
        register_menu(registrar);
    }

    private static void register_general(final PayloadRegistrar registrar) {
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
        registrar.playBidirectional(
                SkillPayload.SkillData.TYPE,
                SkillPayload.SkillData.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        SkillPayload.ClientPayloadHandler::handleDataOnMain,
                        SkillPayload.ServerPayloadHandler::handleDataOnMain
                )
        );
        registrar.playBidirectional(
                TradePayload.TradePayloadData.TYPE,
                TradePayload.TradePayloadData.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        TradePayload.ClientPayloadHandler::handleDataOnMain,
                        TradePayload.ServerPayloadHandler::handleDataOnMain
                )
        );
        registrar.playBidirectional(
                HotbarPayload.HotbarData.TYPE,
                HotbarPayload.HotbarData.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        HotbarPayload.ClientPayloadHandler::handleDataOnMain,
                        HotbarPayload.ServerPayloadHandler::handleDataOnMain
                )
        );
        registrar.playBidirectional(
                EquipmentPayload.EquipmentData.TYPE,
                EquipmentPayload.EquipmentData.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        EquipmentPayload.ClientPayloadHandler::handleDataOnMain,
                        EquipmentPayload.ServerPayloadHandler::handleDataOnMain
                )
        );
        registrar.playBidirectional(
                EventGeneralPayload.EventGeneralPayloadData.TYPE,
                EventGeneralPayload.EventGeneralPayloadData.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        EventGeneralPayload.ClientPayloadHandler::handleDataOnMain,
                        EventGeneralPayload.ServerPayloadHandler::handleDataOnMain
                )
        );
        registrar.playBidirectional(
                AttributePayload.AttributeData.TYPE,
                AttributePayload.AttributeData.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        AttributePayload.ClientPayloadHandler::handleDataOnMain,
                        AttributePayload.ServerPayloadHandler::handleDataOnMain
                )
        );
    }

    private static void register_menu(final PayloadRegistrar registrar){
        registrar.playBidirectional(
                SkillMenuPayload.SkillMenuData.TYPE,
                SkillMenuPayload.SkillMenuData.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        SkillMenuPayload.ClientPayloadHandler::handleDataOnMain,
                        SkillMenuPayload.ServerPayloadHandler::handleDataOnMain
                )
        );
    }
}
