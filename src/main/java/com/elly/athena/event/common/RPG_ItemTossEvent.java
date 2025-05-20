package com.elly.athena.event.common;

import com.elly.athena.Athena;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;

@EventBusSubscriber(modid = Athena.MODID)
public class RPG_ItemTossEvent {

    @SubscribeEvent
    public static void onTossItem(ItemTossEvent event){

    }
}
