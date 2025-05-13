package com.elly.athena.item.special.setting;

import com.elly.athena.item.Item_Register;
import net.minecraft.world.item.Item;

public class TeleportStaff implements Item_Register.ItemRegisterData {

    static class TeleportStaff_Item extends Item {

        public TeleportStaff_Item(Properties properties) {
            super(properties);
        }
    }

    @Override
    public String get_key() {
        return "teleport_setting_staff";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties()
                .stacksTo(1);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new TeleportStaff_Item(props);
    }
}