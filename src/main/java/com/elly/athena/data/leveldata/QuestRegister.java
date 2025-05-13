package com.elly.athena.data.leveldata;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

public class QuestRegister extends SavedData {

    public static QuestRegister create() {
        return new QuestRegister();
    }

    public static QuestRegister load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        QuestRegister data = QuestRegister.create();

        return data;
    }

    @Override
    public CompoundTag save(@NotNull CompoundTag compoundTag, HolderLookup.@NotNull Provider provider) {
        return compoundTag;
    }
}
