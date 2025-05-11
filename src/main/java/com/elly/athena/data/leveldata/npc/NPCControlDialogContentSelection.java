package com.elly.athena.data.leveldata.npc;

import com.elly.athena.data.interfaceType.leveldata.INPCRegister;

import java.util.Collection;
import java.util.HashMap;

public class NPCControlDialogContentSelection implements INPCRegister.INPCControlDialogContentSelection{
    private SelectionType meta = SelectionType.Choice;
    private String Quest;
    private Collection<INPCRegister.INPCControlDialogAction> Confirm;
    private Collection<INPCRegister.INPCControlDialogAction> Cancel;
    private HashMap<String, Collection<INPCRegister.INPCControlDialogAction>> options;
    private final NPCControl control;

    public NPCControlDialogContentSelection(NPCControl control) {
        this.control = control;
    }

    @Override
    public void QuestSelection(String QuestId, Collection<INPCRegister.INPCControlDialogAction> Confirm, Collection<INPCRegister.INPCControlDialogAction> Cancel) {
        meta = SelectionType.Quest;
        Quest = QuestId;
        this.Confirm = Confirm;
        this.Cancel = Cancel;
    }

    @Override
    public void MultipleSelect(HashMap<String, Collection<INPCRegister.INPCControlDialogAction>> options) {
        meta = SelectionType.Choice;
        this.options = options;
    }

    @Override
    public SelectionType GetType() {
        return meta;
    }

    @Override
    public INPCRegister.INPCControlDialogAction GenerateAction() {
        return new NPCControlDialogAction(this, control);
    }
}
