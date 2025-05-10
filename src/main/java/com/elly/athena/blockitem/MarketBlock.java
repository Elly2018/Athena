package com.elly.athena.blockitem;

import net.minecraft.world.item.Item;

public class MarketBlock implements BlockItems_Register.BlockItemRegisterData {

    public static class MarketBlock_Item extends Item {
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
}
