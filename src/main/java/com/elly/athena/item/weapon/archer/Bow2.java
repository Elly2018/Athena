package com.elly.athena.item.weapon.archer;

import com.elly.athena.item.Item_Register;
import com.elly.athena.item.weapon.RPGBow_Base;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;

public class Bow2 implements Item_Register.ItemRegisterData {

    static class Bow2_Item extends RPGBow_Base {
        public Bow2_Item(Properties properties) {
            super(properties);
        }
    }

    @Override
    public String get_key() {
        return "weapon_bow2";
    }

    @Override
    public BowItem.Properties get_behaviour() {
        return new SwordItem.Properties()
                .stacksTo(1)
                .durability(300);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new Bow2_Item(props);
    }
}
