package com.elly.athena.system.skill;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

import java.util.ArrayList;

public class SkillCategory implements INBTSerializable<CompoundTag> {
    public String Name;
    public ArrayList<SkillData> Skills;

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putString("name", Name);
        nbt.putInt("size", Skills.size());

        for (int i = 0; i < Skills.size(); i++) {
            SkillData data = Skills.get(i);

            CompoundTag local = new CompoundTag();
            local.putString("name", data.Name);
            local.putInt("point", data.Point);
            local.putInt("cooldown", data.Cooldown);
            nbt.put(String.valueOf(i), local);
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.@NotNull Provider provider, CompoundTag compoundTag) {
        this.Name = compoundTag.getString("name");
        int size = compoundTag.getInt("size");
        this.Skills = new ArrayList<>(size);
        for(int i = 0; i < size; i++){
            CompoundTag local = compoundTag.getCompound(String.valueOf(i));
            String n = local.getString("name");
            int p = local.getInt("point");
            int c = local.getInt("cooldown");
            this.Skills.add(new SkillData(n, p, c));
        }
    }
}
