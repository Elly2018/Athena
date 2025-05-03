package com.elly.rpg.gui;

import com.elly.rpg.gui.menu.Skill_Menu;
import com.elly.rpg.gui.menu.Trade_Menu;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class GUI_Register {
    public static Supplier<MenuType<Skill_Menu>> SKILL;
    public static Supplier<MenuType<Trade_Menu>> TRADE;

    private final DeferredRegister<MenuType<?>> MENU_TYPES;

    public GUI_Register(DeferredRegister<MenuType<?>> _MENU_TYPES) {
        this.MENU_TYPES = _MENU_TYPES;
    }

    public void registerMenu(){
        SKILL = this.MENU_TYPES.register("skill", () -> new MenuType(Skill_Menu::new, FeatureFlags.VANILLA_SET));
        TRADE = this.MENU_TYPES.register("trade", () -> new MenuType(Trade_Menu::new, FeatureFlags.VANILLA_SET));
    }
}
