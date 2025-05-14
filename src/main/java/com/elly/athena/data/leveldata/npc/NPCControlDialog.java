package com.elly.athena.data.leveldata.npc;

import com.elly.athena.data.interfaceType.leveldata.INPCRegister;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;

public class NPCControlDialog implements INPCRegister.INPCControlDialog{
    private final ArrayList<NPCControlDialogCondition> Condition = new ArrayList<>();
    private final NPCControl control;

    public NPCControlDialog(NPCControl control) {
        this.control = control;
    }

    @Override
    public int AddCondition(INPCRegister.DialogConditionType type) {
        Condition.add(new NPCControlDialogCondition(type, control));
        return Condition.size() - 1;
    }

    @Override
    public INPCRegister.INPCControlDialogCondition GetCondition(int index) {
        return Condition.get(index);
    }

    @Override
    public boolean RemoveCondition(int index) {
        return Condition.remove(index) != null;
    }

    @Override
    public int ConditionSize() {
        return Condition.size();
    }

    @Override
    public INPCRegister.INPCControlDialogCondition GetTargetCondition(Player player) {
        return null;
    }
}
