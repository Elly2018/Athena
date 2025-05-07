package com.elly.athena.data.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public class ItemUpgrade {

    public record ItemUpgradeRecord(int upgrade) {}

    public static final Codec<ItemUpgradeRecord> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("upgrade").forGetter(ItemUpgradeRecord::upgrade)
            ).apply(instance, ItemUpgradeRecord::new)
    );
    public static final StreamCodec<ByteBuf, ItemUpgradeRecord> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, ItemUpgradeRecord::upgrade,
            ItemUpgradeRecord::new
    );
    public static final StreamCodec<ByteBuf, ItemUpgradeRecord> UNIT_STREAM_CODEC = StreamCodec.unit(new ItemUpgradeRecord(0));
}
