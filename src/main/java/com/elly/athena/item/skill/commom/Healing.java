package com.elly.athena.item.skill.commom;

import com.elly.athena.item.skill.RPGSkill_Base;
import com.elly.athena.item.skill.RPGSkill_Header;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.joml.Math;

public class Healing extends RPGSkill_Header {

    static class Healing_RPG_Skill extends RPGSkill_Base {
        public Healing_RPG_Skill(Properties properties) {
            super(properties);
            this.skillType = SkillType.Active;
        }

        @Override
        public void server_apply(Level world, Player player, int level, InteractionHand hand) {
            player.addEffect(new MobEffectInstance(
                    MobEffects.HEAL, (10 + (level * 2)) * 20, (int) Math.ceil(level / 3F) - 1
            ));
        }

        @Override
        public int requireMana(int level) {
            return 5;
        }
    }

    @Override
    public String get_key() {
        return "healing";
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new Healing_RPG_Skill(props);
    }
}
