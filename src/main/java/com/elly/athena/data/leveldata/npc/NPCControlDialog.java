package com.elly.athena.data.leveldata.npc;

import com.elly.athena.data.interfaceType.leveldata.INPCRegister;

import java.util.ArrayList;

public class NPCControlDialog implements INPCRegister.INPCControlDialog{
    private final ArrayList<NPCControlDialogCondition> Condition = new ArrayList<>();

    @Override
    public int AddCondition(INPCRegister.DialogConditionType type) {
        Condition.add(new NPCControlDialogCondition(type));
        return Condition.size() - 1;
    }

    @Override
    public INPCRegister.INPCControlDialogCondition GetCondition(int index) {
        return Condition.get(index);
    }

    @Override
    public void RemoveCondition(int index) {
        Condition.remove(index);
    }

    @Override
    public int ConditionSize() {
        return Condition.size();
    }
}
