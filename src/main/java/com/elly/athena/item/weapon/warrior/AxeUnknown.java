package com.elly.athena.item.weapon.warrior;

import com.elly.athena.item.Item_Register;
import com.elly.athena.item.weapon.RPGMelee_Base;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ToolMaterial;

public class AxeUnknown implements Item_Register.ItemRegisterData {

    static class AxeUnknown_Item extends RPGMelee_Base {
        public AxeUnknown_Item(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties) {
            super(material, attackDamage, attackSpeed, properties);
        }
    }

    @Override
    public String get_key() {
        return "weapon_axe_unknown";
    }

    @Override
    public SwordItem.Properties get_behaviour() {
        return new SwordItem.Properties()
                .stacksTo(1)
                .durability(300);
    }

    @Override
    public SwordItem get_binding(Item.Properties props) {
        return new AxeUnknown_Item(ToolMaterial.IRON, 6, -3.4F, props);
    }
}
