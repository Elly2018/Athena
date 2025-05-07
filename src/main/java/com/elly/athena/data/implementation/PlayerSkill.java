package com.elly.athena.data.implementation;

import com.elly.athena.data.interfaceType.IPlayerSkill;
import com.elly.athena.system.skill.SkillCategory;
import com.elly.athena.system.skill.SkillData;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

import java.util.ArrayList;
import java.util.Objects;

public class PlayerSkill implements IPlayerSkill, INBTSerializable<CompoundTag> {

    ArrayList<SkillCategory> skills = new ArrayList<>();

    @Override
    public SkillCategory[] getSkills() { return (SkillCategory[]) skills.toArray(); }

    @Override
    public SkillCategory getCategory(String name) {
        for (SkillCategory skill : skills) {
            if (Objects.equals(skill.Name, name)) return skill;
        }
        return null;
    }

    @Override
    public SkillData getData(String category, String name) {
        SkillCategory cate = getCategory(category);
        if(cate == null) return null;
        for(SkillData skill : cate.Skills){
            if(Objects.equals(skill.Name, name)) return skill;
        }
        return null;
    }

    @Override
    public int getPoint(String category, String name) {
        SkillData sd = getData(category, name);
        return sd == null ? 0 : sd.Point;
    }

    @Override
    public void addPoint(String category, String name, int point) {
        SkillData sd = getData(category, name);
        sd.Point += point;
    }

    @Override
    public void setPoint(String category, String name, int point) {
        SkillData sd = getData(category, name);
        sd.Point = point;
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putInt("size", skills.size());
        for (int i = 0; i < skills.size(); i++) {
            SkillCategory cate = skills.get(i);
            nbt.put(String.valueOf(i), cate.serializeNBT(provider));
        }
        return null;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag compoundTag) {
        int size = compoundTag.getInt("size");
        skills = new ArrayList<>();
        skills = new ArrayList<>(size);
        for(int i = 0; i < size; i++){
            SkillCategory sc = new SkillCategory();
            sc.deserializeNBT(null, compoundTag.getCompound(String.valueOf(i)));
            skills.add(sc);
        }
    }
}
