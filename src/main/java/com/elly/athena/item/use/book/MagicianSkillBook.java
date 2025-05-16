package com.elly.athena.item.use.book;

import com.elly.athena.item.Item_Register;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class MagicianSkillBook implements Item_Register.ItemRegisterData {

    static class MagicianSkillBook_Item extends RPGBook_Base{

        public MagicianSkillBook_Item(Properties properties) {
            super(properties);
        }
    }

    @Override
    public String get_key() {
        return "book_magician";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties()
                .rarity(Rarity.UNCOMMON);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new MagicianSkillBook_Item(props);
    }
}
