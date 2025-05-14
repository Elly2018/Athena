package com.elly.athena.item.equipment.orb;

import com.elly.athena.data.types.ModEquipmentSlot;
import com.elly.athena.item.equipment.RPGEquip_Base;
import com.elly.athena.item.equipment.RPGEquip_Header;
import net.minecraft.world.item.Item;

public class EvilOrb extends RPGEquip_Header {

    static class EvilOrb_RPG_Equip extends RPGEquip_Base{
        public EvilOrb_RPG_Equip(Properties properties) {
            super(properties);
            slot = ModEquipmentSlot.ORB;
        }
    }

    @Override
    public String get_key() {
        return "orb_evil";
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new EvilOrb_RPG_Equip(props);
    }
}
