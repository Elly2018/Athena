package com.elly.athena.data.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public class ItemDefense {

    public record ItemDefenseRecord(int defense) {}

    public static final Codec<ItemDefenseRecord> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("defense").forGetter(ItemDefenseRecord::defense)
            ).apply(instance, ItemDefenseRecord::new)
    );
    public static final StreamCodec<ByteBuf, ItemDefenseRecord> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, ItemDefenseRecord::defense,
            ItemDefenseRecord::new
    );
    public static final StreamCodec<ByteBuf, ItemDefenseRecord> UNIT_STREAM_CODEC = StreamCodec.unit(new ItemDefenseRecord(0));
}
