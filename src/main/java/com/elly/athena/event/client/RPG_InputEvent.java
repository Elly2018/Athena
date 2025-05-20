package com.elly.athena.event.client;

import com.elly.athena.Athena;
import com.elly.athena.event.ClientGameHandler;
import com.elly.athena.network.general.EventGeneralPayload;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = Athena.MODID, bus = EventBusSubscriber.Bus.GAME)
public class RPG_InputEvent {

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onClientsScroll(InputEvent.MouseScrollingEvent event){
        if(ClientGameHandler.minecraft.level != null && ClientGameHandler.minecraft.screen == null){
            PacketDistributor.sendToServer(EventGeneralPayload.GenerateData_Input(EventGeneralPayload.InputMeta.SELECTION));
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onClientsKey(InputEvent.Key event){
        if(ClientGameHandler.minecraft.level != null && ClientGameHandler.minecraft.screen == null){
            if(event.getKey() >= GLFW.GLFW_KEY_0 && event.getKey() <= GLFW.GLFW_KEY_9){
                PacketDistributor.sendToServer(EventGeneralPayload.GenerateData_Input(EventGeneralPayload.InputMeta.SELECTION));
            }
        }
    }
}
