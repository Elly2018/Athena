package com.elly.athena.data;

import com.elly.athena.data.component.ItemUpgrade;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.elly.athena.Athena.MODID;

public class DataComponent_Register {
    public static final DeferredRegister.DataComponents REGISTRAR = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MODID);
    public static final Supplier<DataComponentType<ItemUpgrade.ItemUpgradeRecord>> UPGRADE = REGISTRAR.registerComponentType("upgrade", builder -> builder.persistent(ItemUpgrade.CODEC).networkSynchronized(ItemUpgrade.STREAM_CODEC));
    public static final Supplier<DataComponentType<ItemUpgrade.ItemUpgradeRecord>> DEFENSE = REGISTRAR.registerComponentType("defense",builder -> builder.persistent(ItemUpgrade.CODEC).networkSynchronized(ItemUpgrade.STREAM_CODEC));
    public static final Supplier<DataComponentType<ItemUpgrade.ItemUpgradeRecord>> MAGIC_DAMAGE = REGISTRAR.registerComponentType("magic_damage",builder -> builder.persistent(ItemUpgrade.CODEC).networkSynchronized(ItemUpgrade.STREAM_CODEC));
    public static final Supplier<DataComponentType<ItemUpgrade.ItemUpgradeRecord>> MAGIC_DEFENSE = REGISTRAR.registerComponentType("magic_defense",builder -> builder.persistent(ItemUpgrade.CODEC).networkSynchronized(ItemUpgrade.STREAM_CODEC));
}
