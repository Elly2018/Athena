package com.elly.athena.item.use.book;

import com.elly.athena.item.Item_Register;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class WarriorSkillBook implements Item_Register.ItemRegisterData {

    static class WarriorSkillBook_Item extends RPGBook_Base{

        public WarriorSkillBook_Item(Properties properties) {
            super(properties);
        }
    }

    @Override
    public String get_key() {
        return "book_warrior";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties()
                .rarity(Rarity.UNCOMMON);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new WarriorSkillBook_Item(props);
    }
}
