package com.elly.rpg.capability.implementation.status;

import com.elly.rpg.capability.capability.status.IInt;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.IntTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

public class Int implements IInt, INBTSerializable<IntTag> {
    private int Int;

    public Int() {
        this.Int = 1;
    }

    @Override
    public int getInt() {
        return this.Int;
    }

    @Override
    public void setInt(int value) {
        this.Int = value;
    }

    @Override
    public void addInt(int value) {
        this.Int += value;
    }

    @Override
    public IntTag serializeNBT(HolderLookup.Provider registryAccess) {
        return IntTag.valueOf(this.getInt());
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider registryAccess, IntTag nbt) {
        if (!(nbt instanceof IntTag intNbt))
            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
        this.Int = intNbt.getAsInt();
    }
}
