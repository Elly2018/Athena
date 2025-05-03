package com.elly.rpg.keymap;

import com.elly.rpg.gui.menu.Skill_Menu;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.TickEvent;
import org.lwjgl.glfw.GLFW;

public class KeyMap_Register {
    public static Lazy<KeyMapping> SKILL_MAPPING = Lazy.of(() -> new KeyMapping("key.rpg.skill", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_I, "key.categories.rpg"));
    public static Lazy<KeyMapping> STATUS_MAPPING = Lazy.of(() -> new KeyMapping("key.rpg.status", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_I, "key.categories.rpg"));
    public static Lazy<KeyMapping> SWITCH_MAPPING = Lazy.of(() -> new KeyMapping("key.rpg.switch", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, "key.categories.rpg"));

    public void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(SKILL_MAPPING.get());
        event.register(STATUS_MAPPING.get());
        event.register(SWITCH_MAPPING.get());
    }

    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) { // Only call code once as the tick event is called twice every tick
            Player player = Minecraft.getInstance().player;
            if(player == null) return;

            while (SKILL_MAPPING.get().consumeClick()) {

                player.openMenu(new SimpleMenuProvider(
                        (containerId, playerInventory, _player) -> new Skill_Menu(containerId, playerInventory),
                        Component.translatable("menu.title.skill.menu")
                ));
            }
            while (STATUS_MAPPING.get().consumeClick()) {

            }
            while (SWITCH_MAPPING.get().consumeClick()) {

            }
        }
    }
}
