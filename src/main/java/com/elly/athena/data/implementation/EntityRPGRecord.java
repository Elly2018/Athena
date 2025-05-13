package com.elly.athena.data.implementation;

import com.elly.athena.data.interfaceType.attachment.IEntityRPGRecord;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

public class EntityRPGRecord implements IEntityRPGRecord, INBTSerializable<CompoundTag> {
    private String UUID;
    private String Skill;
    private int Level;
    private int Damage;
    private String Meta;

    public EntityRPGRecord(){

    }

    public EntityRPGRecord(String uuid, String skill, int level, int damage, String meta) {
        UUID = uuid;
        Skill = skill;
        Level = level;
        Damage = damage;
        Meta = meta;
    }

    @Override public void setEntityId(String value) { UUID = value; }
    @Override public String getEntityId() { return UUID; }

    @Override public void setSkillItem(String value) { Skill = value; }
    @Override public String getSkillItem() { return Skill; }

    @Override public void setSkillLevel(int value) { Level = value; }
    @Override public int getSkillLevel() { return Level; }

    @Override public void setDamage(int value) { Damage = value; }
    @Override public int getDamage() { return Damage; }

    @Override public void setMeta(String value) { Meta = value; }
    @Override public String getMeta() { return Meta; }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putString("uuid", UUID);
        nbt.putString("skill", Skill);
        nbt.putInt("level", Level);
        nbt.putInt("damage", Damage);
        nbt.putString("meta", Meta);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.@NotNull Provider provider, @NotNull CompoundTag compoundTag) {
        UUID = compoundTag.getString("uuid");
        Skill = compoundTag.getString("skill");
        Level = compoundTag.getInt("level");
        Damage = compoundTag.getInt("damage");
        Meta = compoundTag.getString("meta");
    }
}
