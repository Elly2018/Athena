package com.elly.athena.item.weapon.warrior;

import com.elly.athena.item.Item_Register;
import com.elly.athena.item.weapon.RPGMelee_Base;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ToolMaterial;

public class DiamondKatana implements Item_Register.ItemRegisterData {

    static class DiamondKatana_Item extends RPGMelee_Base {
        public DiamondKatana_Item(Properties properties) {
            super(properties);
        }
    }

    @Override
    public String get_key() {
        return "weapon_katana_diamond";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties()
                .stacksTo(1)
                .durability(300);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new DiamondKatana_Item(props);
    }
}
