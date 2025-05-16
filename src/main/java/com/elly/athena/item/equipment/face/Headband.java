package com.elly.athena.item.equipment.face;

import com.elly.athena.data.types.ModEquipmentSlot;
import com.elly.athena.item.equipment.RPGEquip_Base;
import com.elly.athena.item.equipment.RPGEquip_Header;
import net.minecraft.world.item.Item;

public class Headband extends RPGEquip_Header {

    static class HandBand_Item extends RPGEquip_Base{
        public HandBand_Item(Properties properties) {
            super(properties);
            slot = ModEquipmentSlot.FACE;
        }
    }

    @Override
    public String get_key() {
        return "face_headband";
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new HandBand_Item(props);
    }
}
