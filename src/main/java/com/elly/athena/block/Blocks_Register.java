package com.elly.athena.block;

import com.elly.athena.Athena;
import com.elly.athena.block.block.MarketBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.HashMap;
import java.util.function.Supplier;

import static com.elly.athena.Athena.BLOCKS;

public class Blocks_Register {

    public interface BlockRegisterData {
        String get_key();
        BlockBehaviour.Properties get_behaviour();
    }

    public static HashMap<String, Supplier<Block>> RegisterDict = new HashMap<String, Supplier<Block>>();

    private static BlockRegisterData[] AllBlocks = new BlockRegisterData[0];

    public static void RegisterAllBlocks () {
        AllBlocks = new BlockRegisterData[] {
                new MarketBlock()
        };

        for (BlockRegisterData allBlock : AllBlocks) {
            String key = allBlock.get_key();
            BlockBehaviour.Properties behaviour = allBlock.get_behaviour();
            behaviour.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.parse(Athena.MODID + ":" + key)));
            Supplier<Block> buffer = BLOCKS.register(key, () -> new Block(behaviour));
            RegisterDict.put(key, buffer);
        }
    }
}
