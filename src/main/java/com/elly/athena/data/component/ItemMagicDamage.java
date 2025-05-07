package com.elly.athena.data.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public class ItemMagicDamage {

    public record ItemMagicDamageRecord(int magic_damage) {}

    public static final Codec<ItemMagicDamageRecord> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("upgrade").forGetter(ItemMagicDamageRecord::magic_damage)
            ).apply(instance, ItemMagicDamageRecord::new)
    );
    public static final StreamCodec<ByteBuf, ItemMagicDamageRecord> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, ItemMagicDamageRecord::magic_damage,
            ItemMagicDamageRecord::new
    );
    public static final StreamCodec<ByteBuf, ItemMagicDamageRecord> UNIT_STREAM_CODEC = StreamCodec.unit(new ItemMagicDamageRecord(0));
}
