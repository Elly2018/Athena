package com.elly.athena.data.leveldata.npc;

import com.elly.athena.data.interfaceType.leveldata.INPCRegister;

import java.util.Collection;
import java.util.HashMap;

public class NPCControlDialogContentSelection implements INPCRegister.INPCControlDialogContentSelection{

    public int meta;
    public String Quest;
    public Collection<INPCRegister.INPCControlDialogAction> Confirm;
    public Collection<INPCRegister.INPCControlDialogAction> Cancel;
    public HashMap<String, Collection<INPCRegister.INPCControlDialogAction>> options;

    @Override
    public void QuestSelection(String QuestId, Collection<INPCRegister.INPCControlDialogAction> Confirm, Collection<INPCRegister.INPCControlDialogAction> Cancel) {
        meta = 0;
        Quest = QuestId;
        this.Confirm = Confirm;
        this.Cancel = Cancel;
    }

    @Override
    public void MultipleSelect(HashMap<String, Collection<INPCRegister.INPCControlDialogAction>> options) {
        meta = 1;
        this.options = options;
    }

    @Override
    public INPCRegister.INPCControlDialogAction GenerateAction() {
        return new NPCControlDialogAction(this);
    }
}
