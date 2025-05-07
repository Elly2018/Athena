package com.elly.athena.item.weapon.magician;

import com.elly.athena.item.Item_Register;
import com.elly.athena.item.weapon.RPGMelee_Base;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ToolMaterial;

public class Staff implements Item_Register.ItemRegisterData {

    public static class Staff_Item extends RPGMelee_Base {
        public Staff_Item(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties) {
            super(material, attackDamage, attackSpeed, properties);
        }
    }

    @Override
    public String get_key() {
        return "staff";
    }

    @Override
    public SwordItem.Properties get_behaviour() {
        return new SwordItem.Properties()
                .stacksTo(1)
                .durability(300);
    }

    @Override
    public SwordItem get_binding(Item.Properties props) {
        return new Staff_Item(ToolMaterial.WOOD, 2, 1.0F, props);
    }
}
