package com.elly.rpg.system;

import com.elly.rpg.capability.CapabilitySystem;
import com.elly.rpg.capability.capability.IMana;
import com.elly.rpg.capability.capability.status.*;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;

import java.util.Optional;

public class BattleSystem {

    public static class BattleSystemStruct {
        public int Level;
        public int HP;
        public int MaxHP;
        public int MP;
        public int MaxMP;
        public int MinDamage;
        public int MaxDamage;
        public int MinMagicDamage;
        public int MaxMagicDamage;
        public int Defense;
        public int MagicDefense;
        public int Dodge;
        public int MagicDodge;
        public int Accuracy;
        public int MagicAccuracy;
    }

    public static class BattleSystemProvider {
        private final Player player;
        public final Optional<ILevel> optional_level;
        public final Optional<IMana> optional_mana;
        public final Optional<IStr> optional_str;
        public final Optional<IDex> optional_dex;
        public final Optional<IInt> optional_int;
        public final Optional<ILuk> optional_luk;
        public final Optional<IPoint> optional_point;

        public BattleSystemProvider(Player _player){
            this.player =_player;
            this.optional_level = this.player.getCapability(CapabilitySystem.LEVEL).resolve();
            this.optional_mana = this.player.getCapability(CapabilitySystem.MANA).resolve();
            this.optional_str = this.player.getCapability(CapabilitySystem.STR).resolve();
            this.optional_dex = this.player.getCapability(CapabilitySystem.DEX).resolve();
            this.optional_int = this.player.getCapability(CapabilitySystem.INT).resolve();
            this.optional_luk = this.player.getCapability(CapabilitySystem.LUK).resolve();
            this.optional_point = this.player.getCapability(CapabilitySystem.POINT).resolve();
        }

        public BattleSystemStruct GetStruct(){
            BattleSystemStruct buffer = new BattleSystemStruct();
            if(optional_level.isPresent()) {
                buffer.Level = optional_level.get().getLevel();
            }
            buffer.HP = (int)player.getHealth();
            buffer.MaxHP = (int)player.getMaxHealth();
            if(optional_mana.isPresent()) {
                buffer.MP = optional_mana.get().getMana();
                buffer.MaxMP = optional_mana.get().getManaMaximum();
            }
            return buffer;
        }
    }
}
