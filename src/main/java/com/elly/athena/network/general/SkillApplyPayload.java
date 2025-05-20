package com.elly.athena.network.general;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.attachment.IPlayerSkill;
import com.elly.athena.data.interfaceType.attachment.IPlayerStatus;
import com.elly.athena.system.SkillSystem;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SkillApplyPayload {
    public record SkillApplyData(CompoundTag data) implements CustomPacketPayload {
        public static final CustomPacketPayload.Type<SkillApplyPayload.SkillApplyData> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "skill"));

        public static final StreamCodec<ByteBuf, SkillApplyPayload.SkillApplyData> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.COMPOUND_TAG,
                SkillApplyPayload.SkillApplyData::data,
                SkillApplyPayload.SkillApplyData::new
        );

        @Override
        public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    public static CompoundTag Generate(String skillName){
        CompoundTag tag = new CompoundTag();
        tag.putString("name", skillName);
        return tag;
    }

    public static class ClientPayloadHandler {

        public static void handleDataOnMain(final SkillApplyPayload.SkillApplyData data, final IPayloadContext context) {
            context.enqueueWork(() -> {

            });
        }
    }

    public static class ServerPayloadHandler {

        public static void handleDataOnMain(final SkillApplyPayload.SkillApplyData data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                String n = data.data.getString("name");
                Player player = context.player();
                IPlayerSkill ips = player.getData(Attachment_Register.PLAYER_SKILL);
                IPlayerStatus ipss = player.getData(Attachment_Register.PLAYER_STATUS);
                ipss.addPoint(-1);
                ips.addPoint(n, 1);
                SkillSystem.ApplyChange(player);
            });
        }
    }
}
