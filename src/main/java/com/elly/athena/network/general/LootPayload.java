package com.elly.athena.network.general;

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

public class LootPayload {
    public record LootData(CompoundTag data) implements CustomPacketPayload {
        public static final CustomPacketPayload.Type<LootData> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "loot"));

        public static final StreamCodec<ByteBuf, LootData> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.COMPOUND_TAG,
                LootData::data,
                LootData::new
        );

        @Override
        public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    public static CompoundTag Generate(String name, int color, int amount){
        CompoundTag nbt = new CompoundTag();
        nbt.putString("item_name", name);
        nbt.putInt("color", color);
        nbt.putInt("amount", amount);
        return nbt;
    }

    public static class ClientPayloadHandler {

        public static void handleDataOnMain(final LootPayload.LootData data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                LocalPlayer player = Minecraft.getInstance().player;
                if(player == null) return;

                String itemName = data.data.getString("item_name");
                int color = data.data.getInt("color");
                int amount = data.data.getInt("amount");
                Hud.AddLoot(itemName, color, amount);
                Athena.LOGGER.info(String.format("Added lot to client: %s %d %d", itemName, color, amount));
            });
        }
    }

    public static class ServerPayloadHandler {

        public static void handleDataOnMain(final LootPayload.LootData data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                PlayerStatus ps = context.player().getData(Attachment_Register.PLAYER_STATUS);
                ps.deserializeNBT(null, data.data);
            });
        }
    }
}
