package com.elly.athena.data.leveldata;

import com.elly.athena.data.interfaceType.leveldata.IQuestRegister;
import com.elly.athena.data.leveldata.quest.QuestContext;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class QuestRegister extends SavedData implements IQuestRegister {

    public static QuestRegister create() {
        return new QuestRegister();
    }

    public static QuestRegister load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        QuestRegister data = QuestRegister.create();

        return data;
    }

    private final HashMap<UUID, QuestContext> context = new HashMap<>();

    @Override
    public UUID CreateQuest() {
        UUID target = UUID.randomUUID();
        context.put(target, new QuestContext());
        return target;
    }

    @Override
    public ArrayList<UUID> GetQuestList() {
        return new ArrayList<>(context.keySet());
    }

    @Override
    public IQuestContext GetQuest(UUID uuid) {
        return context.get(uuid);
    }

    @Override
    public IQuestContext GetQuest(int index) {
        UUID target = GetQuestList().get(index);
        return context.get(target);
    }

    @Override
    public UUID GetQuestUUID(int index) {
        return GetQuestList().get(index);
    }

    @Override
    public void RemoveQuest(UUID uuid) {
        context.remove(uuid);
    }

    @Override
    public int GetQuestSize() {
        return context.size();
    }

    @Override
    public CompoundTag save(@NotNull CompoundTag compoundTag, HolderLookup.@NotNull Provider provider) {
        return compoundTag;
    }
}
