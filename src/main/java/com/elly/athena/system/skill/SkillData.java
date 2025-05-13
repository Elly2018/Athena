package com.elly.athena.system.skill;

public class SkillData {
    // SKill item Id
    public String Name;
    // Skill level
    public int Point;
    public int Cooldown;

    public SkillData(String id, int point, int cooldown) {
        Name = id;
        Point = point;
        Cooldown = cooldown;
    }
}
