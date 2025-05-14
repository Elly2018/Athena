package com.elly.athena.data.interfaceType.leveldata;

import net.minecraft.world.item.ItemStack;

import java.util.Collection;
import java.util.UUID;

public interface IQuestRegister {
    enum QuestType{
        Main, Side
    }

    interface IQuestContext {
        void SetType(QuestType type);
        QuestType GetType();

        void SetOrder(int order);
        int GetOrder();

        void SetReward(IQuestReward reward);
    }

    interface IQuestReward {
        void SetGold();
        boolean AddItem(ItemStack stack);
        ItemStack GetItem(int index);
        void RemoveItem(int index);
        int GetSize();
    }

    UUID CreateQuest();
    Collection<UUID> GetQuestList();
    IQuestContext GetQuest(UUID uuid);
    // To get the main quest, search 0
    // Other index are side quest
    IQuestContext GetQuest(int index);
    UUID GetQuestUUID(int index);
    void RemoveQuest(UUID uuid);
    int GetQuestSize();

}
