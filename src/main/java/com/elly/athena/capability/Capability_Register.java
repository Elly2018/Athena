package com.elly.athena.capability;

import com.elly.athena.Athena;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@EventBusSubscriber(modid = Athena.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Capability_Register {

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event){

    }
}
