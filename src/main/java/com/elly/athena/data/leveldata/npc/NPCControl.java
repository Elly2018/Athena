package com.elly.athena.data.leveldata.npc;

import com.elly.athena.data.interfaceType.leveldata.INPCRegister;
import net.minecraft.world.entity.LivingEntity;

public class NPCControl implements INPCRegister.INPCControl {
    public final LivingEntity Target;
    private final NPCControlDialog Dialog;
    private final NPCControlShop Shop;

    public NPCControl(LivingEntity target) {
        Target = target;
        Dialog = new NPCControlDialog(this);
        Shop = new NPCControlShop(this);
    }
    @Override
    public INPCRegister.INPCControlDialog DialogControl() {
        return Dialog;
    }

    @Override
    public INPCRegister.INPCControlShop ShopControl() {
        return Shop;
    }
}
