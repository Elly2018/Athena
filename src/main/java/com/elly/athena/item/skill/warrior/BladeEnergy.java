package com.elly.athena.item.skill.warrior;

import com.elly.athena.item.skill.RPGSkill_Base;
import com.elly.athena.item.skill.RPGSkill_Header;
import net.minecraft.world.item.Item;

public class BladeEnergy extends RPGSkill_Header {

    static class BladeEnergy_RPG_Skill extends RPGSkill_Base{

        public BladeEnergy_RPG_Skill(Properties properties) {
            super(properties);
        }
    }

    @Override
    public String get_key() {
        return "skill_blade_energy";
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new BladeEnergy_RPG_Skill(props);
    }
}
