package com.elly.athena.data.implementation;

import com.elly.athena.data.interfaceType.IBattleHotbar;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

import java.util.ArrayList;

public class BattleHotbar implements IBattleHotbar, INBTSerializable<CompoundTag> {

    private ArrayList<ItemStack> items;

    public BattleHotbar(){
        Reset();
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
        for(int i = 0; i < 10; i++){
            Tag tag = new CompoundTag();
            items.get(i).save(provider, tag);
            nbt.put(String.valueOf(i), tag);
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag compoundTag) {
        Reset();
        for(int i = 0; i < 10; i++){
            ItemStack iss = ItemStack.parseOptional(provider, compoundTag.getCompound(String.valueOf(i)));
            items.set(i, iss);
        }
    }

    private void Reset(){
        items = new ArrayList<>(10);
        for(int i = 0; i < 10; i++){
            items.add(ItemStack.EMPTY);
        }
    }
}
