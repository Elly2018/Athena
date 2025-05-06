package com.elly.athena.item.weapon;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ToolMaterial;

public class Weapon_Base extends SwordItem {
    public Weapon_Base(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties) {
        super(material, attackDamage, attackSpeed, properties);
    }
}
