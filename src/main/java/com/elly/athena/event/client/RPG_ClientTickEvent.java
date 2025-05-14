package com.elly.athena.event.client;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.implementation.PlayerStatus;
import com.elly.athena.event.ClientGameHandler;
import com.elly.athena.gui.screen.Status_Screen;
import com.elly.athena.network.general.EventGeneralPayload;
import com.elly.athena.network.general.StatusPayload;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import static com.elly.athena.keymap.KeyMap_Register.STATUS_MAPPING;
import static com.elly.athena.keymap.KeyMap_Register.SWITCH_MAPPING;

@EventBusSubscriber(modid = Athena.MODID, bus = EventBusSubscriber.Bus.GAME)
public class RPG_ClientTickEvent {

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onClientTickPre(ClientTickEvent.Pre event){
        KeyMapping[] maps = ClientGameHandler.minecraft.options.keyHotbarSlots;
        for(KeyMapping key: maps){
            if(key.isDown()){
                PacketDistributor.sendToServer(EventGeneralPayload.GenerateData_Input(EventGeneralPayload.InputMeta.SELECTION));
                break;
            }
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onClientTickPost(ClientTickEvent.Post event) {
        Minecraft instance = Minecraft.getInstance();
        Player player = instance.player;
        assert player != null;

        boolean status_mapping_buffer = STATUS_MAPPING.get().isDown();
        boolean switch_mapping_buffer = SWITCH_MAPPING.get().isDown();

        if (!ClientGameHandler.status_mapping && status_mapping_buffer) {
            instance.setScreen(new Status_Screen(player));
            ClientGameHandler.status_mapping = true;
        }
        else if (ClientGameHandler.status_mapping && !status_mapping_buffer){
            ClientGameHandler.status_mapping = false;
        }

        if (!ClientGameHandler.switch_mapping && switch_mapping_buffer) {
            PlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
            int mode = ps.getMode();
            ps.setMode(mode == 0 ? 1 : 0);
            PacketDistributor.sendToServer(new StatusPayload.StatusData(ps.serializeNBT(player.registryAccess())));
            ClientGameHandler.switch_mapping = true;
        }
        else if (ClientGameHandler.switch_mapping && !switch_mapping_buffer){
            ClientGameHandler.switch_mapping = false;
        }
    }
}
