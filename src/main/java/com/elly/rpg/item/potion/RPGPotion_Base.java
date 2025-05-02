package com.elly.rpg.item.potion;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

public class RPGPotion_Base extends Item {
    public RPGPotion_Base(Properties p_41383_) {
        super(p_41383_);
    }

    public void PlayPotionSound(Player player) {
        player.playSound(SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("rpg", "potion")));
    }
}
