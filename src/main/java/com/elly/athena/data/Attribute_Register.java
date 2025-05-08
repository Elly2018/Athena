package com.elly.athena.data;

import com.elly.athena.Athena;
import com.elly.athena.data.attribute.MagicAttribute;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.elly.athena.Athena.ATTRIBUTES;

@EventBusSubscriber(modid = Athena.MODID)
public class Attribute_Register {

    public static final DeferredHolder<Attribute, MagicAttribute> MAGIC_ATTACK_ATTRIBUTE = ATTRIBUTES.register("magic_attack", () -> new MagicAttribute("attribute.magic_attack", 0));

    @SubscribeEvent
    public static void onItemAttribute(ItemAttributeModifierEvent event){

    }

    @SubscribeEvent
    public static void createDefaultAttributes(EntityAttributeCreationEvent event) {

    }
}
