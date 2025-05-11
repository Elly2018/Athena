package com.elly.athena.data.interfaceType.leveldata;

import com.elly.athena.data.types.JobType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.util.INBTSerializable;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public interface INPCRegister {
    enum DialogConditionType{
        QuestFinish,
        QuestInProgress,
        LevelRequire,
    }

    interface INPCControl{
        INPCControlDialog DialogControl();
        INPCControlShop ShopControl();
    }

    interface INPCControlDialog{
        int AddCondition(DialogConditionType type);
        INPCControlDialogCondition GetCondition(int index);
        boolean RemoveCondition(int index);
        int ConditionSize();
        INPCControlDialogCondition GetTargetCondition(Player player);
    }

    interface INPCControlDialogCondition extends INBTSerializable<CompoundTag> {
        DialogConditionType GetType();
        void SetConditionQuest(UUID target);
        UUID GetConditionQuest();
        void SetConditionLevel(int level);
        int GetConditionLevel();

        INPCControlDialogContent AddContent();
        INPCControlDialogContent GetContent(int index);
        void RemoveContent(int index);
        int GetSize();
        void Clean();
    }

    interface INPCControlDialogContent {
        void SetText(String text);
        String GetText();

        void SetSelection(INPCControlDialogContentSelection data);
        INPCControlDialogContentSelection GetSelection();
    }

    interface INPCControlDialogContentSelection {
        enum SelectionType {
            Quest, Choice
        }
        void QuestSelection(String QuestId, Collection<INPCControlDialogAction> Confirm, Collection<INPCControlDialogAction> Cancel);
        void MultipleSelect(HashMap<String, Collection<INPCControlDialogAction>> options);
        SelectionType GetType();
        INPCControlDialogAction GenerateAction();
    }

    interface INPCControlDialogAction {
        enum ActionType{
            Dialog, Quest, Effect, JobChange, Shop
        }

        enum DialogActionType{
            Jump,
            Forward,
            Backward
        }

        enum QuestActionType{
            Receive, Finish, Cancel
        }

        void SetToDialog(int index);
        void SetToNextDialog();
        void SetToLastDialog();
        void SetReceivedQuest();
        void SetFinishQuest();
        void SetCancelQuest();
        void OpenShop();
        void JobChange(JobType type);
        Collection<Integer> GetFlags();
        void Execute(Player player);
    }

    interface INPCControlShop extends INBTSerializable<CompoundTag> {
        void SetCanSell(boolean value);
        boolean GetCanSell();

        void AddItem(INPCControlShopContent item);
        INPCControlShopContent GetItem(int index);
        boolean Remove(int index);
        int Size();

        void OpenShop(Player player);
        void Sell(Player player, ItemStack target);
    }

    interface INPCControlShopContent {
        void SetItem(ItemStack item);
        void SetPrice(int price);
        void Buy(Player player);
    }

    UUID CreateNPC(LivingEntity target);
    Collection<UUID> GetNPCList();
    INPCControl GetNPC(String UUID);
    int GetNPCSize();

}
