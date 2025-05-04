package com.elly.athena.blockitem;

import net.minecraft.world.item.Item;

public class SymmetricAnchor implements BlockItems_Register.BlockItemRegisterData {

    @Override
    public String get_key() {
        return "symmetric_anchor";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties();
    }
}
