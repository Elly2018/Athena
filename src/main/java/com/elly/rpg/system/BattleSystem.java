package com.elly.rpg.system;

import com.elly.rpg.capability.CapabilitySystem;
import com.elly.rpg.capability.capability.IMana;
import com.elly.rpg.capability.capability.status.*;
import net.minecraft.world.entity.player.Player;

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
        public final ILevel optional_level;
        public final IMana optional_mana;
        public final IStr optional_str;
        public final IDex optional_dex;
        public final IInt optional_int;
        public final ILuk optional_luk;
        public final IPoint optional_point;

        public BattleSystemProvider(Player _player){
            this.player =_player;
            this.optional_level = this.player.getCapability(CapabilitySystem.LEVEL);
            this.optional_mana = this.player.getCapability(CapabilitySystem.MANA);
            this.optional_str = this.player.getCapability(CapabilitySystem.STR);
            this.optional_dex = this.player.getCapability(CapabilitySystem.DEX);
            this.optional_int = this.player.getCapability(CapabilitySystem.INT);
            this.optional_luk = this.player.getCapability(CapabilitySystem.LUK);
            this.optional_point = this.player.getCapability(CapabilitySystem.POINT);
        }

        public BattleSystemStruct GetStruct(){
            BattleSystemStruct buffer = new BattleSystemStruct();
            if(optional_level != null) {
                buffer.Level = optional_level.getLevel();
            }
            buffer.HP = (int)player.getHealth();
            buffer.MaxHP = (int)player.getMaxHealth();
            if(optional_mana != null) {
                buffer.MP = optional_mana.getMana();
                buffer.MaxMP = optional_mana.getManaMaximum();
            }
            return buffer;
        }
    }
}
