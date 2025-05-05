package com.elly.athena.gui.data;

public class LootData {
    public final String name;
    public final int color;
    public final int amount;
    public float timer;

    public LootData(String name, int color, int amount, float timer) {
        this.name = name;
        this.color = color;
        this.amount = amount;
        this.timer = timer;
    }
}
