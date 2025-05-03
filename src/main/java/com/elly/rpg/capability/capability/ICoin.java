package com.elly.rpg.capability.capability;

public interface ICoin {
    int getCoin();
    boolean HaveCoin(int value);
    void setCoin(int value);
    void addCoin(int value);
    void spendCoin(int value);
}
