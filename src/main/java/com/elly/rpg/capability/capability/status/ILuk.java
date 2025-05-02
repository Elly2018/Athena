package com.elly.rpg.capability.capability.status;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface ILuk {
    int getLuk();
    void setLuk(int value);
    void addLuk(int value);
}
