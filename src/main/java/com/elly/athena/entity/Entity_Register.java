package com.elly.athena.entity;

import com.elly.athena.Athena;
import com.elly.athena.entity.mob.TestUseZombie;
import com.elly.athena.entity.mob.WoodElf;
import com.elly.athena.entity.npc.RPGNPC;
import com.elly.athena.entity.spell.*;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.SpectralArrowRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.TippableArrowRenderer;
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
    // ETC
    public static EntityType<AdvancedArrow> ADVANCEDARROW;
    public static EntityType<MagicBall> MAGICBALL;
    public static EntityType<WindSlash> WINDSLASH;

    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ADVANCEDARROW, TippableArrowRenderer::new);
        event.registerEntityRenderer(MAGICBALL, ThrownItemRenderer::new);
        event.registerEntityRenderer(WINDSLASH, ThrownItemRenderer::new);
    }

    public static void registerEntityRenderersLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
    }

    public static void registerEntity(RegisterEvent event){
        event.register(Registries.ENTITY_TYPE, Entity_Register::registerEntity);
    }

    private static void registerEntity(RegisterEvent.RegisterHelper<EntityType<?>> registry){
        register_Arrow(registry);
        register_MagicBall(registry);
        register_Windslash(registry);
    }

    private static void register_Arrow(RegisterEvent.RegisterHelper<EntityType<?>> registry){
        EntityType.Builder<AdvancedArrow> t = EntityType.Builder.<AdvancedArrow>of(AdvancedArrow::new, MobCategory.MISC)
                .noLootTable().noLootTable().sized(0.5F, 0.5F).eyeHeight(0.13F).clientTrackingRange(4).updateInterval(20);
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse(Athena.MODID + ":" + "advanced_arrow"));
        ADVANCEDARROW = t.build(key);
        registry.register(key, ADVANCEDARROW);
    }

    private static void register_MagicBall(RegisterEvent.RegisterHelper<EntityType<?>> registry){
        EntityType.Builder<MagicBall> t = EntityType.Builder.<MagicBall>of(MagicBall::new, MobCategory.MISC)
                .noLootTable().sized(3.2F, 3.2F).clientTrackingRange(4).updateInterval(10);
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse(Athena.MODID + ":" + "magic_ball"));
        MAGICBALL = t.build(key);
        registry.register(key, MAGICBALL);
    }

    private static void register_Windslash(RegisterEvent.RegisterHelper<EntityType<?>> registry){
        EntityType.Builder<WindSlash> t = EntityType.Builder.<WindSlash>of(WindSlash::new, MobCategory.MISC)
                .noLootTable().sized(3.2F, 32).clientTrackingRange(4).updateInterval(10);
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse(Athena.MODID + ":" + "wind_slash"));
        WINDSLASH = t.build(key);
        registry.register(key, WINDSLASH);
    }
}
