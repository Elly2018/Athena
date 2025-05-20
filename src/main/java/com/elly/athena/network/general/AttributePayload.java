package com.elly.athena.network.general;

import com.elly.athena.Athena;
import com.elly.athena.event.ClientGameHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class AttributePayload {
    public record AttributeData(CompoundTag data) implements CustomPacketPayload {
        public static final CustomPacketPayload.Type<AttributePayload.AttributeData> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "attribute"));

        public static final StreamCodec<ByteBuf, AttributePayload.AttributeData> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.COMPOUND_TAG,
                AttributePayload.AttributeData::data,
                AttributePayload.AttributeData::new
        );

        @Override
        public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    public static AttributeData SelfTag(AttributeMap map){
        CompoundTag t = new CompoundTag();
        t.putInt("type", 0);
        t.put("list", map.save());
        return new AttributeData(t);
    }

    public static class ClientPayloadHandler {

        public static void handleDataOnMain(final AttributePayload.AttributeData data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                int type = data.data.getInt("type");
                ListTag list = data.data.getList("list", 10);
                if(type == 0){
                    ClientGameHandler.self_status = list;
                }
            });
        }
    }

    public static class ServerPayloadHandler {

        public static void handleDataOnMain(final AttributePayload.AttributeData data, final IPayloadContext context) {
            context.enqueueWork(() -> {

            });
        }
    }
}
