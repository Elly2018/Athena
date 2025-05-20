package com.elly.athena.event.client;

import com.elly.athena.Athena;
import com.elly.athena.event.ClientGameHandler;
import com.elly.athena.gui.screen.PlayerInteract_Screen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = Athena.MODID, bus = EventBusSubscriber.Bus.GAME)
public class RPG_PlayerInteractEvent {

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event){
        if (event.getTarget() instanceof Player){
            Player target = (Player) event.getTarget();
            Player source = event.getEntity();
            ClientGameHandler.gui_worker.add(() -> {
                Minecraft.getInstance().setScreen(new PlayerInteract_Screen(source, target));
            });

            event.setCancellationResult(InteractionResult.SUCCESS);
            event.setCanceled(true);
        }
    }

}
