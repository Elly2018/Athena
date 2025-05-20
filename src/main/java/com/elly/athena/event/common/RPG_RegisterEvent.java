package com.elly.athena.event.common;

import com.elly.athena.Athena;
import com.elly.athena.entity.Entity_Register;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(modid = Athena.MODID, bus = EventBusSubscriber.Bus.MOD)
public class RPG_RegisterEvent {

    @SubscribeEvent
    public static void registerEntity(RegisterEvent event){
        Entity_Register.registerEntity(event);
    }
}
