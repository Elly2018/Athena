package com.elly.athena.data.interfaceType;

import com.elly.athena.system.skill.SkillCategory;
import com.elly.athena.system.skill.SkillData;

public interface IPlayerSkill {
    SkillCategory[] getSkills();
    SkillCategory getCategory(String name);
    SkillData getData(String category, String name);
    int getPoint(String category, String name);
    void addPoint(String category, String name, int point);
    void setPoint(String category, String name, int point);

    void SetCooldown(String category, String name, int time);
    void UpdateCooldown();
    boolean CheckCooldown(String category, String name);
}
