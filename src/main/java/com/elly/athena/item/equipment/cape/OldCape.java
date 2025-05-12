package com.elly.athena.item.equipment.cape;

import com.elly.athena.data.types.ModEquipmentSlot;
import com.elly.athena.item.equipment.RPGEquip_Base;
import com.elly.athena.item.equipment.RPGEquip_Header;
import net.minecraft.world.item.Item;

public class OldCape extends RPGEquip_Header {

    static class OldCape_RPG_Equip extends RPGEquip_Base {
        public OldCape_RPG_Equip(Properties properties) {
            super(properties);
            slot = ModEquipmentSlot.CAPE;
        }
    }

    @Override
    public String get_key() {
        return "cape_old";
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new OldCape_RPG_Equip(props);
    }
}
