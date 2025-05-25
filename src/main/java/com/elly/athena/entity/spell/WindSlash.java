package com.elly.athena.entity.spell;

import com.elly.athena.data.Attribute_Register;
import com.elly.athena.entity.Entity_Register;
import com.elly.athena.item.Item_Register;
import com.elly.athena.sound.Sound_Register;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.joml.Math;

import java.util.Objects;

public class WindSlash extends ThrowableItemProjectile {
    private int particleTick;
    private int tick = 0;
    private int stage = 0;
    private int damage_count = 0;
    private Vec3 dir;

    public WindSlash(EntityType<? extends WindSlash> entityType, Level level) {
        super(entityType, level);
        init();
    }

    public WindSlash(Level level, LivingEntity owner, ItemStack item) {
        super(Entity_Register.WINDSLASH, owner, level, item);
        init();
    }

    public WindSlash(Level level, ItemStack item) {
        this(Entity_Register.WINDSLASH, level);
        init();
    }

    public WindSlash(Level level, double x, double y, double z, ItemStack item) {
        super(Entity_Register.WINDSLASH, x, y, z, level, item);
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
        particleTick += 1;
        this.setInvisible(stage == 1);
        if(particleTick > 2){
            particleTick = 0;
            for(int i = 0; i < 4; i++){
                this.level().addParticle(ParticleTypes.SNOWFLAKE, this.getX(), this.getY(), this.getZ(),
                        this.random.nextIntBetweenInclusive(-1, 1) * 0.2F,
                        this.random.nextIntBetweenInclusive(-1, 1) * 0.2F,
                        this.random.nextIntBetweenInclusive(-1, 1) * 0.2F);
            }
        }
        if(stage == 1 && tick > AttackGap() && damage_count < 2){
            RangeAttack();
        }
        else if(stage == 1 && tick > AttackGap() && damage_count >= 2){
            for(int i = 0; i < 8; i++){
                this.level().addParticle(ParticleTypes.SNOWFLAKE, this.getX(), this.getY(), this.getZ(),
                        this.random.nextIntBetweenInclusive(-1, 1) * 0.2F,
                        this.random.nextIntBetweenInclusive(-1, 1) * 0.2F,
                        this.random.nextIntBetweenInclusive(-1, 1) * 0.2F);
            }
            this.remove(RemovalReason.KILLED);
        }
        if(isInWater() || isInLava()){
            this.remove(RemovalReason.KILLED);
        }
    }
    private int AttackGap() {
        return 10;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        RangeAttack();
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        this.remove(RemovalReason.KILLED);
    }

    private void RangeAttack(){
        stage = 1;
        tick = 0;
        damage_count++;
        if (this.level() instanceof ServerLevel sl) {
            for(var en: sl.getAllEntities()){
                if(en instanceof LivingEntity target && en.isAlive()){
                    if(en.position().distanceTo(this.position()) < 3F){
                        HitEntity(target);
                    }
                }
            }
        }
    }

    private void HitEntity(Entity target){
        if(this.getOwner() instanceof Player player){
            int attack = (int) Objects.requireNonNull(player.getAttribute(Attributes.ATTACK_DAMAGE)).getValue();
            int attack_max = (int) Objects.requireNonNull(player.getAttribute(Attribute_Register.DAMAGE_MAX)).getValue();
            int d = this.getRandom().nextIntBetweenInclusive(attack, Math.max(attack + attack_max, attack + 1));
            target.hurt(this.damageSources().thrown(this, this.getOwner()), (float)d);
            if(player.level() instanceof ServerLevel sl){
                sl.playSound(null, target.getX(), target.getY(), target.getZ(), Sound_Register.HIT0.get(), SoundSource.HOSTILE, 1F, 1F);
            }
        }
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return Item_Register.ENTITY_MAGICBALL.get();
    }
}
