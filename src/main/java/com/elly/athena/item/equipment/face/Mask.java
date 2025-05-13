package com.elly.athena.item.equipment.face;

import com.elly.athena.data.types.ModEquipmentSlot;
import com.elly.athena.item.equipment.RPGEquip_Base;
import com.elly.athena.item.equipment.RPGEquip_Header;
import net.minecraft.world.item.Item;

public class Mask extends RPGEquip_Header {

    static class Mask_RPG_Equip extends RPGEquip_Base{
        public Mask_RPG_Equip(Properties properties) {
            super(properties);
            slot = ModEquipmentSlot.FACE;
        }
    }

    @Override
    public String get_key() {
        return "face_mask";
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new Mask_RPG_Equip(props);
    }
}
