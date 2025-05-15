package com.elly.athena.item.entity;

import com.elly.athena.item.Item_Register;
import net.minecraft.world.item.Item;

public class Entity_MagicBall implements Item_Register.ItemRegisterData {

    static class Entity_MagicBall_Item extends Item {
        public Entity_MagicBall_Item(Properties properties) {
            super(properties);
        }
    }

    @Override
    public String get_key() {
        return "entity_magicball";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties().stacksTo(1);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new Entity_MagicBall_Item(props);
    }
}
