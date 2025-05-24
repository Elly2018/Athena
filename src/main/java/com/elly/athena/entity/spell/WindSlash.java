package com.elly.athena.entity.spell;

import com.elly.athena.data.Attribute_Register;
import com.elly.athena.entity.Entity_Register;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Math;

import java.util.Objects;

public class WindSlash extends Projectile {
    private int tick = 0;
    private int stage = 0;
    private int damage_count = 0;
    private Vec3 dir;

    public WindSlash(EntityType<? extends WindSlash> entityType, Level level) {
        super(entityType, level);
        init();
    }

    public WindSlash(Level level, LivingEntity shooter) {
        this(Entity_Register.WINDSLASH, level);
        this.setOwner(shooter);
        Vec3 vec3 = shooter.position();
        dir = shooter.getForward();
        vec3.add(dir);
        this.moveTo(vec3.x, vec3.y, vec3.z, this.getYRot(), this.getXRot());
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
        if(stage == 0 && tick > SlashTimer() * 2 + 30){
            this.remove(RemovalReason.KILLED);
        }
        else if(stage == 1 && tick > AttackGap() && damage_count < 2){
            RangeAttack();
        }
        else if(stage == 1 && tick > AttackGap() && damage_count >= 2){
            for(int i = 0; i < 4; i++){
                this.level().addParticle(ParticleTypes.SNOWFLAKE, this.getX(), this.getY(), this.getZ(),
                        this.random.nextIntBetweenInclusive(-1, 1) * 0.2F,
                        this.random.nextIntBetweenInclusive(-1, 1) * 0.2F,
                        this.random.nextIntBetweenInclusive(-1, 1) * 0.2F);
            }
            this.remove(RemovalReason.KILLED);
        }
    }

    private int SlashTimer () {
        return 20 * 2;
    }
    private int AttackGap() {
        return 10;
    }

    public float GetState0(){ // 0-1 => 0-40
        if(stage > 0) return 0F;
        return Math.max(1F, (float) tick / (float)SlashTimer());
    }

    public float GetState1(){ // 0-1 => 40-80
        if(stage > 0) return 0F;
        if(tick < SlashTimer()) return 0;
        return Math.max(1F, (float) tick - SlashTimer() / (float)SlashTimer());
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        RangeAttack();
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
    protected void defineSynchedData(SynchedEntityData.Builder builder) { }
}
