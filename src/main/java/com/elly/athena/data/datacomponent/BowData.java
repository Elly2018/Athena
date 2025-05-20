package com.elly.athena.data.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record BowData(int time){
    public static final Codec<BowData> BASIC_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("BowData").forGetter(BowData::time)
            ).apply(instance, BowData::new)
    );
    public static final StreamCodec<ByteBuf, BowData> BASIC_STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, BowData::time,
            BowData::new
    );
}
