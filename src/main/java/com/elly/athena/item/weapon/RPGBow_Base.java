package com.elly.athena.item.weapon;

import com.elly.athena.data.Attribute_Register;
import com.elly.athena.data.DataComponent_Register;
import com.elly.athena.data.datacomponent.BowData;
import com.elly.athena.data.datacomponent.Upgrade;
import com.elly.athena.item.Item_Register;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class RPGBow_Base extends BowItem {
    public RPGBow_Base(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull Component getName(ItemStack stack) {
        Upgrade c = stack.getComponents().getOrDefault(DataComponent_Register.UPGRADE.get(), new Upgrade(0));
        if(c.upgrade() == 0) return super.getName(stack);
        return Component.literal(String.format("[%s] %s", c.upgrade() == Item_Register.MAX_UPGRADE ? "MAX" : c.upgrade(), super.getName(stack).getString()));
    }

    public static ItemAttributeModifiers GetModify(float attackDamage, float attackSpeed){
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(Item_Register.AttackDamage_ID, (double)(attackDamage), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED,  new AttributeModifier(Item_Register.AttackDamage_ID, (double)attackDamage, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .build().withTooltip(true);
    }

    @Override
    public boolean releaseUsing(@NotNull ItemStack iss, @NotNull Level level, @NotNull LivingEntity entity, int count) {
        if (entity instanceof Player player) {
            ItemStack itemstack = player.getProjectile(iss);
            if (itemstack.isEmpty()) {
                return false;
            } else {
                int i = this.getUseDuration(iss, entity) - count;
                i = EventHooks.onArrowLoose(iss, level, player, i, !itemstack.isEmpty());
                BowData bowdata = iss.get(DataComponent_Register.BOWDATA);
                if (i < 0) {
                    return false;
                } else {
                    float f = getCustomPowerForTime(i, bowdata == null ? 20 : bowdata.time());
                    if ((double)f < 0.1) {
                        return false;
                    } else {
                        List<ItemStack> list = draw(iss, itemstack, player);
                        if (level instanceof ServerLevel) {
                            ServerLevel serverlevel = (ServerLevel)level;
                            if (!list.isEmpty()) {
                                this.shoot(serverlevel, player, player.getUsedItemHand(), iss, list, f * 3.0F, 1.0F, f == 1.0F, (LivingEntity)null);
                            }
                        }

                        level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                        player.awardStat(Stats.ITEM_USED.get(this));
                        return true;
                    }
                }
            }
        } else {
            return false;
        }
    }

    public float getCustomPowerForTime(int charge, int time) {
        float f = (float)charge / time;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public int getDefaultProjectileRange() {
        return DEFAULT_RANGE;
    }

    @Override
    protected void shootProjectile(LivingEntity p_331372_, Projectile p_332000_, int p_330631_, float p_331251_, float p_331199_, float p_330857_, @Nullable LivingEntity p_331572_) {
        p_332000_.shootFromRotation(p_331372_, p_331372_.getXRot(), p_331372_.getYRot() + p_330857_, 0.0F, p_331251_, p_331199_);
    }

    public Item_Register.ItemRegisterData_Upgrade_Bow GetRegister(){
        return null;
    }
}
