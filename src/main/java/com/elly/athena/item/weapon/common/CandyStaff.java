package com.elly.athena.item.weapon.common;

import com.elly.athena.item.Item_Register;
import com.elly.athena.item.weapon.RPGMelee_Base;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ToolMaterial;

public class CandyStaff implements Item_Register.ItemRegisterData {
    static class CandyStaff_Item extends RPGMelee_Base {
        public CandyStaff_Item(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties) {
            super(material, attackDamage, attackSpeed, properties);
        }
    }

    @Override
    public String get_key() {
        return "weapon_candy";
    }

    @Override
    public BowItem.Properties get_behaviour() {
        return new SwordItem.Properties()
                .stacksTo(1)
                .durability(300);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new CandyStaff_Item(ToolMaterial.GOLD, 5, -2.5F, props);
    }
}
