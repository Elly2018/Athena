package com.elly.athena.network.general;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.implementation.BattleHotbar;
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

public class HotbarPayload {
    public record HotbarData(CompoundTag data) implements CustomPacketPayload {
        public static final CustomPacketPayload.Type<HotbarPayload.HotbarData> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "hotbar"));

        public static final StreamCodec<ByteBuf, HotbarPayload.HotbarData> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.COMPOUND_TAG,
                HotbarPayload.HotbarData::data,
                HotbarPayload.HotbarData::new
        );

        @Override
        public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    public static class ClientPayloadHandler {

        public static void handleDataOnMain(final HotbarPayload.HotbarData data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                LocalPlayer player = Minecraft.getInstance().player;
                if(player == null) return;
                BattleHotbar ps = new BattleHotbar();
                ps.deserializeNBT(context.player().registryAccess(), data.data);
                Minecraft.getInstance().player.setData(Attachment_Register.BATTLE_HOTBAR, ps);
            });
        }
    }

    public static class ServerPayloadHandler {

        public static void handleDataOnMain(final HotbarPayload.HotbarData data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                ServerData sd = Minecraft.getInstance().getCurrentServer();
                BattleHotbar ps = new BattleHotbar();
                ps.deserializeNBT(context.player().registryAccess(), data.data);
                context.player().setData(Attachment_Register.BATTLE_HOTBAR, ps);
            });
        }
    }
}
