package com.elly.athena.system;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.Attribute_Register;
import com.elly.athena.data.implementation.PlayerEquipment;
import com.elly.athena.data.implementation.PlayerSkill;
import com.elly.athena.data.interfaceType.IPlayerStatus;
import com.elly.athena.item.skill.RPGSkill_Base;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import javax.annotation.Nullable;
import java.util.Objects;

public class BattleSystem {

    public static class BattleSystemStruct {
        public int Level = 0;
        public int HP = 0;
        public int MaxHP = 0;
        public int MP = 0;
        public int MaxMP = 0;
        public int MinDamage = 0;
        public int MaxDamage = 0;
        public int AttackSpeed = 0;
        public int MinMagicDamage = 0;
        public int MaxMagicDamage = 0;
        public int Defense = 0; // Mostly Depends on Equip
        public int MagicDefense = 0; // Mostly Depends on  Equip
        public int Dodge = 0;
        public int MagicDodge = 0;
        public int Accuracy = 0;
        public int MagicAccuracy = 0;

        public static BattleSystemStruct DEFAULT(){
            BattleSystemStruct buffer = new BattleSystemStruct();
            buffer.Level = 1;
            buffer.HP = 20;
            buffer.MaxHP = 20;
            buffer.MP = 10;
            buffer.MaxMP = 10;
            buffer.MinDamage = 1;
            buffer.MaxDamage = 1;
            buffer.AttackSpeed = 1;
            buffer.MinMagicDamage = 0;
            buffer.Defense = 0;
            buffer.MagicDefense = 0;
            buffer.Dodge = 0;
            buffer.MagicDodge = 0;
            buffer.Accuracy = 0;
            buffer.MaxMagicDamage = 0;
            buffer.MagicAccuracy = 0;
            return buffer;
        }
        public static BattleSystemStruct ONE(){
            BattleSystemStruct buffer = new BattleSystemStruct();
            buffer.Level = 1;
            buffer.HP = 1;
            buffer.MaxHP = 1;
            buffer.MP = 1;
            buffer.MaxMP = 1;
            buffer.MinDamage = 1;
            buffer.MaxDamage = 1;
            buffer.AttackSpeed = 1;
            buffer.MinMagicDamage = 1;
            buffer.Defense = 1;
            buffer.MagicDefense = 1;
            buffer.Dodge = 1;
            buffer.MagicDodge = 1;
            buffer.Accuracy = 1;
            buffer.MaxMagicDamage = 1;
            buffer.MagicAccuracy = 1;
            return buffer;
        }
        public static BattleSystemStruct ZERO(){
            BattleSystemStruct buffer = new BattleSystemStruct();
            buffer.Level = 0;
            buffer.HP = 0;
            buffer.MaxHP = 0;
            buffer.MP = 0;
            buffer.MaxMP = 0;
            buffer.MinDamage = 0;
            buffer.MaxDamage = 0;
            buffer.AttackSpeed = 0;
            buffer.MinMagicDamage = 0;
            buffer.Defense = 0;
            buffer.MagicDefense = 0;
            buffer.Dodge = 0;
            buffer.MagicDodge = 0;
            buffer.Accuracy = 0;
            buffer.MaxMagicDamage = 0;
            buffer.MagicAccuracy = 0;
            return buffer;
        }
        public static BattleSystemStruct plus(BattleSystemStruct a, BattleSystemStruct b){
            BattleSystemStruct buffer = new BattleSystemStruct();
            buffer.Level = a.Level + b.Level;
            buffer.HP = a.HP + b.HP;
            buffer.MaxHP = a.MaxHP + b.MaxHP;
            buffer.MP = a.MP + b.MP;
            buffer.MaxMP = a.MaxMP + b.MaxMP;
            buffer.MinDamage = a.MinDamage + b.MinDamage;
            buffer.MaxDamage = a.MaxDamage + b.MaxDamage;
            buffer.AttackSpeed = a.AttackSpeed + b.AttackSpeed;
            buffer.MinMagicDamage = a.MinMagicDamage + b.MinMagicDamage;
            buffer.Defense = a.Defense + b.Defense;
            buffer.MagicDefense = a.MagicDefense + b.MagicDefense;
            buffer.Dodge = a.Dodge + b.Dodge;
            buffer.MagicDodge = a.MagicDodge + b.MagicDodge;
            buffer.Accuracy = a.Accuracy + b.Accuracy;
            buffer.MaxMagicDamage = a.MaxMagicDamage + b.MaxMagicDamage;
            buffer.MagicAccuracy = a.MagicAccuracy + b.MagicAccuracy;
            return buffer;
        }
        public static BattleSystemStruct minus(BattleSystemStruct a, BattleSystemStruct b){
            BattleSystemStruct buffer = new BattleSystemStruct();
            buffer.Level = a.Level - b.Level;
            buffer.HP = a.HP - b.HP;
            buffer.MaxHP = a.MaxHP - b.MaxHP;
            buffer.MP = a.MP - b.MP;
            buffer.MaxMP = a.MaxMP - b.MaxMP;
            buffer.MinDamage = a.MinDamage - b.MinDamage;
            buffer.MaxDamage = a.MaxDamage - b.MaxDamage;
            buffer.AttackSpeed = a.AttackSpeed - b.AttackSpeed;
            buffer.MinMagicDamage = a.MinMagicDamage - b.MinMagicDamage;
            buffer.Defense = a.Defense - b.Defense;
            buffer.MagicDefense = a.MagicDefense - b.MagicDefense;
            buffer.Dodge = a.Dodge - b.Dodge;
            buffer.MagicDodge = a.MagicDodge - b.MagicDodge;
            buffer.Accuracy = a.Accuracy - b.Accuracy;
            buffer.MaxMagicDamage = a.MaxMagicDamage - b.MaxMagicDamage;
            buffer.MagicAccuracy = a.MagicAccuracy - b.MagicAccuracy;
            return buffer;
        }
        public static BattleSystemStruct multiply(BattleSystemStruct a, BattleSystemStruct b){
            BattleSystemStruct buffer = new BattleSystemStruct();
            buffer.Level = a.Level * b.Level;
            buffer.HP = a.HP * b.HP;
            buffer.MaxHP = a.MaxHP * b.MaxHP;
            buffer.MP = a.MP * b.MP;
            buffer.MaxMP = a.MaxMP * b.MaxMP;
            buffer.MinDamage = a.MinDamage * b.MinDamage;
            buffer.MaxDamage = a.MaxDamage * b.MaxDamage;
            buffer.AttackSpeed = a.AttackSpeed * b.AttackSpeed;
            buffer.MinMagicDamage = a.MinMagicDamage * b.MinMagicDamage;
            buffer.Defense = a.Defense * b.Defense;
            buffer.MagicDefense = a.MagicDefense * b.MagicDefense;
            buffer.Dodge = a.Dodge * b.Dodge;
            buffer.MagicDodge = a.MagicDodge * b.MagicDodge;
            buffer.Accuracy = a.Accuracy * b.Accuracy;
            buffer.MaxMagicDamage = a.MaxMagicDamage * b.MaxMagicDamage;
            buffer.MagicAccuracy = a.MagicAccuracy * b.MagicAccuracy;
            return buffer;
        }
    }

