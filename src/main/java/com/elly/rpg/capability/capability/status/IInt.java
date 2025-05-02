package com.elly.rpg.capability.capability.status;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface IInt {
    int getInt();
    void setInt(int value);
    void addInt(int value);
}