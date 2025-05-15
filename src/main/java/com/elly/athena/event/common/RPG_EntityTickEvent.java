package com.elly.athena.event.common;

import com.elly.athena.Athena;
import com.elly.athena.item.entity.Entity_MagicBall;
import net.minecraft.world.entity.projectile.Snowball;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = Athena.MODID, bus = EventBusSubscriber.Bus.GAME)
public class RPG_EntityTickEvent {

    @SubscribeEvent
    public static void onEntityTickEvent(EntityTickEvent.Pre event){
        if(event.getEntity() instanceof Snowball spell){
            if(spell.getItem().getItem() instanceof Entity_MagicBall.Entity_MagicBall_Item magicBall){

            }
        }
    }
}
