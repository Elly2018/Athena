package com.elly.athena.data.leveldata.npc;

import com.elly.athena.data.interfaceType.leveldata.INPCRegister;

import java.util.ArrayList;

public class NPCControlDialogCondition implements INPCRegister.INPCControlDialogCondition {
    public final INPCRegister.DialogConditionType type;
    public final ArrayList<INPCRegister.INPCControlDialogContent> context;

    public NPCControlDialogCondition(INPCRegister.DialogConditionType type) {
        this.type = type;
        this.context = new ArrayList<>();
    }

    @Override
    public INPCRegister.DialogConditionType GetType() {
        return type;
    }

    @Override
    public void AddContent(INPCRegister.INPCControlDialogContent _context) {
        context.add(_context);
    }

    @Override
    public void Clean() {
        context.clear();
    }
}
