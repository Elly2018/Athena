package com.elly.athena.block;

import com.elly.athena.Athena;
import com.elly.athena.block.block.MarketBlock;
import com.elly.athena.block.item.MarketBlockItem;
import com.elly.athena.item.Item_Register;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.HashMap;
import java.util.function.Supplier;

import static com.elly.athena.Athena.BLOCKS;
import static com.elly.athena.Athena.ITEMS;

public class Blocks_Register {
    public interface BlockRegisterData {
        String get_key();
        BlockBehaviour.Properties get_behaviour();
        Block get_binding(BlockBehaviour.Properties props);
    }

    // Block
    public static Tuple<Supplier<Block>, Supplier<BlockItem>> MARKET_BLOCK;

    public static void RegisterAllBlocks () {
        MARKET_BLOCK = RegisterBlock(new MarketBlock(), new MarketBlockItem());
    }

    private static Tuple<Supplier<Block>, Supplier<BlockItem>> RegisterBlock(BlockRegisterData allBlock, Item_Register.ItemRegisterData blockItemRegisterData){
        String key = allBlock.get_key();
        BlockBehaviour.Properties behaviour = allBlock.get_behaviour();
        behaviour.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.parse(Athena.MODID + ":" + key)));
        Supplier<Block> buffer_block = BLOCKS.register(key, () -> allBlock.get_binding(behaviour));

        String key2 = blockItemRegisterData.get_key();
        Item.Properties behaviour2 = blockItemRegisterData.get_behaviour();
        behaviour2.setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse(Athena.MODID + ":" + key2)));
        Supplier<BlockItem> buffer_block_item = ITEMS.register(key, () -> new BlockItem(buffer_block.get(), behaviour2));
        return new Tuple<>(buffer_block, buffer_block_item);
    }
}
