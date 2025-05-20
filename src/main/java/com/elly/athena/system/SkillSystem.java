package com.elly.athena.system;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.attachment.IPlayerSkill;
import com.elly.athena.item.skill.RPGSkill_Base;
import com.elly.athena.system.skill.SkillCategory;
import net.minecraft.util.Tuple;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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

    public static void ApplyChange(@NotNull Player player){
        AttributeMap map = player.getAttributes();
        ArrayList<Tuple<RPGSkill_Base, Integer>> skills = getPlayerSkills(player);
        skills.forEach(x -> {
            ItemAttributeModifiers skillmap = x.getA().use_passive_effect(player, x.getB());
            if(skillmap == null) return;
            skillmap.modifiers().forEach(y -> {
                AttributeInstance instance = map.getInstance(y.attribute());
                if(instance != null){
                    instance.addOrReplacePermanentModifier(y.modifier());
                }
            });
        });
    }

    private static ArrayList<Tuple<RPGSkill_Base, Integer>> getPlayerSkills(Player player){
        IPlayerSkill pss = player.getData(Attachment_Register.PLAYER_SKILL);
        ArrayList<Tuple<RPGSkill_Base, Integer>> skills = new ArrayList<>();
        for(var i: pss.getSkills()){
            for(var j: i.Skills){
                if(j.Point > 0){
                    String itemName = "item.athena.skill_" + j.Name;
                    RPGSkill_Base item = findSkill(itemName);
                    if(item != null) skills.add(new Tuple<>(item, j.Point));
                }
            }
        }
        return skills;
    }

    private static RPGSkill_Base findSkill(String name){
        for(var i: Athena.ITEMS.getEntries()){
            if(i.get().getDescriptionId().equals(name) && i.get() instanceof RPGSkill_Base target){
                if(target.skillType == RPGSkill_Base.SkillType.Passive) return target;
            }
        }
        return null;
    }
}
