package com.elly.athena.data.implementation;

import com.elly.athena.data.interfaceType.IPlayerStatus;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.*;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

public class PlayerStatus implements IPlayerStatus, INBTSerializable<CompoundTag> {

    private int Coin = 0;
    private String Job = "none";
    private int Level = 1;
    private int Exp = 0;
    private int Mana = 10;
    private int MaxMana = 10;
    private int Str = 1;
    private int Dex = 1;
    private int Int = 1;
    private int Luk = 1;
    private int Point = 0;

    public PlayerStatus(int mode){ }

    @Override public int getCoin() { return this.Coin; }
    @Override public boolean HaveCoin(int value) { return this.Coin >= value; }
    @Override public void setCoin(int value) { this.Coin = value; }
    @Override public void addCoin(int value) { this.Coin += value; }
    @Override public void spendCoin(int value) { this.Coin -= value; }

    @Override public int getExp() { return this.Exp; }
    @Override public int getExpMaximum(int level) { return (int)(Math.pow((double)(level * 100), (double)1.27F)); }
    @Override public void addExp(int value) { this.Exp += value; }
    @Override public void setExp(int value) { this.Exp = value; }
    @Override public boolean isLevelUp(int level) { return this.Exp >= this.getExpMaximum(level); }

    @Override public String getJob() { return this.Job; }
    @Override public void setJob(String value) { this.Job = value; }

    @Override public int getManaMaximum() { return MaxMana; }
    @Override public int getMana() { return Mana; }

    @Override public void setMana(int value) { this.Mana = value; vaildMana(); }
    @Override public void addMana(int value) { this.Mana += value; vaildMana(); }
    private void vaildMana(){ if(this.Mana > this.MaxMana) this.Mana = this.MaxMana; }

    @Override public int getDex() { return this.Dex; }

    @Override public void setDex(int value) { this.Dex = value; }
    @Override public void addDex(int value) { this.Dex += value; }
    @Override public int getInt() { return this.Int; }
    @Override public void setInt(int value) { this.Int = value; }
    @Override public void addInt(int value) { this.Int += value; }

    @Override public int getLevel() { return this.Level; }
    @Override public void setLevel(int value) { this.Level = value; }
    @Override public void addLevel(int value) { this.Level += value; }

    @Override public int getLuk() { return this.Luk; }
    @Override public void setLuk(int value) { this.Luk = value; }
    @Override public void addLuk(int value) { this.Luk += value; }
    @Override public int getPoint() { return this.Point; }

    @Override public void setPoint(int value) { this.Point = value; }
    @Override public void addPoint(int value) { this.Point += value; }
    @Override public void consumer(int value) { this.Point -= value; }

    @Override public int getStr() { return this.Str; }
    @Override public void setStr(int value) { this.Str = value; }
    @Override public void addStr(int value) { this.Str += value; }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        ListTag nbtTagList = new ListTag();

        CompoundTag tagCoin = new CompoundTag();
        tagCoin.putInt("coin", this.Coin);
        nbtTagList.add(tagCoin);

        CompoundTag tagJob = new CompoundTag();
        tagJob.putString("job", this.Job);
        nbtTagList.add(tagJob);

        CompoundTag tagLevel = new CompoundTag();
        tagLevel.putInt("level", this.Level);
        nbtTagList.add(tagLevel);

        CompoundTag tagExp = new CompoundTag();
        tagExp.putInt("exp", this.Exp);
        nbtTagList.add(tagExp);

        CompoundTag tagMana = new CompoundTag();
        tagMana.putInt("mana", this.Mana);
        nbtTagList.add(tagMana);

        CompoundTag tagMaxMana = new CompoundTag();
        tagMaxMana.putInt("max_mana", this.MaxMana);
        nbtTagList.add(tagMaxMana);

        CompoundTag nbt = new CompoundTag();
        nbt.put("rpg_status", nbtTagList);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag compoundTag) {
        ListTag tagList = compoundTag.getList("rpg_status", 10);
        CompoundTag itemTags = tagList.getCompound(0);
        this.Coin = itemTags.getInt("coin");
        this.Job = itemTags.getString("job");
        this.Level = itemTags.getInt("level");
        this.Exp = itemTags.getInt("exp");
        this.Mana = itemTags.getInt("mana");
        this.MaxMana = itemTags.getInt("max_mana");
    }
}
