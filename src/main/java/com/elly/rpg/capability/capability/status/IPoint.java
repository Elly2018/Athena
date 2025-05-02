package com.elly.rpg.capability.capability.status;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface IPoint {
    int getPoint();
    void setPoint(int value);
    void addPoint(int value);
    void consumer(int value);
}
