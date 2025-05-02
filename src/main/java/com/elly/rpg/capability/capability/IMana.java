package com.elly.rpg.capability.capability;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface IMana {
    int getManaMaximum();
    int getMana();
    void setMana(int value);
    void addMana(int value);
}