    public static class BattleSystemProvider {
        public final Player player;
        public LivingEntity target;

        private final RandomSource random;

        public BattleSystemProvider(Player _player){
            this.random = _player.getRandom();
            this.player =_player;
        }

        public void setTarget(LivingEntity _target){
            target = _target;
        }

        public int DamageCalculat(RPGSkill_Base skill, int level){
            if(skill == null) return DamageCalculat();
            return 0;
        }

        public int DamageCalculat(){
            return 0;
        }

        public int MagicDamageCalculat(RPGSkill_Base skill, int level){
            return 0;
        }

        public BattleSystemStruct GetSourceBasic(){
            return getPlayerBasic(player);
        }
        public BattleSystemStruct GetSourceFinal(){
            return ApplyCalculateAttribute(player);
        }
        @Nullable
        public BattleSystemStruct GetTargetBasic(){
            if(target == null) return null;
            if(target instanceof Player) return getPlayerBasic((Player) target);
            return null;
        }
        private BattleSystemStruct getPlayerBasic(Player _player){
            IPlayerStatus _status = _player.getData(Attachment_Register.PLAYER_STATUS);
            BattleSystemStruct buffer = new BattleSystemStruct();
            buffer.Level = _status.getLevel();
            buffer.HP = (int)_player.getHealth();
            buffer.MaxHP = _status.getHealthMaximum() + _status.getStr() + ((_status.getLevel() - 1) * 4);
            buffer.MP = _status.getMana();
            buffer.MaxMP = _status.getManaMaximum() + _status.getInt() + ((_status.getLevel() - 1) * 4);
            buffer.MaxDamage = + (_status.getLevel() * 5) + (_status.getStr() * 3);
            buffer.MinDamage = + (_status.getLevel() * 5) + (_status.getStr() * 3);
            buffer.AttackSpeed = (_status.getLevel()) + _status.getDex();
            buffer.Dodge = (_status.getLevel()) + _status.getLuk();
            buffer.Accuracy = (_status.getLevel()) + _status.getLuk();
            buffer.MagicAccuracy = (_status.getLevel()) + _status.getLuk();
            return buffer;
        }
    }

