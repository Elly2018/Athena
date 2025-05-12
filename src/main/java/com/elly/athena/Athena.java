package com.elly.athena;

import com.elly.athena.block.Blocks_Register;
import com.elly.athena.blockitem.BlockItems_Register;
import com.elly.athena.effect.Effect_Register;
import com.elly.athena.effect.Phalanx;
import com.elly.athena.item.Item_Register;
import com.elly.athena.sound.Sound_Register;
import com.elly.athena.tabs.CreativeTabs_Register;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import static com.elly.athena.data.Attachment_Register.ATTACHMENT;
import static com.elly.athena.gui.GUI_Register.MENU_TYPES;
import static com.elly.athena.tabs.CreativeTabs_Register.CREATIVE_MODE_TABS;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(com.elly.athena.Athena.MODID)
public class Athena {
    public static final String MODID = "athena";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, MODID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(BuiltInRegistries.POTION, MODID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MODID);
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, MODID);
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, MODID);
    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MODID);
    public static final DeferredRegister<GameEvent> EVENT = DeferredRegister.create(BuiltInRegistries.GAME_EVENT, MODID);

    public Athena(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        ATTRIBUTES.register(modEventBus);
        ATTACHMENT.register(modEventBus);
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        POTIONS.register(modEventBus);
        SOUNDS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        RECIPE.register(modEventBus);
        MENU_TYPES.register(modEventBus);
        MOB_EFFECTS.register(modEventBus);
        ENTITY.register(modEventBus);
        EVENT.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        Effect_Register.RegisterAllEffect();
        Blocks_Register.RegisterAllBlocks();
        BlockItems_Register.RegisterAllItems();
        Item_Register.RegisterAllItems();
        Sound_Register.registerSounds();
        CreativeTabs_Register.RegisterAllTabs();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    @SubscribeEvent
    private void onServerTick(ServerTickEvent.Post event) {
        event.getServer().getPlayerList().getPlayers().forEach(x -> {
            if(!Config.hunger_exist){
                FoodData fd = x.getFoodData();
                fd.setFoodLevel(16);
                fd.setSaturation(0);
            }
            if(!Config.air_exist){
                x.setAirSupply(20);
            }
        });
    }
}

