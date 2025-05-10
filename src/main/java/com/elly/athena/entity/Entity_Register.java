package com.elly.athena.entity;

import com.elly.athena.Athena;
import com.elly.athena.entity.mob.WoodElf;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;

import static com.elly.athena.Athena.MODID;

public class Entity_Register {
    public static final Supplier<EntityType<WoodElf>> WOODELF = register("woodelf", Entity_Register::woodelf);


    private static <E extends Entity> Supplier<EntityType<E>> register(final String name, final Supplier<EntityType.Builder<E>> sup) {
        return Athena.ENTITY.register(name, () -> sup.get().build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse(MODID + ":" + name))));
    }

    private static EntityType.Builder<WoodElf> woodelf(){
        return EntityType.Builder.<WoodElf>of(WoodElf::new, MobCategory.MONSTER)
                .sized(0.5f, 0.5f)
                .setTrackingRange(4)
                .setUpdateInterval(3)
                .setShouldReceiveVelocityUpdates(true);
    }
}
