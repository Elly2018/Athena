package com.elly.athena.data.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public class ItemStatus {

    public record ItemStatusRecord(int upgrade) {}

    public static final Codec<ItemStatusRecord> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("upgrade").forGetter(ItemStatusRecord::upgrade)
            ).apply(instance, ItemStatusRecord::new)
    );
    public static final StreamCodec<ByteBuf, ItemStatusRecord> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, ItemStatusRecord::upgrade,
            ItemStatusRecord::new
    );
    public static final StreamCodec<ByteBuf, ItemStatusRecord> UNIT_STREAM_CODEC = StreamCodec.unit(new ItemStatusRecord(0));
}
