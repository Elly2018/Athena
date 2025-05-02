package com.elly.rpg.keymap;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.util.Lazy;
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
}
