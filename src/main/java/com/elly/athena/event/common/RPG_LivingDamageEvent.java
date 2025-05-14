package com.elly.athena.event.common;

import com.elly.athena.Athena;
import com.elly.athena.Config;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber(modid = Athena.MODID)
public class RPG_LivingDamageEvent {

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Pre event){
        if(!Config.damage_cooldown) event.getEntity().invulnerableTime = 0;
        Entity entity = event.getSource().getEntity();
        if(entity instanceof Player player){

        }
    }
}
