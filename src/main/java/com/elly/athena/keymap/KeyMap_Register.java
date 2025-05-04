package com.elly.athena.keymap;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.jarjar.nio.util.Lazy;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyMap_Register {
    public static Lazy<KeyMapping> SKILL_MAPPING = Lazy.of(() -> new KeyMapping("key.athena.skill", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_K, "key.categories.athena"));
    public static Lazy<KeyMapping> STATUS_MAPPING = Lazy.of(() -> new KeyMapping("key.athena.status", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_I, "key.categories.athena"));
    public static Lazy<KeyMapping> SWITCH_MAPPING = Lazy.of(() -> new KeyMapping("key.athena.switch", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, "key.categories.athena"));

    public void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(SKILL_MAPPING.get());
        event.register(STATUS_MAPPING.get());
        event.register(SWITCH_MAPPING.get());
    }

    public void onClientTick(ClientTickEvent.Post event) {
        Player player = Minecraft.getInstance().player;
        if(player == null) return;

        while (SKILL_MAPPING.get().consumeClick()) {
            com.elly.athena.Athena.LOGGER.info("%s is trying to check skill", player.getName().getString());
        }
        while (STATUS_MAPPING.get().consumeClick()) {
            com.elly.athena.Athena.LOGGER.info("%s is trying to check status", player.getName().getString());
        }
        while (SWITCH_MAPPING.get().consumeClick()) {
            com.elly.athena.Athena.LOGGER.info("%s is trying to switch mode", player.getName().getString());
        }
    }
}
