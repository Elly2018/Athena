package com.elly.rpg.system;

import com.elly.rpg.capability.CapabilitySystem;
import com.elly.rpg.capability.capability.IMana;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;

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
        private final LazyOptional<IMana> mana;

        public BattleSystemProvider(Player _player){
            this.player =_player;
            this.mana = this.player.getCapability(CapabilitySystem.MANA);
        }

        public BattleSystemStruct GetStruct(){
            BattleSystemStruct buffer = new BattleSystemStruct();
            return buffer;
        }
    }
}
