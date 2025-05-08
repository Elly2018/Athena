package com.elly.athena.item.skill;

import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.Attribute_Register;
import com.elly.athena.data.interfaceType.IPlayerStatus;
import com.elly.athena.network.SkillPayload;
import com.elly.athena.system.BattleSystem;
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

    public RPGSkill_Base(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if(skillType == SkillType.Passive) return InteractionResult.FAIL;
        IPlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
        if(ps.getMana() < requireMana(1)) return InteractionResult.FAIL;

        if(!player.isLocalPlayer()) server_apply(level, player, 1, hand);
        return InteractionResult.SUCCESS;
    }

    public void server_apply(Level world, Player player, int level, InteractionHand hand){ }
    public int requireMana(int level) { return 1; }
}
