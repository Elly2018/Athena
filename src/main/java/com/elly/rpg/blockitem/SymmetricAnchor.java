package com.elly.rpg.blockitem;

import net.minecraft.world.item.Item;

public class SymmetricAnchor implements BlockItems_Register.BlockItemRegisterData {

    public Item.Properties behaviour = null;
    public final String Key = "symmetric_anchor";

    public SymmetricAnchor() {
        this.behaviour = new Item.Properties();
    }

    @Override
    public String get_key() {
        return "";
    }

    @Override
    public Item.Properties get_behaviour() {
        return null;
    }
}
