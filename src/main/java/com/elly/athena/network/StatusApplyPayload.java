package com.elly.athena.network;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.implementation.PlayerStatus;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class StatusApplyPayload {

    public record StatusApplyData(CompoundTag data) implements CustomPacketPayload {
        public static final CustomPacketPayload.Type<StatusApplyData> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "status_apply"));

        public static final StreamCodec<ByteBuf, StatusApplyData> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.COMPOUND_TAG,
                StatusApplyData::data,
                StatusApplyData::new
        );

        @Override
        public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    public static class ClientPayloadHandler {

        public static void handleDataOnMain(final StatusApplyData data, final IPayloadContext context) {

        }
    }

    public static class ServerPayloadHandler {

        public static void handleDataOnMain(final StatusApplyData data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                PlayerStatus buffer = new PlayerStatus();
                buffer.deserializeNBT(null, data.data);

                Player player = context.player();
                PlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
                ps.addStr(buffer.getStr());
                ps.addDex(buffer.getDex());
                ps.addInt(buffer.getInt());
                ps.addLuk(buffer.getLuk());
                ps.addPoint(buffer.getPoint());

                player.setData(Attachment_Register.PLAYER_STATUS, ps);
            });
        }
    }
}
