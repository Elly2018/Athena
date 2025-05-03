package com.elly.rpg.capability.implementation;

import com.elly.rpg.capability.capability.IJob;
import com.elly.rpg.capability.capability.status.IStr;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.StringTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

public class Job implements IJob, INBTSerializable<StringTag> {
    private String Job;

    public Job(){
        this.Job = "none";
    }

    @Override
    public String getJob() {
        return this.Job;
    }

    @Override
    public void setJob(String value) {
        this.Job = value;
    }

    @Override
    public StringTag serializeNBT(HolderLookup.Provider registryAccess) {
        return StringTag.valueOf(this.getJob());
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider registryAccess, StringTag nbt) {
        if (!(nbt instanceof StringTag intNbt))
            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
        this.Job = intNbt.getAsString();
    }
}
