package com.elly.athena.item.equipment;

import com.elly.athena.data.types.ModEquipmentSlot;
import net.minecraft.world.item.Item;

public class RPGEquip_Base extends Item {
    public ModEquipmentSlot slot = ModEquipmentSlot.BELT;

    public RPGEquip_Base(Properties properties) {
        super(properties);
    }

    public boolean canEquip(ModEquipmentSlot target){
        return ModEquipmentSlot.checkEquipable(target.id, slot.id);
    }
}
