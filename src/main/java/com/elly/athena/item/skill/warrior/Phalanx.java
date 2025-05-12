package com.elly.athena.item.skill.warrior;

import com.elly.athena.effect.Effect_Register;
import com.elly.athena.item.skill.RPGSkill_Base;
import com.elly.athena.item.skill.RPGSkill_Header;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.joml.Math;

public class Phalanx extends RPGSkill_Header {

    static class Phalanx_RPG_Skill extends RPGSkill_Base {

        public Phalanx_RPG_Skill(Properties properties) {
            super(properties);
            this.skillType = SkillType.Active;
        }

        @Override
        public void server_apply(Level world, Player player, int level, InteractionHand hand) {
            player.addEffect(new MobEffectInstance(
                    Effect_Register.PHALANX, 40 + (level * 4) * 20, (int) Math.ceil(level / 3F) - 1)
            );
        }

        @Override
        public int requireMana(int level) {
            return 5;
        }
    }

    @Override
    public String get_key() {
        return "skill_phalanx";
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new Phalanx_RPG_Skill(props);
    }
}
