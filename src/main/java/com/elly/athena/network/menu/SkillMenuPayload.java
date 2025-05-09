package com.elly.athena.network.menu;

import com.elly.athena.Athena;
import com.elly.athena.gui.menu.Skill_Menu;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SkillMenuPayload {
    public record SkillMenuData(CompoundTag data) implements CustomPacketPayload {
        public static final CustomPacketPayload.Type<SkillMenuPayload.SkillMenuData> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Athena.MODID, "skill_menu"));

        public static final StreamCodec<ByteBuf, SkillMenuPayload.SkillMenuData> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.COMPOUND_TAG,
                SkillMenuPayload.SkillMenuData::data,
                SkillMenuPayload.SkillMenuData::new
        );

        @Override
        public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    public static CompoundTag Generate(int selected, int page){
        CompoundTag b = new CompoundTag();
        b.putInt("selected", selected);
        b.putInt("page", page);
        return b;
    }

    public static class ClientPayloadHandler {

        public static void handleDataOnMain(final SkillMenuPayload.SkillMenuData data, final IPayloadContext context) {
            context.enqueueWork(() -> {

            });
        }
    }

    public static class ServerPayloadHandler {

        public static void handleDataOnMain(final SkillMenuPayload.SkillMenuData data, final IPayloadContext context) {
            context.enqueueWork(() -> {
                CompoundTag nbt = data.data;
                int selected = nbt.getInt("selected");
                int page = nbt.getInt("page");
                Player player = context.player();
                Skill_Menu menu = (Skill_Menu) player.containerMenu;
                if(menu == null) return;
                menu.ChangeState(selected, page);
            });
        }
    }
}
