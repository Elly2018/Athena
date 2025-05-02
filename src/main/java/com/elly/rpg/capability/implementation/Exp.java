package com.elly.rpg.capability.implementation;

import com.elly.rpg.capability.capability.IExp;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.IntTag;
import net.minecraftforge.common.util.INBTSerializable;

public class Exp implements IExp, INBTSerializable<IntTag> {
    private int Exp;

    @Override
    public int getExp() {
        return this.Exp;
    }

    @Override
    public int getExpMaximum(int level) {
        return (int)(Math.pow((double)(level * 100), (double)1.27F));
    }

    @Override
    public void addExp(int value) {
        this.Exp += value;
    }

    @Override
    public void setExp(int value) {
        this.Exp = value;
    }

    @Override
    public boolean isLevelUp(int level) {
        int thr = this.getExpMaximum(level);
        return this.Exp >= thr;
    }

    @Override
    public IntTag serializeNBT(HolderLookup.Provider registryAccess) {
        return IntTag.valueOf(this.getExp());
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider registryAccess, IntTag nbt) {
        if (!(nbt instanceof IntTag intNbt))
            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
        this.Exp = intNbt.getAsInt();
    }
}
