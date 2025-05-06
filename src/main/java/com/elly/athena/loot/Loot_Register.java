package com.elly.athena.loot;

import com.elly.athena.Athena;
import com.elly.athena.loot.entry.CoinEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.LootTableLoadEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@EventBusSubscriber(modid = Athena.MODID)
public class Loot_Register {

    public static final DeferredRegister<LootPoolEntryType> LOOT_POOL_ENTRY_TYPES = DeferredRegister.create(Registries.LOOT_POOL_ENTRY_TYPE, Athena.MODID);
    public static final Supplier<LootPoolEntryType> ENTITY_COIN_LOOT = LOOT_POOL_ENTRY_TYPES.register("entity_coin_loot", () -> new LootPoolEntryType(CoinEntry.CODEC));

    @SubscribeEvent
    public static void lootLoad(LootTableLoadEvent event) {

    }
}
