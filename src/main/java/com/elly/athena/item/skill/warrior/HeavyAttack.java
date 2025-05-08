package com.elly.athena.item.skill.warrior;

import com.elly.athena.item.Item_Register;
import com.elly.athena.item.skill.RPGSkill_Base;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class HeavyAttack implements Item_Register.ItemRegisterData {

    static class HeavyAttack_RPG_Skill extends RPGSkill_Base {
        public HeavyAttack_RPG_Skill(Properties properties) {
            super(properties);
        }

        @Override
        public InteractionResult useOn(UseOnContext context) {
            return super.useOn(context);
        }
    }

    @Override
    public String get_key() {
        return "skill.heavy_attack";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties()
                .stacksTo(1);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new HeavyAttack_RPG_Skill(props);
    }
}
