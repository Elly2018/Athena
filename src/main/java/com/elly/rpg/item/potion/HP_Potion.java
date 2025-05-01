package com.elly.rpg.item.potion;

import com.elly.rpg.item.Item_Register;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class HP_Potion implements Item_Register.ItemRegisterData {

    @Override
    public String get_key() {
        return "hp_potion";
    }

    @Override
    public Item.Properties get_behaviour() {
        List<ItemAttributeModifiers.Entry> modifiers = new ArrayList<>();
        return new Item.Properties()
                .overrideDescription("")
                .useCooldown(0);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new HP_Potion_Item(props);
    }
}
