package com.elly.rpg.item.potion;

import com.elly.rpg.RPG;
import com.elly.rpg.capability.CapabilitySystem;
import com.elly.rpg.capability.capability.IMana;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class RPGPotion_Base extends Item {
    public RPGPotion_Base(Properties p_41383_) {
        super(p_41383_);
    }

    public void PlayPotionSound(Player player) {
        player.playSound(SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("rpg", "potion")));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        boolean pass = false;
        float h = AddHealth(player);
        int m = AddMana(player);
        IMana target = player.getCapability(CapabilitySystem.MANA);

        if (h > 0 && player.getHealth() < player.getMaxHealth() && hand == InteractionHand.MAIN_HAND){
            pass = true;
            player.setHealth(Math.min(player.getHealth() + h, player.getMaxHealth()));
        }
        if (m > 0 && target.getMana() < target.getManaMaximum() && hand == InteractionHand.MAIN_HAND) {
            pass = true;
            target.setMana(Math.min(target.getMana() + m, target.getManaMaximum()));
        }

        if(!pass) return InteractionResult.FAIL;

        ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (stack.getCount() > 1) {
            stack.setCount(stack.getCount() - 1);
        } else {
            player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
        }

        PlayPotionSound(player);
        return InteractionResult.SUCCESS;
    }

    private void _ChangeHeal(){

    }

    public float AddHealth(Player player){ return 0; }
    public int AddMana(Player player){ return 0; }
}
