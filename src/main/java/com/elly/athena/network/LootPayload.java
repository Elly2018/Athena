package com.elly.athena.network;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.implementation.PlayerStatus;
import com.elly.athena.gui.Hud;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
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
        ListTag listTag = new ListTag();

        CompoundTag itemNameTag = new CompoundTag();
        itemNameTag.putString("item_name", name);
        listTag.add(itemNameTag);

        CompoundTag colorTag = new CompoundTag();
        colorTag.putInt("color", color);
        listTag.add(colorTag);

        CompoundTag amountTag = new CompoundTag();
        amountTag.putInt("amount", amount);
        listTag.add(amountTag);

        nbt.put("element", listTag);
        return nbt;
    }

    public class ClientPayloadHandler {

        public static void handleDataOnMain(final LootPayload.LootData data, final IPayloadContext context) {
            // Do something with the data, on the main thread
            LocalPlayer player = Minecraft.getInstance().player;
            if(player == null) return;
            ListTag listTag = data.data.getList("element", 10);
            CompoundTag itemNameTag = listTag.getCompound(0);
            String itemName = itemNameTag.getString("item_name");
            CompoundTag colorTag = listTag.getCompound(1);
            int color = colorTag.getInt("color");
            CompoundTag amountTag = listTag.getCompound(2);
            int amount = amountTag.getInt("amount");
            Hud.AddLoot(itemName, color, amount);
            Athena.LOGGER.info(String.format("Added lot to client: %s %d %d", itemName, color, amount));
        }
    }

    public class ServerPayloadHandler {

        public static void handleDataOnMain(final LootPayload.LootData data, final IPayloadContext context) {
            // Do something with the data, on the main thread
            PlayerStatus ps = context.player().getData(Attachment_Register.PLAYER_STATUS);
            ps.deserializeNBT(null, data.data);
        }
    }
}
