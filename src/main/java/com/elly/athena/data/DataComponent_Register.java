package com.elly.athena.data;

import com.elly.athena.data.component.ItemStatus;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.elly.athena.Athena.MODID;

public class DataComponent_Register {
    public static final DeferredRegister.DataComponents REGISTRAR = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MODID);
    public static final Supplier<DataComponentType<ItemStatus.ItemStatusRecord>> ITEM_STATUS = REGISTRAR.registerComponentType(
            "upgrade",
            builder -> builder
                    // The codec to read/write the data to disk
                    .persistent(ItemStatus.CODEC)
                    // The codec to read/write the data across the network
                    .networkSynchronized(ItemStatus.STREAM_CODEC)
    );

}
