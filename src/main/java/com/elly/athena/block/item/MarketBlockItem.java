package com.elly.athena.block.item;

import com.elly.athena.item.Item_Register;
import net.minecraft.world.item.Item;

public class MarketBlockItem implements Item_Register.ItemRegisterData {

    static class MarketBlock_Item extends Item {
        public MarketBlock_Item(Properties properties) {
            super(properties);
        }
    }

    @Override
    public String get_key() {
        return "market_block";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties();
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return null;
    }
}
