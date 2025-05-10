package com.elly.athena.network.general;

import com.elly.athena.Athena;
import com.elly.athena.ServerHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class RightClickPayload {
    public record RightClickPayloadData(int meta) implements CustomPacketPayload {
        public static final CustomPacketPayload.Type<RightClickPayload.RightClickPayloadData> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "right_click"));

        public static final StreamCodec<ByteBuf, RightClickPayload.RightClickPayloadData> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.INT,
                RightClickPayload.RightClickPayloadData::meta,
                RightClickPayload.RightClickPayloadData::new
        );

        @Override
        public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    public static class ClientPayloadHandler {
        public static void handleDataOnMain(final RightClickPayload.RightClickPayloadData data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                ServerHandler.onSkillUse(context.player());
            });
        }
    }

    public static class ServerPayloadHandler {
        public static void handleDataOnMain(final RightClickPayload.RightClickPayloadData data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                ServerHandler.onSkillUse(context.player());
            });
        }
    }
}
