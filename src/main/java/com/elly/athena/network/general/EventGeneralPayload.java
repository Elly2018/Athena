package com.elly.athena.network.general;

import com.elly.athena.Athena;
import com.elly.athena.data.Attribute_Register;
import com.elly.athena.event.common.RPG_PlayerInteractEvent;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class EventGeneralPayload {
    public enum EventType {
        INPUT
    }
    public enum InputMeta {
        RIGHT_CLICK, SELECTION
    }

    public static EventGeneralPayloadData GenerateData_Input(InputMeta meta){
        CompoundTag tag = new CompoundTag();
        tag.putInt("type", EventType.INPUT.ordinal());
        tag.putInt("meta", meta.ordinal());
        return new EventGeneralPayloadData(tag);
    }

    public record EventGeneralPayloadData(CompoundTag data) implements CustomPacketPayload {
        public static final CustomPacketPayload.Type<EventGeneralPayload.EventGeneralPayloadData> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "event"));

        public static final StreamCodec<ByteBuf, EventGeneralPayload.EventGeneralPayloadData> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.COMPOUND_TAG,
                EventGeneralPayload.EventGeneralPayloadData::data,
                EventGeneralPayload.EventGeneralPayloadData::new
        );

        @Override
        public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    public static class ClientPayloadHandler {

        public static void handleDataOnMain(final EventGeneralPayload.EventGeneralPayloadData data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                int type = data.data.getInt("type");
                if(type == EventType.INPUT.ordinal()){
                    int meta = data.data.getInt("meta");
                    if(meta == InputMeta.RIGHT_CLICK.ordinal()){
                        RPG_PlayerInteractEvent.onSkillUse(context.player());
                    }
                }
            });
        }
    }

    public static class ServerPayloadHandler {

        public static void handleDataOnMain(final EventGeneralPayload.EventGeneralPayloadData data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                int type = data.data.getInt("type");
                if(type == EventType.INPUT.ordinal()){
                    int meta = data.data.getInt("meta");
                    if(meta == InputMeta.RIGHT_CLICK.ordinal()){
                        RPG_PlayerInteractEvent.onSkillUse(context.player());
                    }
                    else if(meta == InputMeta.SELECTION.ordinal()){
                        Attribute_Register.ApplyChange(context.player());
                    }
                }
            });
        }
    }
}
