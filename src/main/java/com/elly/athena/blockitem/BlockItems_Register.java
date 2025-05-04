package com.elly.athena.blockitem;

import com.elly.athena.block.Blocks_Register;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.function.Supplier;

public class BlockItems_Register {

    public interface BlockItemRegisterData {
        String get_key();
        Item.Properties get_behaviour();
    }

    public HashMap<String, Supplier<BlockItem>> RegisterDict = new HashMap<String, Supplier<BlockItem>>();

    private final DeferredRegister<Item> ITEMS;
    private final Blocks_Register BlockRegister;
    private final BlockItemRegisterData[] AllBlockItem;

    public BlockItems_Register (DeferredRegister<Item> _ITEMS, Blocks_Register _block_register) {
        this.ITEMS = _ITEMS;
        this.BlockRegister = _block_register;
        AllBlockItem = new BlockItemRegisterData[] {
                new SymmetricAnchor()
        };
    }

    public void RegisterAllItems () {
        for (BlockItemRegisterData blockItemRegisterData : AllBlockItem) {
            String key = blockItemRegisterData.get_key();
            Item.Properties behaviour = blockItemRegisterData.get_behaviour();
            boolean hasKey = this.BlockRegister.RegisterDict.containsKey(key);
            if (hasKey) {
                Supplier<Block> target = this.BlockRegister.RegisterDict.get(key);
                behaviour.setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse("rpg:" + key)));
                Supplier<BlockItem> buffer = this.ITEMS.register(key, () -> new BlockItem(target.get(), behaviour));
                this.RegisterDict.put(key, buffer);
            }
        }
    }
}
