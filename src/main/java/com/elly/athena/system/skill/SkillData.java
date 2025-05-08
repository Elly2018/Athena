package com.elly.athena.system.skill;

public class SkillData {
    // SKill item Id
    public String Name;
    // Skill level
    public int Point;

    public SkillData(String id, int point) {
        Name = id;
        this.Point = point;
    }
}
