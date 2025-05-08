package com.elly.athena.data.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public class ItemEquip {

    public record ItemEquipRecord(int slot) {}

    public static final Codec<ItemEquipRecord> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("slot").forGetter(ItemEquipRecord::slot)
            ).apply(instance, ItemEquipRecord::new)
    );
    public static final StreamCodec<ByteBuf, ItemEquipRecord> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, ItemEquipRecord::slot,
            ItemEquipRecord::new
    );
    public static final StreamCodec<ByteBuf, ItemEquipRecord> UNIT_STREAM_CODEC = StreamCodec.unit(new ItemEquipRecord(0));
}
