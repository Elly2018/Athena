package com.elly.athena.event.server;

import com.elly.athena.Athena;
import com.elly.athena.data.DataComponent_Register;
import com.elly.athena.item.entity.Entity_MagicBall;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.projectile.Snowball;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.Objects;

@EventBusSubscriber(modid = Athena.MODID)
public class RPG_LivingIncomingDamageEvent {

    @SubscribeEvent
    public static void onLivingIncomingDamageEvent(LivingIncomingDamageEvent event){
        DamageSource source = event.getSource();
        if(source.getEntity() instanceof Snowball spell){
            if(spell.getItem().getItem() instanceof Entity_MagicBall.Entity_MagicBall_Item magicball){
                int damage = Objects.requireNonNull(spell.getItem().get(DataComponents.DAMAGE));
                event.setAmount(damage);
            }
        }
    }
}
