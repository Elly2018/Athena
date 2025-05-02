package com.elly.rpg.capability.capability.status;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface RPGLevel {
    int getLevel();
    void setLevel(int value);
    void addLevel(int value);
}
