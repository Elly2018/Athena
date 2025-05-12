package com.elly.athena.network.input;

import com.elly.athena.Athena;
import com.elly.athena.ServerHandler;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.Attribute_Register;
import com.elly.athena.data.implementation.PlayerEquipment;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SelectionPayload {
    public record SelectionData(int dummy) implements CustomPacketPayload {
        public static final CustomPacketPayload.Type<SelectionPayload.SelectionData> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "selection"));

        public static final StreamCodec<ByteBuf, SelectionPayload.SelectionData> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.INT,
                SelectionPayload.SelectionData::dummy,
                SelectionPayload.SelectionData::new
        );

        @Override
        public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    public static class ClientPayloadHandler {

        public static void handleDataOnMain(final SelectionPayload.SelectionData data, final IPayloadContext context) {
            context.enqueueWork(() -> {

            });
        }
    }

    public static class ServerPayloadHandler {

        public static void handleDataOnMain(final SelectionPayload.SelectionData data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                ServerHandler.event_worker.add(() -> {
                    Attribute_Register.ApplyChange(context.player());
                });
            });
        }
    }
}
