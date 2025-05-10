package com.elly.athena.item.skill;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.IPlayerSkill;
import com.elly.athena.data.interfaceType.IPlayerStatus;
import com.elly.athena.data.types.JobType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class RPGSkill_Base extends Item {
    public enum SkillType {
        Passive,
        Active
    }

    public SkillType skillType = SkillType.Passive;
    public int MaxLevel = 10;
    public String Category = "common";

    public RPGSkill_Base(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level world, @NotNull Player player, @NotNull InteractionHand hand) {
        if(skillType == SkillType.Passive) {
            Athena.LOGGER.debug(String.format("Cannot use passive skill: %s %s", player.getName().getString(), descriptionId));
            return InteractionResult.FAIL;
        }
        IPlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
        IPlayerSkill pss = player.getData(Attachment_Register.PLAYER_SKILL);
        int mana_req = requireMana(1);
        if(ps.getMana() < mana_req) {
            Athena.LOGGER.debug(String.format("Player does not have enough mana: player:%d  require:%d", ps.getMana(), mana_req));
            return InteractionResult.FAIL;
        }
        JobType job = ps.getJob();
        if(!JobType.CheckJobInheritance(requireJob(), job)) {
            Athena.LOGGER.debug(String.format("Json check failed: base:%s player:%s", requireJob(), job));
            return InteractionResult.FAIL;
        }

        String skillName = descriptionId.replace("item.athena.", "");
        int level = pss.getPoint(Category, skillName);
        if(level < 0) return InteractionResult.FAIL;
        boolean CanUse = pss.CheckCooldown(Category, skillName);
        if(!CanUse) return InteractionResult.FAIL;

        if(!player.isLocalPlayer()) {
            if(!player.isCreative()){
                ps.addMana(-mana_req);
            }
            pss.SetCooldown(Category, skillName, cooldown(level));
            server_apply(world, player, level, hand);
        }
        return InteractionResult.SUCCESS;
    }

    public void server_apply(Level world, Player player, int level, InteractionHand hand) { }
    public int requireMana(int level) { return 1; }
    public int cooldown(int level) { return 200; }
    public JobType requireJob() { return JobType.NEWBIE; }
}
