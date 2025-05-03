package com.elly.rpg.capability.implementation;

import com.elly.rpg.capability.capability.ICoin;
import com.elly.rpg.capability.capability.IMana;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.IntTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

public class Coin implements ICoin, INBTSerializable<IntTag> {
    private int Coin;

    @Override
    public int getCoin() {
        return this.Coin;
    }

    @Override
    public boolean HaveCoin(int value){
        return this.Coin >= value;
    }

    @Override
    public void setCoin(int value) {
        this.Coin = value;
    }

    @Override
    public void addCoin(int value) {
        this.Coin += value;
    }

    @Override
    public void spendCoin(int value) {
        this.Coin -= value;
    }

    @Override
    public IntTag serializeNBT(HolderLookup.Provider registryAccess) {
        return IntTag.valueOf(this.getCoin());
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider registryAccess, IntTag nbt) {
        if (!(nbt instanceof IntTag intNbt))
            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
        this.Coin = intNbt.getAsInt();
    }
}
