package com.elly.athena.gui;

import com.elly.athena.gui.menu.Status_Menu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.elly.athena.Athena.MODID;

public class GUI_Register {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(BuiltInRegistries.MENU, MODID);
    public static final Supplier<MenuType<Status_Menu>> STATUS_MENU = MENU_TYPES.register("status_menu", () -> new MenuType<>(Status_Menu::new, FeatureFlags.DEFAULT_FLAGS));
}
