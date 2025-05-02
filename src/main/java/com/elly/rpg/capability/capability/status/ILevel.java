package com.elly.rpg.capability.capability.status;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface ILevel {
    int getLevel();
    void setLevel(int value);
    void addLevel(int value);
}
