package com.elly.rpg.capability.implementation.status;

import com.elly.rpg.capability.capability.status.ILuk;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.IntTag;
import net.minecraftforge.common.util.INBTSerializable;

public class Luk implements ILuk, INBTSerializable<IntTag> {
    private int Luk;

    public Luk() {
        this.Luk = 1;
    }

    @Override
    public int getLuk() {
        return this.Luk;
    }

    @Override
    public void setLuk(int value) {
        this.Luk = value;
    }

    @Override
    public void addLuk(int value) {
        this.Luk += value;
    }

    @Override
    public IntTag serializeNBT(HolderLookup.Provider registryAccess) {
        return IntTag.valueOf(this.getLuk());
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider registryAccess, IntTag nbt) {
        if (!(nbt instanceof IntTag intNbt))
            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
        this.Luk = intNbt.getAsInt();
    }
}
