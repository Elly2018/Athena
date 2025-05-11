package com.elly.athena.data.interfaceType.leveldata;

import net.minecraft.world.entity.LivingEntity;

import java.util.Collection;
import java.util.HashMap;

public interface INPCRegister {
    enum DialogConditionType{
        LoevelRequire,
        Quest
    }

    interface INPCControl{
        INPCControlDialog DialogControl();
    }

    interface INPCControlDialog{
        int AddCondition(DialogConditionType type);
        INPCControlDialogCondition GetCondition(int index);
        void RemoveCondition(int index);
        int ConditionSize();
    }

    interface INPCControlDialogCondition {
        DialogConditionType GetType();
        void AddContent(INPCControlDialogContent context);
        void Clean();
    }

    interface INPCControlDialogContent {
        void SetText(String text);
        void SetSelection(INPCControlDialogContentSelection data);
    }

    interface INPCControlDialogContentSelection {
        void QuestSelection(String QuestId, Collection<INPCControlDialogAction> Confirm, Collection<INPCControlDialogAction> Cancel);
        void MultipleSelect(HashMap<String, Collection<INPCControlDialogAction>> options);
        INPCControlDialogAction GenerateAction();
    }

    interface INPCControlDialogAction {
        void SetToDialog(int index);
        void SetToNextDialog();
        void SetToLastDialog();
        void SetReceivedQuest();
        void SetFinishQuest();
        void SetCancelQuest();
    }

    boolean CreateNPC(String UUID, LivingEntity target);
    INPCControl GetNPC(String UUID);
    int GetNPCSize();

}
