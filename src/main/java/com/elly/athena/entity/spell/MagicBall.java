package com.elly.athena.entity.spell;

import com.elly.athena.entity.Entity_Register;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;

public class MagicBall extends AbstractHurtingProjectile {

    public MagicBall(EntityType<? extends AbstractHurtingProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public MagicBall(Level level) {
        this(Entity_Register.MAGICBALL.get(), level);
    }
}
