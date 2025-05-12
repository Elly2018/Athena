package com.elly.athena.item.skill.warrior;

import com.elly.athena.item.Item_Register;
import com.elly.athena.item.skill.RPGSkill_Base;
import net.minecraft.world.item.Item;

public class Phalanx implements Item_Register.ItemRegisterData {

    static class Phalanx_RPG_Skill extends RPGSkill_Base{

        public Phalanx_RPG_Skill(Properties properties) {
            super(properties);
            this.skillType = SkillType.Active;
        }
    }

    @Override
    public String get_key() {
        return "skill_phalanx";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties()
                .stacksTo(1);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new Phalanx_RPG_Skill(props);
    }
}
