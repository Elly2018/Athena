package com.elly.athena.item.skill.warrior;

import com.elly.athena.entity.Entity_Register;
import com.elly.athena.item.Item_Register;
import com.elly.athena.item.skill.RPGSkill_Base;
import com.elly.athena.item.skill.RPGSkill_Header;
import com.elly.athena.sound.Sound_Register;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class WindSlash extends RPGSkill_Header {

    static class WindSlash_RPG_Skill extends RPGSkill_Base {
        public WindSlash_RPG_Skill(Properties properties) {
            super(properties);
            skillType = SkillType.Active;
        }

        @Override
        public void server_apply(Level world, Player player, int level, InteractionHand hand) {
            super.server_apply(world, player, level, hand);
            if(world instanceof ServerLevel serverlevel){
                for(int i = 0; i < 5; i++){
                    ItemStack iss = new ItemStack(Item_Register.ENTITY_MAGICBALL.get());
                    float f = player.getYRot();
                    com.elly.athena.entity.spell.WindSlash ws = new com.elly.athena.entity.spell.WindSlash(serverlevel, player, iss);
                    ws.shootFromRotation(player, player.getXRot(), f + ((i - 2) * 15F), 0.0F, 1.0F, 0.8F);
                    serverlevel.addFreshEntity(ws);
                    ws.applyOnProjectileSpawned(serverlevel, iss);
                }
                serverlevel.playSound(null, player.getX(), player.getY(), player.getZ(), Sound_Register.ICE.get(), SoundSource.PLAYERS, 1F, 1F);
            }
        }
    }

    @Override
    public String get_key() {
        return "skill_wind_slash";
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new WindSlash_RPG_Skill(props);
    }
}
