package com.elly.athena.item.weapon.magician;

import com.elly.athena.item.Item_Register;
import com.elly.athena.item.weapon.Weapon_Base;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ToolMaterial;

public class Wand implements Item_Register.ItemRegisterData {

    public static class Wand_Item extends Weapon_Base {
        public Wand_Item(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties) {
            super(material, attackDamage, attackSpeed, properties);
        }
    }

    @Override
    public String get_key() {
        return "wand";
    }

    @Override
    public SwordItem.Properties get_behaviour() {
        return new SwordItem.Properties()
                .stacksTo(1)
                .durability(300);
    }

    @Override
    public SwordItem get_binding(Item.Properties props) {
        return new Wand_Item(ToolMaterial.WOOD, 1, 1.8F, props);
    }
}
