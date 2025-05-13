package com.elly.athena.effect;

import com.elly.athena.Athena;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class Phalanx extends MobEffect {
    private ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "effect.phalanx");

    public Phalanx() {
        super(MobEffectCategory.BENEFICIAL, 9154528);
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        return super.applyEffectTick(level, entity, amplifier);
    }

    @Override
    public void onEffectStarted(LivingEntity entity, int amplifier) {
        super.onEffectStarted(entity, amplifier);
        AttributeMap map = entity.getAttributes();
        AttributeInstance instance = map.getInstance(Attributes.ARMOR);
        if(instance != null){
            instance.removeModifier(id);
            instance.addOrUpdateTransientModifier(GetModifier(amplifier));
        }
    }

    @Override
    public void onMobRemoved(ServerLevel level, LivingEntity entity, int amplifier, Entity.RemovalReason reason) {
        super.onMobRemoved(level, entity, amplifier, reason);
        AttributeMap map = entity.getAttributes();
        AttributeInstance instance = map.getInstance(Attributes.ARMOR);
        if(instance != null){
            instance.removeModifier(id);
        }
    }

    AttributeModifier GetModifier(int amplifier){
        return new AttributeModifier(id, 2F * amplifier, AttributeModifier.Operation.ADD_VALUE);
    }
}
