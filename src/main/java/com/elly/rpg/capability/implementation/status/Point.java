package com.elly.rpg.capability.implementation.status;

import com.elly.rpg.capability.capability.status.ILuk;
import com.elly.rpg.capability.capability.status.IPoint;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.IntTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

public class Point implements IPoint, INBTSerializable<IntTag> {
    private int Point;

    public Point() {
        this.Point = 0;
    }

    @Override
    public int getPoint() {
        return this.Point;
    }

    @Override
    public void setPoint(int value) {
        this.Point = value;
    }

    @Override
    public void addPoint(int value) {
        this.Point += value;
    }

    @Override
    public void consumer(int value) {
        this.Point -= value;
    }

    @Override
    public IntTag serializeNBT(HolderLookup.Provider registryAccess) {
        return IntTag.valueOf(this.getPoint());
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider registryAccess, IntTag nbt) {
        if (!(nbt instanceof IntTag intNbt))
            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
        this.Point = intNbt.getAsInt();
    }
}
