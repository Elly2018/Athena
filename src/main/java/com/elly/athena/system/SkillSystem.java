package com.elly.athena.system;

import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.attachment.IPlayerSkill;
import com.elly.athena.system.skill.SkillCategory;
import net.minecraft.world.entity.player.Player;

import java.util.Objects;

public class SkillSystem {

    public static void initCheck(Player player){
        IPlayerSkill skill = player.getData(Attachment_Register.PLAYER_SKILL);
        SkillCategory[] sc = skill.getSkills();
        boolean hasCommon = false;
        for (SkillCategory skillCategory : sc) {
            if (Objects.equals(skillCategory.Name, "common")) {
                hasCommon = true;
                break;
            }
        }
        if(!hasCommon) applyCommonSkill(skill);
    }

    private static void applyCommonSkill(IPlayerSkill target){
        target.setPoint("common", "speed_boost", 0);
        target.setPoint("common", "healing", 0);
    }
}
