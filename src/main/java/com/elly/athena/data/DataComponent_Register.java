package com.elly.athena.data;

import com.elly.athena.data.component.*;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.elly.athena.Athena.MODID;

public class DataComponent_Register {
    public static final DeferredRegister.DataComponents REGISTRAR = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MODID);
    public static final Supplier<DataComponentType<ItemUpgrade.ItemUpgradeRecord>> UPGRADE = REGISTRAR.registerComponentType("upgrade", builder -> builder.persistent(ItemUpgrade.CODEC).networkSynchronized(ItemUpgrade.STREAM_CODEC));
    public static final Supplier<DataComponentType<ItemDefense.ItemDefenseRecord>> DEFENSE = REGISTRAR.registerComponentType("defense", builder -> builder.persistent(ItemDefense.CODEC).networkSynchronized(ItemDefense.STREAM_CODEC));
    public static final Supplier<DataComponentType<ItemMagicDamage.ItemMagicDamageRecord>> MAGIC_DAMAGE = REGISTRAR.registerComponentType("magic_damage", builder -> builder.persistent(ItemMagicDamage.CODEC).networkSynchronized(ItemMagicDamage.STREAM_CODEC));
    public static final Supplier<DataComponentType<ItemMagicDefense.ItemMagicDefenseRecord>> MAGIC_DEFENSE = REGISTRAR.registerComponentType("magic_defense", builder -> builder.persistent(ItemMagicDefense.CODEC).networkSynchronized(ItemMagicDefense.STREAM_CODEC));
}
