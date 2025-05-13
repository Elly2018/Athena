package com.elly.athena.item.equipment.neck;

import com.elly.athena.data.types.ModEquipmentSlot;
import com.elly.athena.item.equipment.RPGEquip_Base;
import com.elly.athena.item.equipment.RPGEquip_Header;
import net.minecraft.world.item.Item;

public class EvilNecklace extends RPGEquip_Header {

    static class Evil_RPG_Equip extends RPGEquip_Base {
        public Evil_RPG_Equip(Properties properties) {
            super(properties);
            slot = ModEquipmentSlot.NECK;
        }
    }

    @Override
    public String get_key() {
        return "necklace_evil";
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new Evil_RPG_Equip(props);
    }
}
