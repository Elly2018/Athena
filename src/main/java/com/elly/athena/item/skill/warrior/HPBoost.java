package com.elly.athena.item.skill.warrior;

import com.elly.athena.item.Item_Register;
import com.elly.athena.item.skill.RPGSkill_Base;
import net.minecraft.world.item.Item;

public class HPBoost implements Item_Register.ItemRegisterData {

    static class HPBoost_RPG_Skill extends RPGSkill_Base {
        public HPBoost_RPG_Skill(Properties properties) {
            super(properties);
        }
    }

    @Override
    public String get_key() {
        return "skill.hp_boost";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties()
                .stacksTo(1);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new HPBoost_RPG_Skill(props);
    }
}
