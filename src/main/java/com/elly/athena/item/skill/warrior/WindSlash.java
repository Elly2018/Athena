package com.elly.athena.item.skill.warrior;

import com.elly.athena.item.Item_Register;
import com.elly.athena.item.skill.RPGSkill_Base;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class WindSlash implements Item_Register.ItemRegisterData {

    static class WindSlash_RPG_Skill extends RPGSkill_Base {
        public WindSlash_RPG_Skill(Properties properties) {
            super(properties);
        }

        @Override
        public InteractionResult useOn(UseOnContext context) {
            return super.useOn(context);
        }
    }

    @Override
    public String get_key() {
        return "skill_wind_slash";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties()
                .stacksTo(1);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new WindSlash_RPG_Skill(props);
    }
}
