package com.elly.athena;

import com.elly.athena.command.Command_Register;
import com.elly.athena.data.implementation.PlayerStatus;
import com.elly.athena.gui.Hud;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import static com.elly.athena.keymap.KeyMap_Register.*;

@EventBusSubscriber(modid = Athena.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ClientGameHandler {
    public static Hud hub;

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void renderGUI(RenderGuiLayerEvent.Pre event){
        if(hub == null) hub = new Hud();
        hub.renderGUI(event);
    }

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event){
        Command_Register.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
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
