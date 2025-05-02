package com.elly.rpg.capability.capability.status;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface Str {
    int getStr();
    void setStr(int value);
    void addStr(int value);
}
