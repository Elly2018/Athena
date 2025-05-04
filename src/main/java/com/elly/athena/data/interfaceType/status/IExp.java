package com.elly.athena.data.interfaceType.status;

public interface IExp {
    int getExp();
    int getExpMaximum(int level);
    void addExp(int value);
    void setExp(int value);
    boolean isLevelUp(int level);
}
