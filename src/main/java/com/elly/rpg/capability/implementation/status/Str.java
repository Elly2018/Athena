package com.elly.rpg.capability.implementation.status;

import com.elly.rpg.capability.capability.IMana;
import com.elly.rpg.capability.capability.status.IStr;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.IntTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

public class Str implements IStr, INBTSerializable<IntTag> {
    private int Str;

    public Str() {
        this.Str = 1;
    }

    @Override
    public int getStr() {
        return this.Str;
    }

    @Override
    public void setStr(int value) {
        this.Str = value;
    }

    @Override
    public void addStr(int value) {
        this.Str += value;
    }

    @Override
    public IntTag serializeNBT(HolderLookup.Provider registryAccess) {
        return IntTag.valueOf(this.getStr());
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider registryAccess, IntTag nbt) {
        if (!(nbt instanceof IntTag intNbt))
            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
        this.Str = intNbt.getAsInt();
    }
}
