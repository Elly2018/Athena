package com.elly.athena.data.implementation;

import com.elly.athena.data.interfaceType.attachment.IPlayerSkill;
import com.elly.athena.system.skill.SkillCategory;
import com.elly.athena.system.skill.SkillData;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

import java.util.ArrayList;
import java.util.Objects;

public class PlayerSkill implements IPlayerSkill, INBTSerializable<CompoundTag> {

    ArrayList<SkillCategory> skills = new ArrayList<>();

    @Override
    public SkillCategory[] getSkills() {
        SkillCategory[] sc = new SkillCategory[skills.size()];
        return skills.toArray(sc);
    }

    @Override
    @NotNull
    public SkillCategory getCategory(String name) {
        for (SkillCategory skill : skills) {
            if (Objects.equals(skill.Name, name)) return skill;
        }
        SkillCategory sc = new SkillCategory();
        sc.Name = name;
        sc.Skills = new ArrayList<>();
        skills.add(sc);
        return sc;
    }

    @Override
    @NotNull
    public SkillData getData(String category, String name) {
        SkillCategory cate = getCategory(category);
        for(SkillData skill : cate.Skills){
            if(Objects.equals(skill.Name, name)) return skill;
        }
        SkillData sd = new SkillData(name, -1, 0);
        cate.Skills.add(sd);
        return sd;
    }

    @Override
    public int getPoint(String category, String name) {
        SkillData sd = getData(category, name);
        return sd.Point;
    }

    @Override
    public void addPoint(String category, String name, int point) {
        SkillData sd = getData(category, name);
        sd.Point += point;
    }

    @Override
    public void addPoint(String name, int point) {
        for(var cate: skills){
            for(var skill: cate.Skills){
                if(skill.equals(name)){
                    skill.Point += point;
                    return;
                }
            }
        }
    }

    @Override
    public void setPoint(String category, String name, int point) {
        SkillData sd = getData(category, name);
        sd.Point = point;
    }

    @Override
    public void SetCooldown(String category, String name, int time) {
        SkillData sd = getData(category, name);
        sd.Cooldown = time;
    }

    @Override
    public void UpdateCooldown() {
        skills.forEach(x -> {
            x.Skills.forEach(y -> {
                if(y.Cooldown > 0){
                    y.Cooldown--;
                }
            });
        });
    }

    @Override
    public boolean CheckCooldown(String category, String name) {
        SkillData sd = getData(category, name);
        return sd.Cooldown == 0;
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("size", skills.size());
        for (int i = 0; i < skills.size(); i++) {
            SkillCategory cate = skills.get(i);
            nbt.put(String.valueOf(i), cate.serializeNBT(provider));
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.@NotNull Provider provider, CompoundTag compoundTag) {
        int size = compoundTag.getInt("size");
        skills = new ArrayList<>(size);
        for(int i = 0; i < size; i++){
            SkillCategory sc = new SkillCategory();
            sc.deserializeNBT(provider, compoundTag.getCompound(String.valueOf(i)));
            skills.add(sc);
        }
    }
}
