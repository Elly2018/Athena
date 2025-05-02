package com.elly.rpg.capability.implementation.status;

import com.elly.rpg.capability.capability.status.IDex;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.IntTag;
import net.minecraftforge.common.util.INBTSerializable;

public class Dex implements IDex, INBTSerializable<IntTag> {
    private int Dex;

    public Dex() {
        this.Dex = 1;
    }

    @Override
    public int getDex() {
        return this.Dex;
    }

    @Override
    public void setDex(int value) {
        this.Dex = value;
    }

    @Override
    public void addDex(int value) {
        this.Dex += value;
    }

    @Override
    public IntTag serializeNBT(HolderLookup.Provider registryAccess) {
        return IntTag.valueOf(this.getDex());
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider registryAccess, IntTag nbt) {
        if (!(nbt instanceof IntTag intNbt))
            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
        this.Dex = intNbt.getAsInt();
    }
}
