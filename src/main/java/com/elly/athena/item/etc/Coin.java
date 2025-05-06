package com.elly.athena.item.etc;

import com.elly.athena.item.Item_Register;
import net.minecraft.world.item.Item;

public class Coin implements Item_Register.ItemRegisterData{

    static class Coin_Item extends RPGETC_Base {

        public Coin_Item(Properties properties) {
            super(properties);
        }
    }

    @Override
    public String get_key() {
        return "coin";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties()
                .stacksTo(99)
                .overrideDescription("Coin");
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new Coin_Item(props);
    }
}
