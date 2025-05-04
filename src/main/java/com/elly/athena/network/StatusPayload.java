package com.elly.athena.network;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.implementation.PlayerStatus;
import com.elly.athena.gui.Hud;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class StatusPayload {

    public record StatusData(CompoundTag data) implements CustomPacketPayload {
        public static final CustomPacketPayload.Type<StatusData> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "status"));

        public static final StreamCodec<ByteBuf, StatusData> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.COMPOUND_TAG,
                StatusData::data,
                StatusData::new
        );

        @Override
        public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    public class ClientPayloadHandler {

        public static void handleDataOnMain(final StatusData data, final IPayloadContext context) {
            // Do something with the data, on the main thread
            LocalPlayer player = Minecraft.getInstance().player;
            if(player == null) return;
            PlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
            ps.deserializeNBT(null, data.data);
            Hud.LocalPlayerStatus = ps;
        }
    }

    public class ServerPayloadHandler {

        public static void handleDataOnMain(final StatusData data, final IPayloadContext context) {
            // Do something with the data, on the main thread
            PlayerStatus ps = context.player().getData(Attachment_Register.PLAYER_STATUS);
            ps.deserializeNBT(null, data.data);
        }
    }
}
