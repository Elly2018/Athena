package com.elly.athena.event.common;

import com.elly.athena.Athena;
import com.elly.athena.system.BattleSystem;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;

@EventBusSubscriber(modid = Athena.MODID)
public class RPG_LivingEquipmentChangeEvent {

    @SubscribeEvent
    public static void onEquipment(LivingEquipmentChangeEvent event){
        if(event.getEntity() instanceof Player player){
            BattleSystem.ApplyEquipmentChange(event, player);;
        }
    }
}
