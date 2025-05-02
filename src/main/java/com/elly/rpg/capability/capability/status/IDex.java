package com.elly.rpg.capability.capability.status;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface IDex {
    int getDex();
    void setDex(int value);
    void addDex(int value);
}
