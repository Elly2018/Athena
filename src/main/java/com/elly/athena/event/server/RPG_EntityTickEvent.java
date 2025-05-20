package com.elly.athena.event.server;

import com.elly.athena.Athena;
import com.elly.athena.item.skill.RPGSkill_Base;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = Athena.MODID)
public class RPG_EntityTickEvent {

    @SubscribeEvent
    public static void entityTick(EntityTickEvent.Pre event){
        if (event.getEntity() instanceof ItemEntity){
            ItemEntity item = (ItemEntity) event.getEntity();
            if(item.getItem().getItem() instanceof RPGSkill_Base){
                item.remove(Entity.RemovalReason.KILLED);
            }
        }
    }
}
