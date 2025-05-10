package com.elly.athena;

import com.elly.athena.command.Command_Register;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.implementation.PlayerStatus;
import com.elly.athena.data.interfaceType.IPlayerSkill;
import com.elly.athena.data.interfaceType.IPlayerStatus;
import com.elly.athena.data.types.ModContainer;
import com.elly.athena.gui.Hud;
import com.elly.athena.gui.screen.PlayerInteract_Screen;
import com.elly.athena.gui.screen.Status_Screen;
import com.elly.athena.network.general.StatusPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static com.elly.athena.keymap.KeyMap_Register.STATUS_MAPPING;
import static com.elly.athena.keymap.KeyMap_Register.SWITCH_MAPPING;

@EventBusSubscriber(modid = Athena.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ClientGameHandler {
    public static Hud hub;
    static BlockingQueue<Runnable> gui_worker = new ArrayBlockingQueue<>(5);

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event){
        if (event.getTarget() instanceof Player){
            Player target = (Player) event.getTarget();
            Player source = event.getEntity();
            gui_worker.add(() -> {
                Minecraft.getInstance().setScreen(new PlayerInteract_Screen(source, target));
            });

            event.setCancellationResult(InteractionResult.SUCCESS);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void renderGUI(RenderGuiLayerEvent.Pre event) throws Exception {
        if(hub == null) hub = new Hud();
        hub.renderGUI(event);
        while (!gui_worker.isEmpty()){
            gui_worker.take().run();
        }
    }

    @SubscribeEvent
    public static void renderPlayer(RenderPlayerEvent.Pre event) {

    }

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event){
        Command_Register.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft instance = Minecraft.getInstance();
        Player player = instance.player;
        assert player != null;

        while (STATUS_MAPPING.get().consumeClick()) {
            instance.setScreen(new Status_Screen(player));
        }
        while (SWITCH_MAPPING.get().consumeClick()) {
            PlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
            int mode = ps.getMode();
            ps.setMode(mode == 0 ? 1 : 0);
            PacketDistributor.sendToServer(new StatusPayload.StatusData(ps.serializeNBT(player.registryAccess())));
        }
    }
}
