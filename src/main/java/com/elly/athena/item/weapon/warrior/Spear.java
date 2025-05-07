package com.elly.athena.item.weapon.warrior;

import com.elly.athena.item.Item_Register;
import com.elly.athena.item.weapon.Weapon_Base;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ToolMaterial;

public class Spear implements Item_Register.ItemRegisterData {

    static class Spear_Item extends Weapon_Base {
        public Spear_Item(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties) {
            super(material, attackDamage, attackSpeed, properties);
        }
    }


    @Override
    public String get_key() {
        return "spear";
    }

    @Override
    public SwordItem.Properties get_behaviour() {
        return new SwordItem.Properties()
                .stacksTo(1)
                .durability(300);
    }

    @Override
    public SwordItem get_binding(Item.Properties props) {
        return new Spear_Item(ToolMaterial.IRON, 8, 1.2F, props);
    }
}
