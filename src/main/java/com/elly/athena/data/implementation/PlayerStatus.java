package com.elly.athena.data.implementation;

import com.elly.athena.data.interfaceType.IPlayerStatus;
import com.elly.athena.system.BattleSystem;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

import java.util.Optional;

public class PlayerStatus implements IPlayerStatus, INBTSerializable<CompoundTag> {
    private int Coin = 0;
    private String Job = "none";
    private int Level = 1;
    private int Exp = 0;
    private int MaxHealth = 20;
    private int Mana = 10;
    private int MaxMana = 10;
    private int Str = 1;
    private int Dex = 1;
    private int Int = 1;
    private int Luk = 1;
    private int Point = 0;
    private int Mode = 0;
    public boolean Dirty = true;

    @Override public int getCoin() { return this.Coin; }
    @Override public boolean HaveCoin(int value) { return this.Coin >= value; }
    @Override public void setCoin(int value) { this.Coin = value; }
    @Override public void addCoin(int value) { this.Coin += value; }
    @Override public void spendCoin(int value) { this.Coin -= value; }

    @Override public float getExpProgress(int level) { return (float)this.Exp / (float)getExpMaximum(level); }
    @Override public int getExp() { return this.Exp; }
    @Override public int getExpMaximum(int level) { return (int)(Math.pow((Math.pow(2, level) * 100) + 10, (double)1.1F)); }
    @Override public void addExp(int value) { this.Exp += value; }
    @Override public void setExp(int value) { this.Exp = value; }
    @Override public boolean isLevelUp(int level) { return this.Exp >= this.getExpMaximum(level); }

    @Override public String getJob() { return this.Job; }
    @Override public void setJob(String value) { this.Job = value; }

    @Override public int getHealthMaximum() { return this.MaxHealth; }
    @Override public void setMaxHealth(int value) { this.MaxHealth = value; }
    @Override public void addMaxHealth(int value) { this.MaxHealth += value; }

    @Override public int getManaMaximum() { return MaxMana; }
    @Override public void setManaMaximum(int value) { MaxMana = value; }
    @Override public void addManaMaximum(int value) { MaxMana += value; }
    @Override public int getMana() { return Mana; }
    @Override public void setMana(int value) { this.Mana = value; }
    @Override public void addMana(int value) { this.Mana += value; }

    @Override public int getDex() { return this.Dex; }
    @Override public void setDex(int value) { this.Dex = value; }
    @Override public void addDex(int value) { this.Dex += value; }

    @Override public int getInt() { return this.Int;  }
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

    @Override public int getMode() { return Mode; }
    @Override public void setMode(int value) { Mode = value; }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag elementTag = new CompoundTag();

        elementTag.putInt("coin", this.Coin);
        elementTag.putString("job", this.Job);
        elementTag.putInt("level", this.Level);
        elementTag.putInt("exp", this.Exp);
        elementTag.putInt("max_health", this.MaxHealth);
        elementTag.putInt("mana", this.Mana);
        elementTag.putInt("max_mana", this.MaxMana);
        elementTag.putInt("str", this.Str);
        elementTag.putInt("dex", this.Dex);
        elementTag.putInt("int", this.Int);
        elementTag.putInt("luk", this.Luk);
        elementTag.putInt("point", this.Point);
        elementTag.putInt("mode", this.Mode);

        CompoundTag nbt = new CompoundTag();
        nbt.put("status", elementTag);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag compoundTag) {
        CompoundTag elementTag = compoundTag.getCompound("status");

        this.Coin = elementTag.getInt("coin");
        this.Job = elementTag.getString("job");
        this.Level = elementTag.getInt("level");
        this.Exp = elementTag.getInt("exp");
        this.MaxHealth = elementTag.getInt("max_health");
        this.Mana = elementTag.getInt("mana");
        this.MaxMana = elementTag.getInt("max_mana");
        this.Str = elementTag.getInt("str");
        this.Dex = elementTag.getInt("dex");
        this.Int = elementTag.getInt("int");
        this.Luk = elementTag.getInt("luk");
        this.Point = elementTag.getInt("point");
        this.Mode = elementTag.getInt("mode");
    }
}
