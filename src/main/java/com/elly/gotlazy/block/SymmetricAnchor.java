package com.elly.gotlazy.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class SymmetricAnchor implements Blocks_Register.BlockRegisterData {
    public BlockBehaviour.Properties behaviour = null;
    public final String Key = "symmetric_anchor";

    public SymmetricAnchor() {
        this.behaviour = BlockBehaviour.Properties.of()
                .mapColor(MapColor.STONE)
                .strength(1.5f);
    }

    @Override
    public String get_key() {
        return this.Key;
    }

    @Override
    public BlockBehaviour.Properties get_behaviour() {
        return this.behaviour;
    }
}
