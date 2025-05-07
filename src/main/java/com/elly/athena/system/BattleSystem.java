package com.elly.athena.system;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.IPlayerStatus;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
        public int AttackSpeed;
        public int MinMagicDamage;
        public int MaxMagicDamage;
        public int Defense; // Mostly Depends on Equip
        public int MagicDefense; // Mostly Depends on  Equip
        public int Dodge;
        public int MagicDodge;
        public int Accuracy;
        public int MagicAccuracy;
    }

    public static class BattleSystemProvider {
        public final Player player;

        public final IPlayerStatus status;

        public BattleSystemProvider(Player _player){
            this.player =_player;
            this.status = this.player.getData(Attachment_Register.PLAYER_STATUS);
        }

        public BattleSystemStruct GetStruct(){
            BattleSystemStruct buffer = new BattleSystemStruct();
            buffer.Level = status.getLevel();
            buffer.HP = (int)player.getHealth();
            buffer.MaxHP = this.status.getHealthMaximum() + status.getStr() + ((status.getLevel() - 1) * 4);
            buffer.MP = status.getMana();
            buffer.MaxMP = status.getManaMaximum() + status.getInt() + ((status.getLevel() - 1) * 4);
            buffer.MaxDamage = + (status.getLevel() * 5) + (status.getStr() * 3);
            buffer.MinDamage = + (status.getLevel() * 5) + (status.getStr() * 3);
            buffer.AttackSpeed = (status.getLevel()) + status.getDex();
            buffer.Dodge = (status.getLevel()) + status.getLuk();
            buffer.Accuracy = (status.getLevel()) + status.getLuk();
            buffer.MagicAccuracy = (status.getLevel()) + status.getLuk();
            return buffer;
        }
    }

    private static final ResourceLocation max_health_id = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "max_health_modifier");
    private static final ResourceLocation damage_id = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "damage_modifier");

    public static void UpdateHealth(ServerPlayer player){
        BattleSystemStruct bs = new BattleSystemProvider(player).GetStruct();
        AttributeInstance maxH = player.getAttributes().getInstance(Attributes.MAX_HEALTH);
        AttributeInstance damage = player.getAttributes().getInstance(Attributes.ATTACK_DAMAGE);
        AttributeModifier maxh_modify = new AttributeModifier(
                max_health_id, bs.MaxHP - 20, AttributeModifier.Operation.ADD_VALUE
        );

        AttributeModifier damage_modify = new AttributeModifier(
                damage_id, bs.MinDamage, AttributeModifier.Operation.ADD_VALUE
        );

        assert maxH != null;
        assert damage != null;
        maxH.addOrReplacePermanentModifier(maxh_modify);
        damage.addOrReplacePermanentModifier(damage_modify);
    }
}
