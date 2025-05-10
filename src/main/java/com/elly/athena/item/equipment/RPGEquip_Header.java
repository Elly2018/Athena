package com.elly.athena.item.equipment;

import com.elly.athena.item.Item_Register;
import net.minecraft.world.item.Item;

public abstract class RPGEquip_Header implements Item_Register.ItemRegisterData {

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties()
                .stacksTo(1);
    }
}
