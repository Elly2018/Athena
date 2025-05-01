package com.elly.rpg.blockitem;

import com.elly.rpg.block.Blocks_Register;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;

public class BlockItems_Register {

    public interface BlockItemRegisterData {
        String get_key();
        Item.Properties get_behaviour();
    }

    public HashMap<String, RegistryObject<BlockItem>> RegisterDict = new HashMap<String, RegistryObject<BlockItem>>();

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
                RegistryObject<Block> target = this.BlockRegister.RegisterDict.get(key);
                RegistryObject<BlockItem> buffer = this.ITEMS.register(key, () -> new BlockItem(target.get(), behaviour.setId(ITEMS.key(key))));
                this.RegisterDict.put(key, buffer);
            }
        }
    }
}
