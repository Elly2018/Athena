package com.elly.gotlazy.blockitem;

import com.elly.gotlazy.block.Blocks_Register;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class BlockItems_Register {

    public interface BlockItemRegisterData {
        String get_key();
        Item.Properties get_behaviour();
    }

    private final DeferredRegister<Item> ITEMS;
    private Blocks_Register block_register;

    public BlockItems_Register (DeferredRegister<Item> _ITEMS, Blocks_Register _block_register) {
        this.ITEMS = _ITEMS;
        this.block_register = _block_register;
    }

    public void RegisterAllItems () {
        BlockItemRegisterData[] all = new BlockItemRegisterData[] {
            new SymmetricAnchor()
        };
        for(int i = 0; i < all.length; i++){
            String key = all[i].get_key();
            Item.Properties prop = all[i].get_behaviour();
            boolean hasKey = this.block_register.RegisterDict.containsKey(key);
            if(hasKey){
                RegistryObject<Block> target = this.block_register.RegisterDict.get(key);
                this.ITEMS.register(key, () -> new BlockItem(target.get(), prop));
            }
        }
    }
}
