package com.elly.athena.event.common;

import com.elly.athena.Athena;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber(modid = Athena.MODID)
public class RPG_LivingDeathEvent {

    @SubscribeEvent
    public static void onLivingDeathEvent(LivingDeathEvent event){

    }
}
