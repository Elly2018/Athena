package com.elly.athena.data.implementation;

import com.elly.athena.data.interfaceType.IBattleHotbar;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

public class BattleHotbar implements IBattleHotbar, INBTSerializable<CompoundTag> {

    private final NonNullList<ItemStack> items;

    public BattleHotbar(){
        items = NonNullList.withSize(9, ItemStack.EMPTY);
    }

    @Override
    public NonNullList<ItemStack> getList() {
        return items;
    }

    @Override
    public ItemStack getSlot(int index) {
        return items.get(index);
    }

    @Override
    public void setSlot(int index, ItemStack value) {
        items.set(index, value);
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        for(int i = 0; i < 9; i++){
            CompoundTag tag = new CompoundTag();
            CompoundTag itemTag = new CompoundTag();
            tag.putBoolean("is_empty", items.get(i).isEmpty());
            if(!items.get(i).isEmpty()) items.get(i).save(provider, itemTag);
            tag.put("item", itemTag);
            nbt.put(String.valueOf(i), tag);
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag compoundTag) {
        for(int i = 0; i < 9; i++){
            CompoundTag buffer = compoundTag.getCompound(String.valueOf(i));
            CompoundTag itemTag = buffer.getCompound("item");
            ItemStack iss = ItemStack.parseOptional(provider, itemTag);
            items.set(i, iss);
        }
    }
}
