package com.elly.rpg.capability.capability;

public interface IExp {
    int getExp();
    int getExpMaximum(int level);
    void addExp(int value);
    void setExp(int value);
    boolean isLevelUp(int level);
}
