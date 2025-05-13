package com.elly.athena.item.special.quest;

import com.elly.athena.item.Item_Register;
import net.minecraft.world.item.Item;

public class QuestStaff implements Item_Register.ItemRegisterData {

    static class QuestStaff_Item extends Item {

        public QuestStaff_Item(Properties properties) {
            super(properties);
        }
    }

    @Override
    public String get_key() {
        return "quest_setting_staff";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties()
                .stacksTo(1);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new QuestStaff_Item(props);
    }
}