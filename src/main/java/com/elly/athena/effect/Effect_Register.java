package com.elly.athena.effect;

import com.elly.athena.Athena;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

public class Effect_Register {
    public static final Holder<MobEffect> PHALANX = register("phalanx", new Phalanx());

    private static Holder<MobEffect> register(String name, MobEffect effect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, ResourceLocation.fromNamespaceAndPath(Athena.MODID, name), effect);
    }
}
