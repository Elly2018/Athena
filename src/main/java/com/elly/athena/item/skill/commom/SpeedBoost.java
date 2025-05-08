package com.elly.athena.item.skill.commom;

import com.elly.athena.item.Item_Register;
import com.elly.athena.item.skill.RPGSkill_Base;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.joml.Math;

public class SpeedBoost implements Item_Register.ItemRegisterData {

    static class SpeedBoost_RPG_Skill extends RPGSkill_Base {
        public SpeedBoost_RPG_Skill(Properties properties) {
            super(properties);
            this.skillType = SkillType.Active;
        }

        @Override
        public void server_apply(Level world, Player player, int level, InteractionHand hand) {
            player.addEffect(new MobEffectInstance(
                    MobEffects.MOVEMENT_SPEED, (30 + (level * 5)) * 20, (int) Math.ceil(level / 3F) - 1
            ));
        }

        @Override
        public int requireMana(int level) {
            return 3;
        }
    }

    @Override
    public String get_key() {
        return "skill.speed_boost";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties()
                .stacksTo(1);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new SpeedBoost_RPG_Skill(props);
    }
}
