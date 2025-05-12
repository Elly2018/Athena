package com.elly.athena.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class RPGNPC extends PathfinderMob {

    public RPGNPC(EntityType<? extends RPGNPC> type, Level world) {
        super(type, world);
    }

    public RPGNPC(Level level) {
        this(Entity_Register.NPC.get(), level);
    }
}
