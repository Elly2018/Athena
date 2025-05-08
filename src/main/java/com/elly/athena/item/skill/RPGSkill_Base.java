package com.elly.athena.item.skill;

import com.elly.athena.network.SkillPayload;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

public class RPGSkill_Base extends Item {
    public RPGSkill_Base(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        PacketDistributor.sendToServer(new SkillPayload.SkillData(
                SkillPayload.Generate(player.getUUID().toString(), descriptionId, 1)
        ));
        return super.use(level, player, hand);
    }
}
