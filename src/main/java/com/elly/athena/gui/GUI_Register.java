package com.elly.athena.gui;

import com.elly.athena.gui.menu.Equipment_Menu;
import com.elly.athena.gui.menu.Market_Menu;
import com.elly.athena.gui.menu.Skill_Menu;
import com.elly.athena.gui.menu.Trade_Menu;
import com.elly.athena.gui.screen.Equipment_Screen;
import com.elly.athena.gui.screen.Skill_Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.elly.athena.Athena.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class GUI_Register {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(BuiltInRegistries.MENU, MODID);
    public static final Supplier<MenuType<Equipment_Menu>> EQUIPMENT_MENU = MENU_TYPES.register("equipment_menu", () -> new MenuType<>(Equipment_Menu::new, FeatureFlags.DEFAULT_FLAGS));
    public static final Supplier<MenuType<Skill_Menu>> SKILL_MENU = MENU_TYPES.register("skill_menu", () -> new MenuType<>(Skill_Menu::new, FeatureFlags.DEFAULT_FLAGS));
    public static final Supplier<MenuType<Trade_Menu>> TRADE_MENU = MENU_TYPES.register("trade_menu", () -> new MenuType<>(Trade_Menu::new, FeatureFlags.DEFAULT_FLAGS));
    public static final Supplier<MenuType<Market_Menu>> MARKET_MENU = MENU_TYPES.register("market_menu", () -> new MenuType<>(Market_Menu::new, FeatureFlags.DEFAULT_FLAGS));

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(EQUIPMENT_MENU.get(), Equipment_Screen::new);
        event.register(SKILL_MENU.get(), Skill_Screen::new);
    }
}
