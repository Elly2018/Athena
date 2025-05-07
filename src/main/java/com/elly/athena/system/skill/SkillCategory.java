package com.elly.athena.system.skill;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

import java.util.ArrayList;

public class SkillCategory implements INBTSerializable<CompoundTag> {
    public String Name;
    public ArrayList<SkillData> Skills;

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putString("name", Name);
        nbt.putInt("size", Skills.size());

        for (int i = 0; i < Skills.size(); i++) {
            SkillData data = Skills.get(i);

            CompoundTag local = new CompoundTag();
            local.putString("name", data.Name);
            local.putInt("point", data.Point);
            nbt.put(String.valueOf(i), local);
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag compoundTag) {
        this.Name = compoundTag.getString("name");
        int size = compoundTag.getInt("size");
        this.Skills = new ArrayList<>(size);
        for(int i = 0; i < size; i++){
            CompoundTag local = compoundTag.getCompound(String.valueOf(i));
            String n = local.getString("name");
            int p = local.getInt("point");
            this.Skills.add(new SkillData(n, p));
        }
    }
}
