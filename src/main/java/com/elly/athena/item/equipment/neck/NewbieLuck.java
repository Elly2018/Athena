package com.elly.athena.item.equipment.neck;

import com.elly.athena.data.types.ModEquipmentSlot;
import com.elly.athena.item.equipment.RPGEquip_Base;
import com.elly.athena.item.equipment.RPGEquip_Header;
import net.minecraft.world.item.Item;

public class NewbieLuck extends RPGEquip_Header {

    static class NewbieLuck_RPG_Equip extends RPGEquip_Base {
        public NewbieLuck_RPG_Equip(Properties properties) {
            super(properties);
            slot = ModEquipmentSlot.NECK;
        }
    }

    @Override
    public String get_key() {
        return "necklace_newbie";
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new NewbieLuck_RPG_Equip(props);
    }
}
