package com.elly.athena.item.equipment.glove;

import com.elly.athena.data.types.ModEquipmentSlot;
import com.elly.athena.item.equipment.RPGEquip_Base;
import com.elly.athena.item.equipment.RPGEquip_Header;
import net.minecraft.world.item.Item;

public class ShinyGlove extends RPGEquip_Header {

    static class ShinyGlove_Item extends RPGEquip_Base {
        public ShinyGlove_Item(Properties properties) {
            super(properties);
            slot = ModEquipmentSlot.GLOVE;
        }
    }

    @Override
    public String get_key() {
        return "glove_shiny";
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new ShinyGlove_Item(props);
    }
}
