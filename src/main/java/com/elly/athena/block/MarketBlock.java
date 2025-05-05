package com.elly.athena.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class MarketBlock implements Blocks_Register.BlockRegisterData {
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
}
