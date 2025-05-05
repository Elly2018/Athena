package com.elly.athena.block;

import com.elly.athena.Athena;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.function.Supplier;

public class Blocks_Register {

    public interface BlockRegisterData {
        String get_key();
        BlockBehaviour.Properties get_behaviour();
    }

    public HashMap<String, Supplier<Block>> RegisterDict = new HashMap<String, Supplier<Block>>();

    private final DeferredRegister<Block> BLOCKS;
    private final BlockRegisterData[] AllBlocks;

    public Blocks_Register(DeferredRegister<Block> _BLOCKS){
        this.BLOCKS = _BLOCKS;
        AllBlocks = new BlockRegisterData[] {
                new MarketBlock()
        };
    }

    public void RegisterAllBlocks () {
        for (BlockRegisterData allBlock : AllBlocks) {
            String key = allBlock.get_key();
            BlockBehaviour.Properties behaviour = allBlock.get_behaviour();
            behaviour.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.parse(Athena.MODID + ":" + key)));
            Supplier<Block> buffer = this.BLOCKS.register(key, () -> new Block(behaviour));
            this.RegisterDict.put(key, buffer);
        }
    }
}
