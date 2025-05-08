package com.elly.athena.system;

import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.implementation.PlayerStatus;
import com.elly.athena.item.Item_Register;
import com.elly.athena.item.skill.RPGSkill_Base;
import com.elly.athena.network.SkillPayload;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.function.Supplier;

public class SkillSystem {
    public static void HandleNetworkSkillEvent(SkillPayload.SkillData data, final IPayloadContext context){
        CompoundTag nbt = data.data();
        String player = nbt.getString("player");
        String skill = nbt.getString("skill");
        int level = nbt.getInt("level");
        String target = nbt.contains("target") ? nbt.getString("target") : null;

        Player p_player = context.player();
        String skill_id = skill.replace("item.athena.", "");
        Supplier<Item> k = (Supplier<Item>) Item_Register.RegisterDict.get(skill_id);
        if(k == null) return;
        RPGSkill_Base k2 = (RPGSkill_Base) k.get();
        Level world = p_player.level();
        k2.server_apply(world, p_player, level, InteractionHand.MAIN_HAND);

        PlayerStatus ps = p_player.getData(Attachment_Register.PLAYER_STATUS);
        ps.addMana(-k2.requireMana(level));
    }
}
