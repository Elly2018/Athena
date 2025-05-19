package com.elly.athena.data;

import com.elly.athena.data.component.*;
import com.elly.athena.data.datacomponent.Upgrade;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.lang.reflect.Field;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static com.elly.athena.Athena.MODID;
import static net.minecraft.core.component.DataComponentType.CODEC;

public class DataComponent_Register {
    public static final DeferredRegister.DataComponents REGISTRAR = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MODID);
    public static final Supplier<DataComponentType<Upgrade>> UPGRADE = REGISTRAR.registerComponentType(
            "upgrade",
            builder -> builder
                    .persistent(Upgrade.BASIC_CODEC)
                    .networkSynchronized(Upgrade.BASIC_STREAM_CODEC)
    );
}
