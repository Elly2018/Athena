package com.elly.athena.event.common;

import com.elly.athena.Athena;
import com.elly.athena.Config;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingExperienceDropEvent;

@EventBusSubscriber(modid = Athena.MODID)
public class RPG_LivingExperienceDropEvent {

    @SubscribeEvent
    public static void onLivingExpDrop(LivingExperienceDropEvent event){
        if(!Config.vanilla_exp_drop){
            event.setCanceled(true);
        }
    }
}
