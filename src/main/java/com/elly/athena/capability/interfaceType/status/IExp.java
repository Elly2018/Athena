package com.elly.athena.capability.interfaceType.status;

public interface IExp {
    int getExp();
    int getExpMaximum(int level);
    void addExp(int value);
    void setExp(int value);
    boolean isLevelUp(int level);
}
