package com.elly.athena.system;

import com.elly.athena.capability.Attachment_Register;
import com.elly.athena.capability.interfaceType.IPlayerStatus;
import net.minecraft.world.entity.player.Player;

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
        public final IPlayerStatus status;

        public BattleSystemProvider(Player _player){
            this.player =_player;
            this.status = this.player.getData(Attachment_Register.PLAYER_STATUS);
        }

        public BattleSystemStruct GetStruct(){
            BattleSystemStruct buffer = new BattleSystemStruct();
            buffer.Level = status.getLevel();
            buffer.HP = (int)player.getHealth();
            buffer.MaxHP = (int)player.getMaxHealth();
            buffer.MP = status.getMana();
            buffer.MaxMP = status.getManaMaximum();
            return buffer;
        }
    }
}
