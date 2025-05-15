package com.elly.athena.event;

import com.elly.athena.Athena;
import com.elly.athena.command.Command_Register;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.implementation.PlayerStatus;
import com.elly.athena.entity.npc.RPGNPC_Renderer;
import com.elly.athena.gui.Hud;
import com.elly.athena.gui.screen.PlayerInteract_Screen;
import com.elly.athena.gui.screen.Status_Screen;
import com.elly.athena.network.general.EventGeneralPayload;
import com.elly.athena.network.general.StatusPayload;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static com.elly.athena.entity.Entity_Register.NPC;
import static com.elly.athena.keymap.KeyMap_Register.STATUS_MAPPING;
import static com.elly.athena.keymap.KeyMap_Register.SWITCH_MAPPING;

public class ClientGameHandler {
    public static Hud hub;
    public static BlockingQueue<Runnable> gui_worker = new ArrayBlockingQueue<>(5);
    public static boolean status_mapping = false;
    public static boolean switch_mapping = false;
    public static Minecraft minecraft = null;
}
