package com.elly.athena.item.etc;

import com.elly.athena.item.Item_Register;
import net.minecraft.world.item.Item;

public class CoinBag implements Item_Register.ItemRegisterData{

    static class Coin_Item extends RPGETC_Base {

        public Coin_Item(Properties properties) {
            super(properties);
        }
    }

    @Override
    public String get_key() {
        return "coin_bag";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties()
                .stacksTo(99);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new Coin_Item(props);
    }
}
