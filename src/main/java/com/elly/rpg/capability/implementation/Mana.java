package com.elly.rpg.capability.implementation;

import com.elly.rpg.capability.capability.IMana;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.IntTag;
import net.minecraftforge.common.util.INBTSerializable;

public class Mana implements IMana, INBTSerializable<IntTag> {

    private int MaxMana;
    private int Mana;

    public Mana() {
        this.MaxMana = 10;
        this.Mana = 10;
    }

    @Override
    public int getManaMaximum() {
        return MaxMana;
    }

    @Override
    public int getMana() {
        return Mana;
    }

    @Override
    public void setMana(int value) {
        this.Mana = value;
        if(this.Mana > this.MaxMana) this.Mana = this.MaxMana;
    }

    @Override
    public void addMana(int value) {
        this.Mana += value;
        if(this.Mana > this.MaxMana) this.Mana = this.MaxMana;
    }

    @Override
    public IntTag serializeNBT(HolderLookup.Provider registryAccess) {
        return IntTag.valueOf(this.getMana());
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider registryAccess, IntTag nbt) {
        if (!(nbt instanceof IntTag intNbt))
            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
        this.Mana = intNbt.getAsInt();
    }
}
