package com.elly.rpg;

import com.elly.rpg.block.Blocks_Register;
import com.elly.rpg.blockitem.BlockItems_Register;
import com.elly.rpg.capability.CapabilitySystem;
import com.elly.rpg.command.Command_Register;
import com.elly.rpg.gui.Hud;
import com.elly.rpg.item.Item_Register;
import com.elly.rpg.keymap.KeyMap_Register;
import com.elly.rpg.sound.Sound_Register;
import com.elly.rpg.tabs.CreativeTabs_Register;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RPG.MODID)
public class RPG {
    public static final String MODID = "athena";
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, MODID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MODID);
    private static final Logger LOGGER = LogUtils.getLogger();
    private final Blocks_Register block_register;
    private final BlockItems_Register blockitem_register;
    private final Item_Register item_register;
    private final Sound_Register sound_register;
    private final CreativeTabs_Register creativeTabs_register;
    private final CapabilitySystem capability_system;
    private final KeyMap_Register keyMap_register;
    private final Hud hud;

    public RPG(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);

        block_register = new Blocks_Register(BLOCKS);
        blockitem_register = new BlockItems_Register(ITEMS, block_register);
        item_register = new Item_Register(ITEMS, POTIONS);
        sound_register = new Sound_Register(SOUNDS);
        creativeTabs_register = new CreativeTabs_Register(CREATIVE_MODE_TABS);
        capability_system = new CapabilitySystem();
        keyMap_register = new KeyMap_Register();
        hud = new Hud();

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        POTIONS.register(modEventBus);
        SOUNDS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        RECIPE.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        block_register.RegisterAllBlocks();
        blockitem_register.RegisterAllItems();
        item_register.RegisterAllItems();
        sound_register.registerSounds(MODID);
        CreativeTabs_Register.RegisterCollection collection = new CreativeTabs_Register.RegisterCollection(
                block_register,
                blockitem_register,
                item_register
        );
        creativeTabs_register.RegisterAllTabs(collection);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    @SubscribeEvent
    public void onAttachingCapabilities(final AttachCapabilitiesEvent<Entity> event) {
        capability_system.onAttachingCapabilities(MODID, event);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @SubscribeEvent
    public void registerBindings(RegisterKeyMappingsEvent event) {
        keyMap_register.registerBindings(event);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void renderOverlay(CustomizeGuiOverlayEvent event) {
        hud.renderOverlay(event);
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public class ModEventListener {
        @SubscribeEvent
        public static void registerCommands(RegisterCommandsEvent event){
            Command_Register.register(event.getDispatcher());
        }
    }
}
