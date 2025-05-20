package com.elly.athena.block.block;

import com.elly.athena.block.Blocks_Register;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class MarketBlock implements Blocks_Register.BlockRegisterData {

    static class MarketBlock_Block extends Block {
        public MarketBlock_Block(Properties p_49795_) {
            super(p_49795_);
        }
    }

    @Override
    public String get_key() {
        return "market_block";
    }

    @Override
    public BlockBehaviour.Properties get_behaviour() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.STONE)
                .strength(1.5f);
    }

    @Override
    public Block get_binding(BlockBehaviour.Properties props) {
        return new Block(props);
    }
}
