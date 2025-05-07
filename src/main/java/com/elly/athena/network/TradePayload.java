package com.elly.athena.network;

import com.elly.athena.Athena;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class TradePayload {
    public record EventPayloadData(CompoundTag data) implements CustomPacketPayload {
        public static final CustomPacketPayload.Type<TradePayload.EventPayloadData> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "trade"));

        public static final StreamCodec<ByteBuf, TradePayload.EventPayloadData> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.COMPOUND_TAG,
                TradePayload.EventPayloadData::data,
                TradePayload.EventPayloadData::new
        );

        @Override
        public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    public static class ClientPayloadHandler {

        public static void handleDataOnMain(final TradePayload.EventPayloadData data, final IPayloadContext context) {

        }
    }

    public static class ServerPayloadHandler {

        public static void handleDataOnMain(final TradePayload.EventPayloadData data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                CompoundTag tag = data.data();
            });
        }
    }
}
