package com.elly.athena.item.skill.magician;

import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.attachment.IEntityRPGRecord;
import com.elly.athena.entity.Entity_Register;
import com.elly.athena.item.Item_Register;
import com.elly.athena.item.skill.RPGSkill_Base;
import com.elly.athena.item.skill.RPGSkill_Header;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MagicBall extends RPGSkill_Header {

    static class MagicBall_RPG_Skill extends RPGSkill_Base {
        public MagicBall_RPG_Skill(Properties properties) {
            super(properties);
            this.skillType = SkillType.Active;
        }

        @Override
        public void server_apply(Level world, Player player, int level, InteractionHand hand) {
            super.server_apply(world, player, level, hand);

            if(world instanceof ServerLevel serverlevel){
                ItemStack iss = new ItemStack(Item_Register.RegisterDict.get("entity_magicball").get());
                Projectile.spawnProjectileFromRotation(
                        com.elly.athena.entity.spell.MagicBall::new,
                        serverlevel,
                        iss,
                        player,
                        0.0F,
                        1.0F,
                        0.8F
                );
            }
        }

        @Override
        public int requireMana(int level) {
            return 1;
        }
    }

    @Override
    public String get_key() {
        return "skill_magic_ball";
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new MagicBall_RPG_Skill(props);
    }
}
