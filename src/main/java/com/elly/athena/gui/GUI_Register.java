package com.elly.athena.gui;

import com.elly.athena.gui.menu.Equipment_Menu;
import com.elly.athena.gui.menu.Market_Menu;
import com.elly.athena.gui.menu.Trade_Menu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.elly.athena.Athena.MODID;

public class GUI_Register {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(BuiltInRegistries.MENU, MODID);
    public static final Supplier<MenuType<Market_Menu>> MARKET_MENU = MENU_TYPES.register("market_menu", () -> new MenuType<>(Market_Menu::new, FeatureFlags.DEFAULT_FLAGS));
    public static final Supplier<MenuType<Equipment_Menu>> EQUIPMENT_MENU = MENU_TYPES.register("equipment_menu", () -> new MenuType<>(Equipment_Menu::new, FeatureFlags.DEFAULT_FLAGS));
    public static final Supplier<MenuType<Trade_Menu>> TRADE_MENU = MENU_TYPES.register("trade_menu", () -> new MenuType<>(Trade_Menu::new, FeatureFlags.DEFAULT_FLAGS));
}
