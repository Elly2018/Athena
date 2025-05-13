package com.elly.athena.effect;

import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.elly.athena.Athena.MOB_EFFECTS;

public class Effect_Register {
    public static DeferredHolder<MobEffect, Phalanx> PHALANX;

    public static void RegisterAllEffect(){
        Effect_Register.PHALANX = MOB_EFFECTS.register("phalanx", Phalanx::new);
    }
}
