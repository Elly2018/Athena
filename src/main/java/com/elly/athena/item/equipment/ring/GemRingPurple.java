package com.elly.athena.item.equipment.ring;

import com.elly.athena.data.types.ModEquipmentSlot;
import com.elly.athena.item.equipment.RPGEquip_Base;
import com.elly.athena.item.equipment.RPGEquip_Header;
import net.minecraft.world.item.Item;

public class GemRingPurple extends RPGEquip_Header {

    static class GemRingPurple_RPG_Equip extends RPGEquip_Base {
        public GemRingPurple_RPG_Equip(Properties properties) {
            super(properties);
            slot = ModEquipmentSlot.RING0;
        }
    }

    @Override
    public String get_key() {
        return "ring_gem_purple";
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new GemRingPurple_RPG_Equip(props);
    }
}
