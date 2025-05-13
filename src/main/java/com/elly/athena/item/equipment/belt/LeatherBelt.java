package com.elly.athena.item.equipment.belt;

import com.elly.athena.data.types.ModEquipmentSlot;
import com.elly.athena.item.equipment.RPGEquip_Base;
import com.elly.athena.item.equipment.RPGEquip_Header;
import net.minecraft.world.item.Item;

public class LeatherBelt extends RPGEquip_Header {

    static class LeatherBelt_RPG_Equip extends RPGEquip_Base {
        public LeatherBelt_RPG_Equip(Properties properties) {
            super(properties);
            slot = ModEquipmentSlot.BELT;
        }
    }

    @Override
    public String get_key() {
        return "belt_leather";
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new LeatherBelt_RPG_Equip(props);
    }
}
