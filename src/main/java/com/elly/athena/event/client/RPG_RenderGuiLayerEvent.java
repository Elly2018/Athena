package com.elly.athena.event.client;

import com.elly.athena.Athena;
import com.elly.athena.event.ClientGameHandler;
import com.elly.athena.gui.Hud;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;

@EventBusSubscriber(modid = Athena.MODID, bus = EventBusSubscriber.Bus.GAME)
public class RPG_RenderGuiLayerEvent {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void renderGUI(RenderGuiLayerEvent.Pre event) {
        if(ClientGameHandler.hub == null) ClientGameHandler.hub = new Hud();
        ClientGameHandler.hub.renderGUI(event);
        while (!ClientGameHandler.gui_worker.isEmpty()){
            try {
                ClientGameHandler.gui_worker.take().run();
            } catch (InterruptedException e) {
                Athena.LOGGER.error(e.getLocalizedMessage());
            }
        }
    }
}
