package com.elly.rpg.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;

public class Blocks_Register {

    public interface BlockRegisterData {
        String get_key();
        BlockBehaviour.Properties get_behaviour();
    }

    private final DeferredRegister<Block> BLOCKS;
    public HashMap<String, RegistryObject<Block>> RegisterDict = new HashMap<String, RegistryObject<Block>>();

    public Blocks_Register(DeferredRegister<Block> _BLOCKS){
        this.BLOCKS = _BLOCKS;
    }

    public void RegisterAllBlocks () {
        BlockRegisterData[] all = new BlockRegisterData[] {
          new SymmetricAnchor()
        };

        for(int i = 0; i < all.length; i++){
            String key = all[i].get_key();
            BlockBehaviour.Properties beha = all[i].get_behaviour();
            beha.setId(this.BLOCKS.key(key));
            RegistryObject<Block> buffer = this.BLOCKS.register(key, () -> new Block(beha));
            this.RegisterDict.put(key, buffer);
        }
    }
}
