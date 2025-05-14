package com.elly.athena.data.interfaceType.attachment;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public interface IBattleHotbar {
    NonNullList<ItemStack> getList();
    ItemStack getSlot(int index);
    void setSlot(int index, ItemStack value);
}
