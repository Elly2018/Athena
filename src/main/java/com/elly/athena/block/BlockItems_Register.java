package com.elly.athena.block;

import com.elly.athena.Athena;
import com.elly.athena.block.item.MarketBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.function.Supplier;

import static com.elly.athena.Athena.ITEMS;

public class BlockItems_Register {

    public interface BlockItemRegisterData {
        String get_key();
        Item.Properties get_behaviour();
    }

    public static HashMap<String, Supplier<BlockItem>> RegisterDict = new HashMap<String, Supplier<BlockItem>>();

    private static BlockItemRegisterData[] AllBlockItem = new BlockItemRegisterData[0];

    public static void RegisterAllItems () {
        AllBlockItem = new BlockItemRegisterData[] {
                new MarketBlock()
        };

        for (BlockItemRegisterData blockItemRegisterData : AllBlockItem) {
            String key = blockItemRegisterData.get_key();
            Item.Properties behaviour = blockItemRegisterData.get_behaviour();
            boolean hasKey = RegisterDict.containsKey(key);
            if (hasKey) {
                Supplier<Block> target = Blocks_Register.RegisterDict.get(key);
                behaviour.setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse(Athena.MODID + ":" + key)));
                Supplier<BlockItem> buffer = ITEMS.register(key, () -> new BlockItem(target.get(), behaviour));
                RegisterDict.put(key, buffer);
            }
        }
    }
}
