package com.elly.athena;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@EventBusSubscriber(modid = Athena.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue HUNGER_EXIST = BUILDER
            .comment("Is hunger exist in the RPG world")
            .define("hunger_exist", false);

    private static final ModConfigSpec.BooleanValue AIR_EXIST = BUILDER
            .comment("Is air exist in the RPG world")
            .define("air_exist", false);

    private static final ModConfigSpec.BooleanValue VANILLA_EXP_DROP = BUILDER
            .comment("What you want the introduction message to be for the magic number")
            .define("vanilla_exp_drop", false);

    private static final ModConfigSpec.BooleanValue DAMAGE_COOLDOWN = BUILDER
            .comment("Has damage cooldown")
            .define("damage_cooldown", false);

    // a list of strings that are treated as resource locations for items
    private static final ModConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), Config::validateItemName);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean hunger_exist;
    public static boolean air_exist;
    public static boolean vanilla_exp_drop;
    public static boolean damage_cooldown;
    public static Set<Item> items;

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        hunger_exist = HUNGER_EXIST.get();
        air_exist = AIR_EXIST.get();
        vanilla_exp_drop = VANILLA_EXP_DROP.get();
        damage_cooldown = DAMAGE_COOLDOWN.get();

        // convert the list of strings into a set of items
        items = ITEM_STRINGS.get().stream()
                .map(itemName -> BuiltInRegistries.ITEM.getValue(ResourceLocation.parse(itemName)))
                .collect(Collectors.toSet());
    }
}
