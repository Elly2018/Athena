package com.elly.athena.loot;

import com.elly.athena.Athena;
import com.elly.athena.loot.modifier.CoinNormal;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.elly.athena.Athena.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class LootTable_Register {
    public static final DeferredRegister<LootPoolEntryType> LOOT_POOL_ENTRY_TYPES = DeferredRegister.create(BuiltInRegistries.LOOT_POOL_ENTRY_TYPE, MODID);
    public static final DeferredRegister<LootItemConditionType> LOOT_CONDITION_TYPE = DeferredRegister.create(BuiltInRegistries.LOOT_CONDITION_TYPE, MODID);
    public static final DeferredRegister<LootItemFunctionType<?>> LOOT_FUNCTION_TYPE = DeferredRegister.create(BuiltInRegistries.LOOT_FUNCTION_TYPE, MODID);

    public static class CoinGlobalLootModifierProvider extends GlobalLootModifierProvider {

        public CoinGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries, MODID);
        }

        @Override
        protected void start() {
            this.add(
                    "normal_coin",
                    new CoinNormal(new LootItemCondition[0]),
                    List.of(new ModLoadedCondition("create"))
            );
        }
    }

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent.Client event) {
        // Call event.createDatapackRegistryObjects(...) first if adding datapack objects

        event.createProvider(CoinGlobalLootModifierProvider::new);
    }

}
