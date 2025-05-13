package com.elly.athena.data.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public class ItemMagicDefense {

    public record ItemMagicDefenseRecord(int magic_defense) {}

    public static final Codec<ItemMagicDefenseRecord> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("magic_defense").forGetter(ItemMagicDefenseRecord::magic_defense)
            ).apply(instance, ItemMagicDefenseRecord::new)
    );
    public static final StreamCodec<ByteBuf, ItemMagicDefenseRecord> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, ItemMagicDefenseRecord::magic_defense,
            ItemMagicDefenseRecord::new
    );
    public static final StreamCodec<ByteBuf, ItemMagicDefenseRecord> UNIT_STREAM_CODEC = StreamCodec.unit(new ItemMagicDefenseRecord(0));
}
