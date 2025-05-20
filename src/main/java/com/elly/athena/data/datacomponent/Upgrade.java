package com.elly.athena.data.datacomponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record Upgrade(int upgrade){
    public static final Codec<Upgrade> BASIC_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("upgrade").forGetter(Upgrade::upgrade)
            ).apply(instance, Upgrade::new)
    );
    public static final StreamCodec<ByteBuf, Upgrade> BASIC_STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, Upgrade::upgrade,
            Upgrade::new
    );
}
