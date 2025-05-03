package com.elly.rpg.block;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;

public class Blocks_Register {

    public interface BlockRegisterData {
        String get_key();
        BlockBehaviour.Properties get_behaviour();
    }

    public HashMap<String, RegistryObject<Block>> RegisterDict = new HashMap<String, RegistryObject<Block>>();

    private final DeferredRegister<Block> BLOCKS;
    private final BlockRegisterData[] AllBlocks;

    public Blocks_Register(DeferredRegister<Block> _BLOCKS){
        this.BLOCKS = _BLOCKS;
        AllBlocks = new BlockRegisterData[] {
                new SymmetricAnchor()
        };
    }

    public void RegisterAllBlocks () {
        for (BlockRegisterData allBlock : AllBlocks) {
            String key = allBlock.get_key();
            BlockBehaviour.Properties behaviour = allBlock.get_behaviour();
            behaviour.setId(ResourceKey.create(ForgeRegistries.BLOCKS.getRegistryKey(), ResourceLocation.parse("rpg:" + key)));
            RegistryObject<Block> buffer = this.BLOCKS.register(key, () -> new Block(behaviour));
            this.RegisterDict.put(key, buffer);
        }
    }
}
