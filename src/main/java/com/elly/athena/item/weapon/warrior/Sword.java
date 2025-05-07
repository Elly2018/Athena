package com.elly.athena.item.weapon.warrior;

import com.elly.athena.item.Item_Register;
import com.elly.athena.item.weapon.Weapon_Base;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ToolMaterial;

public class Sword implements Item_Register.ItemRegisterData{

    static class Sword_Item extends Weapon_Base {
        public Sword_Item(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties) {
            super(material, attackDamage, attackSpeed, properties);
        }
    }


    @Override
    public String get_key() {
        return "sword";
    }

    @Override
    public SwordItem.Properties get_behaviour() {
        return new SwordItem.Properties()
                .stacksTo(1)
                .equippable(EquipmentSlot.CODEC)
                .durability(300);
    }

    @Override
    public SwordItem get_binding(Item.Properties props) {
        return new Sword_Item(ToolMaterial.IRON, 6, 1.6F, props);
    }
}
