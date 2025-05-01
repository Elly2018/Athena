package com.elly.rpg.item.potion;

import com.elly.rpg.item.Item_Register;
import net.minecraft.world.item.Item;

public class MP_Potion implements Item_Register.ItemRegisterData {
    @Override
    public String get_key() {
        return "mp_potion";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties()
                .useCooldown(0);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new MP_Potion_Item(props);
    }
}

