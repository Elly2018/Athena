package com.elly.athena.loot.entry;

import com.elly.athena.loot.Loot_Register;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.List;
import java.util.function.Consumer;

public class CoinEntry extends LootPoolSingletonContainer {

    private final Holder<EntityType<?>> entity;
    public static final MapCodec<CoinEntry> CODEC = RecordCodecBuilder.mapCodec(inst ->
            // Add our own fields.
            inst.group( BuiltInRegistries.ENTITY_TYPE.holderByNameCodec().fieldOf("entity").forGetter(e -> e.entity) )
                    // Add common fields: weight, display, conditions, and functions.
            .and(singletonFields(inst))
            .apply(inst, CoinEntry::new)
    );

    public CoinEntry(Holder<EntityType<?>> entity, int weight, int quality, List<LootItemCondition> conditions, List<LootItemFunction> functions) {
        super(weight, quality, conditions, functions);

        this.entity = entity;
    }

    @Override
    public void createItemStack(Consumer<ItemStack> consumer, LootContext context) {
        // Get the entity's loot table. If it doesn't exist, an empty loot table will be returned, so null-checking is not necessary.
        LootTable table = context.getLevel().getServer().reloadableRegistries().getLootTable(entity.value().getDefaultLootTable().get());
        // Use the raw version here, because vanilla does it too. :P
        // #getRandomItemsRaw calls consumer#accept for us on the results of the roll.
        table.getRandomItemsRaw(context, consumer);
    }

    @Override
    public LootPoolEntryType getType() {
        return Loot_Register.ENTITY_COIN_LOOT.get();
    }

    public static LootPoolSingletonContainer.Builder<?> entityLoot(Holder<EntityType<?>> entity) {
        // Use the static simpleBuilder() method defined in LootPoolSingletonContainer.
        return simpleBuilder((weight, quality, conditions, functions) -> new CoinEntry(entity, weight, quality, conditions, functions));
    }
}
