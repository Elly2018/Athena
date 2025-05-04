package com.elly.athena.data.interfaceType.status;

public interface IExp {
    float getExpProgress(int level);
    int getExp();
    int getExpMaximum(int level);
    void addExp(int value);
    void setExp(int value);
    boolean isLevelUp(int level);
}
