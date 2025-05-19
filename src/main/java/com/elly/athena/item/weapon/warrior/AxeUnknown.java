package com.elly.athena.item.weapon.warrior;

import com.elly.athena.item.Item_Register;
import com.elly.athena.item.weapon.RPGMelee_Base;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;

public class AxeUnknown implements Item_Register.ItemRegisterData {

    static class AxeUnknown_Item extends RPGMelee_Base {
        public AxeUnknown_Item(Properties properties) {
            super(properties);
        }
    }

    @Override
    public String get_key() {
        return "weapon_axe_unknown";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new SwordItem.Properties()
                .stacksTo(1)
                .rarity(Rarity.UNCOMMON)
                .durability(300);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new AxeUnknown_Item( props);
    }
}
