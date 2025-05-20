package com.elly.athena.entity.spell;

import com.elly.athena.data.Attribute_Register;
import com.elly.athena.entity.Entity_Register;
import com.elly.athena.item.Item_Register;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import org.joml.Math;

import java.util.Objects;

public class MagicBall extends ThrowableItemProjectile {

    int tick;

    public MagicBall(EntityType<? extends MagicBall> entityType, Level level) {
        super(entityType, level);
        init();
    }

    public MagicBall(Level level, LivingEntity owner, ItemStack item) {
        super(Entity_Register.MAGICBALL, owner, level, item);
        init();
    }

    public MagicBall(Level level, double x, double y, double z, ItemStack item) {
        super(Entity_Register.MAGICBALL, x, y, z, level, item);
        init();
    }

    private void init(){
        this.setNoGravity(true);
        this.refreshDimensions();
    }

    @Override
    public void tick() {
        super.tick();
        tick += 1;
        if(tick > 10){
            tick = 0;
            this.level().addParticle(ParticleTypes.SNOWFLAKE, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        }
    }

    @Override
    public int getDimensionChangingDelay() {
        return 6;
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return Item_Register.ENTITY_MAGICBALL.get();
    }

    @Override
    protected void onHit(@NotNull HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        super.onHitEntity(result);
        if(this.getOwner() instanceof Player player){
            Entity target = result.getEntity();
            int magic_attack = (int) Objects.requireNonNull(player.getAttribute(Attribute_Register.MAGIC_ATTACK)).getValue();
            int magic_attack_max = (int) Objects.requireNonNull(player.getAttribute(Attribute_Register.MAGIC_ATTACK_MAX)).getValue();
            int d = this.getRandom().nextIntBetweenInclusive(magic_attack, Math.max(magic_attack + magic_attack_max, magic_attack + 1));

            target.hurt(this.damageSources().thrown(this, this.getOwner()), (float)d);
        }
    }
}
