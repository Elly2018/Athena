package com.elly.athena.item.weapon.archer;

import com.elly.athena.item.Item_Register;
import com.elly.athena.item.weapon.RPGBow_Base;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;

public class MoonBow implements Item_Register.ItemRegisterData {

    static class MoonBow_Item extends RPGBow_Base {
        public MoonBow_Item(Properties p_40660_) {
            super(p_40660_);
        }
    }

    @Override
    public String get_key() {
        return "weapon_bow_moon";
    }

    @Override
    public BowItem.Properties get_behaviour() {
        return new SwordItem.Properties()
                .stacksTo(1)
                .durability(300);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new MoonBow_Item(props);
    }
}
