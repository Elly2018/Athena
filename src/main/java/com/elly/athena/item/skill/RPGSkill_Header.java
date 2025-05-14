package com.elly.athena.item.skill;

import com.elly.athena.item.Item_Register;
import net.minecraft.world.item.Item;

public abstract class RPGSkill_Header implements Item_Register.ItemRegisterData {

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties()
                .durability(100)
                .stacksTo(1);
    }
}
