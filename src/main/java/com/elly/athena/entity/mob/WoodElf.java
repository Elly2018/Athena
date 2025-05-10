package com.elly.athena.entity.mob;

import com.elly.athena.entity.Entity_Register;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class WoodElf extends Monster {
    public WoodElf(EntityType<? extends WoodElf> type, Level world) {
        super(type, world);
    }

    public WoodElf(Level level) {
        this(Entity_Register.WOODELF.get(), level);
    }
}
