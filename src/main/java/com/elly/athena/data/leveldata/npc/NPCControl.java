package com.elly.athena.data.leveldata.npc;

import com.elly.athena.data.interfaceType.leveldata.INPCRegister;
import net.minecraft.world.entity.LivingEntity;

public class NPCControl implements INPCRegister.INPCControl {
    public final LivingEntity Target;
    public final NPCControlDialog Dialog;

    public NPCControl(LivingEntity target) {
        Target = target;
        Dialog = new NPCControlDialog();
    }
    @Override
    public INPCRegister.INPCControlDialog DialogControl() {
        return Dialog;
    }
}
