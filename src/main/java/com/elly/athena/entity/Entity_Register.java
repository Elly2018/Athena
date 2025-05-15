package com.elly.athena.entity;

import com.elly.athena.Athena;
import com.elly.athena.entity.mob.TestUseZombie;
import com.elly.athena.entity.mob.WoodElf;
import com.elly.athena.entity.npc.RPGNPC;
import com.elly.athena.entity.spell.MagicBall;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.function.Supplier;

public class Entity_Register {
    // BOSS
    public static final Supplier<EntityType<WoodElf>> WOODELF = Athena.ENTITY.registerEntityType("woodelf", WoodElf::new, MobCategory.MONSTER);
    // MOB
    public static final Supplier<EntityType<TestUseZombie>> TESTZOMBIE = Athena.ENTITY.registerEntityType("testzombie", TestUseZombie::new, MobCategory.MONSTER);
    // NPC
    public static final Supplier<EntityType<RPGNPC>> NPC = Athena.ENTITY.registerEntityType("npc", RPGNPC::new, MobCategory.CREATURE);

    public static EntityType<MagicBall> MAGICBALL;

    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(MAGICBALL, ThrownItemRenderer::new);
    }

    public static void registerEntity(RegisterEvent event){
        event.register(Registries.ENTITY_TYPE, Entity_Register::registerEntity);
    }

    private static void registerEntity(RegisterEvent.RegisterHelper<EntityType<?>> registry){
        EntityType.Builder<MagicBall> t = EntityType.Builder.<MagicBall>of(MagicBall::new, MobCategory.MISC)
                .noLootTable().sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10);
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse(Athena.MODID + ":" + "magic_ball"));
        MAGICBALL = t.build(key);
        registry.register(key, MAGICBALL);
    }
}
