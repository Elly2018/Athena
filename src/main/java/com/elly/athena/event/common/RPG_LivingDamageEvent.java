package com.elly.athena.event.common;

import com.elly.athena.Athena;
import com.elly.athena.Config;
import com.elly.athena.data.Attribute_Register;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
            double damage = player.getAttribute(Attributes.ATTACK_DAMAGE).getValue();
            double max_damage = player.getAttribute(Attribute_Register.DAMAGE_MAX).getValue();
            int d = player.getRandom().nextIntBetweenInclusive((int)damage, Math.max((int)max_damage + (int)damage, (int)damage + 1));
            event.setNewDamage(d);
        }
    }
}
