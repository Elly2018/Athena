package com.elly.athena.data.implementation;

import com.elly.athena.data.interfaceType.attachment.IPlayerQuest;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

import java.util.UUID;

public class PlayerQuest implements IPlayerQuest, INBTSerializable<CompoundTag> {

    @Override
    public void AddRecord(UUID target) {

    }

    @Override
    public IQuestRecord GetRecord(UUID target) {
        return null;
    }

    @Override
    public void RemoveRecord(UUID target) {

    }

    @Override
    public int Size() {
        return 0;
    }

    @Override
    public void Clean() {

    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
        return null;
    }

    @Override
    public void deserializeNBT(HolderLookup.@NotNull Provider provider, @NotNull CompoundTag compoundTag) {

    }
}
