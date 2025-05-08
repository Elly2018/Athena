package com.elly.athena.data;

import com.elly.athena.Athena;
import com.elly.athena.data.attribute.MagicAttribute;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.elly.athena.Athena.ATTRIBUTES;

@EventBusSubscriber(modid = Athena.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Attribute_Register {

    public static final DeferredHolder<Attribute, MagicAttribute> MAGIC_ATTACK = ATTRIBUTES.register("magic_attack", () -> new MagicAttribute("attribute.magic_attack", 0));

    @SubscribeEvent
    public static void createDefaultAttributes(EntityAttributeCreationEvent event) {
        /**
        event.put(
                EntityType.PLAYER,
                LivingEntity.createLivingAttributes()
                        .add(Attribute_Register.MAGIC_ATTACK)
                        .add(Attribute_Register.MAGIC_ATTACK, 0)
                        .build()
        );
         */
    }
}
