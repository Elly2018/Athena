package com.elly.rpg.item.potion;

import com.elly.rpg.item.Item_Register;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class HP_Potion implements Item_Register.ItemRegisterData {

    @Override
    public String get_key() {
        return "hp_potion";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties()
                .useCooldown(0);
    }
}
