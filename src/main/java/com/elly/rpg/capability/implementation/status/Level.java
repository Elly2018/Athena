package com.elly.rpg.capability.implementation.status;

import com.elly.rpg.capability.capability.status.ILevel;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.IntTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

public class Level implements ILevel, INBTSerializable<IntTag> {
    private int Level;

    public Level() {
        this.Level = 1;
    }

    @Override
    public int getLevel() {
        return this.Level;
    }

    @Override
    public void setLevel(int value) {
        this.Level = value;
    }

    @Override
    public void addLevel(int value) {
        this.Level += value;
    }

    @Override
    public IntTag serializeNBT(HolderLookup.Provider registryAccess) {
        return IntTag.valueOf(this.getLevel());
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider registryAccess, IntTag nbt) {
        if (!(nbt instanceof IntTag intNbt))
            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
        this.Level = intNbt.getAsInt();
    }
}
