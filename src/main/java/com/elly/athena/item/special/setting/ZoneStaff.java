package com.elly.athena.item.special.setting;

import com.elly.athena.item.Item_Register;
import net.minecraft.world.item.Item;

public class ZoneStaff implements Item_Register.ItemRegisterData {

    static class ZoneStaff_Item extends Item {

        public ZoneStaff_Item(Properties properties) {
            super(properties);
        }
    }

    @Override
    public String get_key() {
        return "zone_setting_staff";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties()
                .stacksTo(1);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new ZoneStaff_Item(props);
    }
}