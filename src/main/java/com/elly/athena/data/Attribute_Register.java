package com.elly.athena.data;

import com.elly.athena.Athena;
import com.elly.athena.data.interfaceType.attachment.IPlayerStatus;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.lang.reflect.Field;
import java.util.Objects;

import static com.elly.athena.Athena.ATTRIBUTES;

@EventBusSubscriber(modid = Athena.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Attribute_Register {
    public static final DeferredHolder<Attribute, RangedAttribute> LEVEL = ATTRIBUTES.register("level", () -> new RangedAttribute("attribute.name.level", (double)1.0F, (double)1.0F, (double)300.0F));
    public static final DeferredHolder<Attribute, RangedAttribute> MANA = ATTRIBUTES.register("mana", () -> new RangedAttribute("attribute.name.mana", (double)10.0F, (double)0.0F, (double)65536.0F));
    public static final DeferredHolder<Attribute, RangedAttribute> MANA_MAX = ATTRIBUTES.register("mana_max", () -> new RangedAttribute("attribute.name.mana_max", (double)10.0F, (double)1.0F, (double)65536.0F));
    public static final DeferredHolder<Attribute, RangedAttribute> DAMAGE_MAX = ATTRIBUTES.register("damage_max", () -> new RangedAttribute("attribute.name.damage_max", (double)1.0F, (double)1.0F, (double)65536.0F));
    public static final DeferredHolder<Attribute, RangedAttribute> MAGIC_ATTACK = ATTRIBUTES.register("magic_attack", () -> new RangedAttribute("attribute.name.magic_attack", (double)1.0F, (double)1.0F, (double)65536.0F));
    public static final DeferredHolder<Attribute, RangedAttribute> MAGIC_ATTACK_MAX = ATTRIBUTES.register("magic_attack_max", () -> new RangedAttribute("attribute.name.magic_attack_max", (double)1.0F, (double)1.0F, (double)65536.0F));
    public static final DeferredHolder<Attribute, RangedAttribute> MAGIC_ARMOR = ATTRIBUTES.register("magic_armor", () -> new RangedAttribute("attribute.name.magic_armor", (double)10.0F, (double)0.0F, (double)65536.0F));
    public static final DeferredHolder<Attribute, RangedAttribute> DODGE = ATTRIBUTES.register("dodge", () -> new RangedAttribute("attribute.name.dodge", (double)0.0F, (double)0.0F, (double)65536.0F));
    public static final DeferredHolder<Attribute, RangedAttribute> MAGIC_DODGE = ATTRIBUTES.register("magic_dodge", () -> new RangedAttribute("attribute.name.magic_dodge", (double)0.0F, (double)0.0F, (double)65536.0F));
    public static final DeferredHolder<Attribute, RangedAttribute> ACCURACY = ATTRIBUTES.register("accuracy", () -> new RangedAttribute("attribute.name.magic_dodge", (double)1.0F, (double)1.0F, (double)65536.0F));
    public static final DeferredHolder<Attribute, RangedAttribute> MAGIC_ACCURACY = ATTRIBUTES.register("magic_accuracy", () -> new RangedAttribute("attribute.name.magic_dodge", (double)1.0F, (double)1.0F, (double)65536.0F));

    private static Holder<Attribute> register(String name, Attribute attribute) {
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, ResourceLocation.withDefaultNamespace(name), attribute);
    }

    @SubscribeEvent
    public static void createDefaultAttributes(EntityAttributeCreationEvent event) {
        try {
            Field armor = Attributes.ARMOR.value().getClass().getDeclaredField("maxValue");
            Field armor_toughness = Attributes.ARMOR_TOUGHNESS.value().getClass().getDeclaredField("maxValue");
            Field attack_damage = Attributes.ATTACK_DAMAGE.value().getClass().getDeclaredField("maxValue");
            Field sneaking_speed = Attributes.SNEAKING_SPEED.value().getClass().getDeclaredField("maxValue");

            armor.setAccessible(true);
            armor_toughness.setAccessible(true);
            attack_damage.setAccessible(true);
            sneaking_speed.setAccessible(true);

            armor.set(Attributes.ARMOR.value(), (double)65536.0F);
            armor_toughness.set(Attributes.ARMOR_TOUGHNESS.value(), (double)65536.0F);
            attack_damage.set(Attributes.ATTACK_DAMAGE.value(), (double)65536.0F);
            sneaking_speed.set(Attributes.SNEAKING_SPEED.value(), (double)20.0F);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @SubscribeEvent
    public static void existingEntityAttributes(EntityAttributeModificationEvent event) {
        Athena.LOGGER.debug("Attribute checker: EntityAttributeModificationEvent");
        if(!event.has(EntityType.PLAYER, LEVEL)) event.add(EntityType.PLAYER, LEVEL);
        if(!event.has(EntityType.PLAYER, MANA)) event.add(EntityType.PLAYER, MANA);
        if(!event.has(EntityType.PLAYER, MANA_MAX)) event.add(EntityType.PLAYER, MANA_MAX);
        if(!event.has(EntityType.PLAYER, DAMAGE_MAX)) event.add(EntityType.PLAYER, DAMAGE_MAX);
        if(!event.has(EntityType.PLAYER, MAGIC_ATTACK)) event.add(EntityType.PLAYER, MAGIC_ATTACK);
        if(!event.has(EntityType.PLAYER, MAGIC_ATTACK_MAX)) event.add(EntityType.PLAYER, MAGIC_ATTACK_MAX);
        if(!event.has(EntityType.PLAYER, MAGIC_ARMOR)) event.add(EntityType.PLAYER, MAGIC_ARMOR);
        if(!event.has(EntityType.PLAYER, DODGE)) event.add(EntityType.PLAYER, DODGE);
        if(!event.has(EntityType.PLAYER, MAGIC_DODGE)) event.add(EntityType.PLAYER, MAGIC_DODGE);
        if(!event.has(EntityType.PLAYER, ACCURACY)) event.add(EntityType.PLAYER, ACCURACY);
        if(!event.has(EntityType.PLAYER, MAGIC_ACCURACY)) event.add(EntityType.PLAYER, MAGIC_ACCURACY);
    }

    private static final ResourceLocation GLOBAL_Health_MAX = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "modifier.global.max_hp");
    private static final ResourceLocation GLOBAL_DAMAGE = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "modifier.global.damage");
    private static final ResourceLocation GLOBAL_ATTACK_SPEED = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "modifier.global.attack_speed");
    private static final ResourceLocation GLOBAL_MANA_MAX = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "modifier.global.max_mp");

    public static void ApplyChange(Player player){
        IPlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
        AttributeMap map = player.getAttributes();
        int level = ps.getLevel();
        int _str = ps.getStr();
        int _dex = ps.getDex();
        int _int = ps.getInt();
        int _luk = ps.getLuk();
        Objects.requireNonNull(map.getInstance(LEVEL)).setBaseValue(level);

        AttributeModifier globalHealth = new AttributeModifier(GLOBAL_Health_MAX, _str + level, AttributeModifier.Operation.ADD_VALUE);
        AttributeModifier globalManaMax = new AttributeModifier(GLOBAL_MANA_MAX, _int + level, AttributeModifier.Operation.ADD_VALUE);
        ApplyModifier(Attributes.MAX_HEALTH, globalHealth, map);
        ApplyModifier(MANA_MAX, globalManaMax, map);
    }

    private static void ApplyModifier(Holder<Attribute> attri, AttributeModifier target, AttributeMap map){
        AttributeInstance attributeinstance = map.getInstance(attri);
        if (attributeinstance != null) {
            attributeinstance.removeModifier(target.id());
            attributeinstance.addTransientModifier(target);
        }
    }
}
