package com.elly.athena.data.interfaceType;

import net.minecraft.world.item.ItemStack;

public interface IBattleHotbar {
    ItemStack getSlot(int index);
    void setSlot(int index, ItemStack value);
}
