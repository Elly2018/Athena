package com.elly.athena;

import com.elly.athena.block.Blocks_Register;
import com.elly.athena.blockitem.BlockItems_Register;
import com.elly.athena.capability.Attachment_Register;
import com.elly.athena.command.Command_Register;
import com.elly.athena.gui.GUI_Register;
import com.elly.athena.gui.Hud;
import com.elly.athena.item.Item_Register;
import com.elly.athena.keymap.KeyMap_Register;
import com.elly.athena.sound.Sound_Register;
import com.elly.athena.tabs.CreativeTabs_Register;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.CustomizeGuiOverlayEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(com.elly.athena.Athena.MODID)
public class Athena {
    public static final String MODID = "athena";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, MODID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(BuiltInRegistries.POTION, MODID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, MODID);
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(BuiltInRegistries.MENU, MODID);
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, MODID);
    public static Attachment_Register capability_system;
    private final Blocks_Register block_register;
    private final BlockItems_Register blockitem_register;
    private final Item_Register item_register;
    private final Sound_Register sound_register;
    private final CreativeTabs_Register creativeTabs_register;
    private final GUI_Register gui_register;

    public Athena(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        capability_system = new Attachment_Register(ATTACHMENT);
        block_register = new Blocks_Register(BLOCKS);
        blockitem_register = new BlockItems_Register(ITEMS, block_register);
        item_register = new Item_Register(ITEMS, POTIONS);
        sound_register = new Sound_Register(SOUNDS);
        creativeTabs_register = new CreativeTabs_Register(CREATIVE_MODE_TABS);
        gui_register = new GUI_Register(MENU_TYPES);

        ATTACHMENT.register(modEventBus);
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        POTIONS.register(modEventBus);
        SOUNDS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        RECIPE.register(modEventBus);
        MENU_TYPES.register(modEventBus);
        MOB_EFFECTS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

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
        gui_register.registerMenu();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    @SubscribeEvent
    private void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        public static KeyMap_Register keyMap_register;

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }

        @SubscribeEvent
        public static void registerBindings(RegisterKeyMappingsEvent event) {
            keyMap_register = new KeyMap_Register();
            keyMap_register.registerBindings(event);
        }
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.GAME)
    public static class ClientModGameEvents {
        public static Hud hub;

        @SubscribeEvent(priority = EventPriority.HIGH)
        public static void renderOverlay(CustomizeGuiOverlayEvent.Chat event){
            if(hub == null) hub = new Hud();
            hub.renderOverlay(event);
        }

        @SubscribeEvent(priority = EventPriority.HIGH)
        public static void renderGUI(RenderGuiLayerEvent.Pre event){
            if(hub == null) hub = new Hud();
            hub.renderGUI(event);
        }

        @SubscribeEvent
        public static void onClientTick(ClientTickEvent.Post event) {
            ClientModEvents.keyMap_register.onClientTick(event);
        }

        @SubscribeEvent
        public static void onServerTick(ServerTickEvent.Post event) {
            if(!Config.hunger_exist){
                event.getServer().getPlayerList().getPlayers().forEach(x -> {
                    FoodData fd = x.getFoodData();
                    fd.setFoodLevel(16);
                    fd.setSaturation(0);
                });
            }
        }

        @SubscribeEvent
        public static void registerCommands(RegisterCommandsEvent event){
            Command_Register.register(event.getDispatcher());
        }
    }
}