    private static final ResourceLocation max_health_id = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "max_health_modifier");
    private static final ResourceLocation damage_id = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "damage_modifier");

    public static void UpdateHealth(ServerPlayer player){
        BattleSystemStruct bs = new BattleSystemProvider(player).GetSourceBasic();
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

    public static BattleSystemStruct ApplyCalculateAttribute(Player player){
        // Get base value
        BattleSystemStruct r = new BattleSystemProvider(player).GetSourceBasic();

        PlayerEquipment pe = player.getData(Attachment_Register.PLAYER_EQUIPMENT);
        PlayerSkill pss = player.getData(Attachment_Register.PLAYER_SKILL);
        NonNullList<ItemStack> am = player.getInventory().armor; // size 4
        NonNullList<ItemStack> pem = pe.getList(); // size 4

        // Base_add, Base_mul, Total_mul
        BattleSystemStruct[] vs = { BattleSystemStruct.ZERO(), BattleSystemStruct.ONE(), BattleSystemStruct.ONE() };
        for (ItemStack stack : am) {
            vs = Apply_Compute(stack, vs);
        }
        for (ItemStack stack : pem) {
            vs = Apply_Compute(stack, vs);
        }

        // Final calculation
        r = BattleSystemStruct.multiply(r, vs[1]);
        r = BattleSystemStruct.plus(r, vs[0]);
        r = BattleSystemStruct.multiply(r, vs[2]);
        return r;
    }

    public static void ApplyModAttribute(Player player, BattleSystemStruct v, boolean vanilla){
        AttributeMap map = player.getAttributes();
        Objects.requireNonNull(map.getInstance(Attributes.ARMOR)).setBaseValue(v.Defense);
        Objects.requireNonNull(map.getInstance(Attributes.MAX_HEALTH)).setBaseValue(v.MaxHP);
        Objects.requireNonNull(map.getInstance(Attributes.ATTACK_DAMAGE)).setBaseValue(v.MinDamage);
        Objects.requireNonNull(map.getInstance(Attributes.ATTACK_SPEED)).setBaseValue(v.AttackSpeed);
    }

    private static BattleSystemStruct[] Apply_Compute(ItemStack iss, BattleSystemStruct[] vs){
        ItemAttributeModifiers itemAtt = iss.getAttributeModifiers();
        for(ItemAttributeModifiers.Entry entry: itemAtt.modifiers()){
            AttributeModifier modifier = entry.modifier();
            Holder<Attribute> att = entry.attribute();
            AttributeModifier.Operation ops = modifier.operation();
            if(att == Attributes.ARMOR && ops == AttributeModifier.Operation.ADD_VALUE)                                vs[0].Defense += (int) modifier.amount();
            else if(att == Attributes.ARMOR && ops == AttributeModifier.Operation.ADD_MULTIPLIED_BASE)                 vs[1].Defense += (int) modifier.amount();
            else if(att == Attributes.ARMOR && ops == AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)                vs[2].Defense += (int) modifier.amount();
            else if(att == Attributes.MAX_HEALTH && ops == AttributeModifier.Operation.ADD_VALUE)                      vs[0].MaxHP += (int) modifier.amount();
            else if(att == Attributes.MAX_HEALTH && ops == AttributeModifier.Operation.ADD_MULTIPLIED_BASE)            vs[1].MaxHP += (int) modifier.amount();
            else if(att == Attributes.MAX_HEALTH && ops == AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)           vs[2].MaxHP += (int) modifier.amount();
            else if(att == Attributes.ATTACK_DAMAGE && ops == AttributeModifier.Operation.ADD_VALUE)                   vs[0].MinDamage += (int) modifier.amount();
            else if(att == Attributes.ATTACK_DAMAGE && ops == AttributeModifier.Operation.ADD_MULTIPLIED_BASE)         vs[1].MinDamage += (int) modifier.amount();
            else if(att == Attributes.ATTACK_DAMAGE && ops == AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)        vs[2].MinDamage += (int) modifier.amount();
            else if(att == Attributes.ATTACK_SPEED && ops == AttributeModifier.Operation.ADD_VALUE)                    vs[0].AttackSpeed += (int) modifier.amount();
            else if(att == Attributes.ATTACK_SPEED && ops == AttributeModifier.Operation.ADD_MULTIPLIED_BASE)          vs[1].AttackSpeed += (int) modifier.amount();
            else if(att == Attributes.ATTACK_SPEED && ops == AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)         vs[2].AttackSpeed += (int) modifier.amount();
            else if(att == Attribute_Register.MAGIC_ATTACK && ops == AttributeModifier.Operation.ADD_VALUE)            vs[0].MinMagicDamage += (int) modifier.amount();
            else if(att == Attribute_Register.MAGIC_ATTACK && ops == AttributeModifier.Operation.ADD_MULTIPLIED_BASE)  vs[1].MinMagicDamage += (int) modifier.amount();
            else if(att == Attribute_Register.MAGIC_ATTACK && ops == AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL) vs[2].MinMagicDamage += (int) modifier.amount();
        }
        return vs;
    }
}
