package com.elly.athena.gui;

import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GUI_Register {

    private final DeferredRegister<MenuType<?>> MENU_TYPES;

    public GUI_Register(DeferredRegister<MenuType<?>> _MENU_TYPES) {
        this.MENU_TYPES = _MENU_TYPES;
    }

    public void registerMenu(){
    }
}
