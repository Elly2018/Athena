package com.elly.athena.network;

import com.elly.athena.Athena;
import com.elly.athena.system.SkillSystem;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SkillPayload {
    public record SkillData(CompoundTag data) implements CustomPacketPayload {
        public static final CustomPacketPayload.Type<SkillData> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "skill"));

        public static final StreamCodec<ByteBuf, SkillData> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.COMPOUND_TAG,
                SkillData::data,
                SkillData::new
        );

        @Override
        public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    public static CompoundTag Generate(String UUID, String skill, int level){
        CompoundTag nbt = new CompoundTag();
        nbt.putString("player", UUID);
        nbt.putString("skill", skill);
        nbt.putInt("level", level);
        return nbt;
    }

    public static CompoundTag Generate(String UUID, String skill, int level, LivingEntity target){
        CompoundTag nbt = new CompoundTag();
        nbt.putString("player", UUID);
        nbt.putString("skill", skill);
        nbt.putInt("level", level);
        nbt.putString("target", target.getUUID().toString());
        return nbt;
    }

    public static class ClientPayloadHandler {

        public static void handleDataOnMain(final SkillPayload.SkillData data, final IPayloadContext context) {
        }
    }

    public static class ServerPayloadHandler {

        public static void handleDataOnMain(final SkillPayload.SkillData data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                SkillSystem.HandleNetworkSkillEvent(data, context);
            });
        }
    }
}
