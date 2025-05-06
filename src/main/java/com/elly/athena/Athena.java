package com.elly.athena;

import com.elly.athena.block.Blocks_Register;
import com.elly.athena.blockitem.BlockItems_Register;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.IPlayerStatus;
import com.elly.athena.gui.GUI_Register;
import com.elly.athena.item.Item_Register;
import com.elly.athena.sound.Sound_Register;
import com.elly.athena.system.BattleSystem;
import com.elly.athena.tabs.CreativeTabs_Register;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
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
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, MODID);
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, MODID);
    public static BattleSystem battle_System;
    public static Blocks_Register block_register;
    public static BlockItems_Register blockitem_register;
    public static Item_Register item_register;
    public static Sound_Register sound_register;
    public static CreativeTabs_Register creativeTabs_register;
    public static GUI_Register gui_register;

    public Athena(IEventBus modEventBus, ModContainer modContainer) {
        battle_System = new BattleSystem();
        block_register = new Blocks_Register(BLOCKS);
        sound_register = new Sound_Register(SOUNDS);

        modEventBus.addListener(this::commonSetup);

        ATTACHMENT.register(modEventBus);
        BLOCKS.register(modEventBus);
        SOUNDS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        RECIPE.register(modEventBus);
        MENU_TYPES.register(modEventBus);
        MOB_EFFECTS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        block_register.RegisterAllBlocks();
        blockitem_register.RegisterAllItems();
        Item_Register.RegisterAllItems();
        sound_register.registerSounds();
        CreativeTabs_Register.RegisterCollection collection = new CreativeTabs_Register.RegisterCollection(
                block_register,
                blockitem_register,
                item_register
        );
        CreativeTabs_Register.RegisterAllTabs(collection);
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
            battle_System.updateHealth(x);;
        });
    }
}
