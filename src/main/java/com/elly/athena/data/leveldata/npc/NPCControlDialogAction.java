package com.elly.athena.data.leveldata.npc;

import com.elly.athena.data.interfaceType.leveldata.INPCRegister;

public class NPCControlDialogAction implements INPCRegister.INPCControlDialogAction {

    enum ActionType{
        Dialog, Quest, Effect, JobChange
    }

    enum DialogActionType{
        Jump,
        Forward,
        Backward
    }

    enum QuestActionType{
        Receive, Finish, Cancel
    }

    public ActionType type;
    public int meta0;
    public int meta1;
    public final NPCControlDialogContentSelection selection;

    public NPCControlDialogAction(NPCControlDialogContentSelection selection) {
        this.selection = selection;
    }

    @Override
    public void SetToDialog(int index) {
        type = ActionType.Dialog;
        meta0 = DialogActionType.Jump.ordinal();
        meta1 = index;
    }

    @Override
    public void SetToNextDialog() {
        type = ActionType.Dialog;
        meta0 = DialogActionType.Forward.ordinal();
    }

    @Override
    public void SetToLastDialog() {
        type = ActionType.Dialog;
        meta0 = DialogActionType.Backward.ordinal();
    }

    @Override
    public void SetReceivedQuest() {
        type = ActionType.Quest;
        meta0 = QuestActionType.Receive.ordinal();
    }

    @Override
    public void SetFinishQuest() {
        type = ActionType.Quest;
        meta0 = QuestActionType.Finish.ordinal();
    }

    @Override
    public void SetCancelQuest() {
        type = ActionType.Quest;
        meta0 = QuestActionType.Cancel.ordinal();
    }
}
