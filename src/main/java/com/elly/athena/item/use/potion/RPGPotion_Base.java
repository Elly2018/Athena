package com.elly.athena.item.use.potion;

import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.Attribute_Register;
import com.elly.athena.data.implementation.PlayerStatus;
import com.elly.athena.item.use.RPGUse_Base;
import com.elly.athena.network.general.StatusPayload;
import com.elly.athena.sound.Sound_Register;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class RPGPotion_Base extends RPGUse_Base {
    public RPGPotion_Base(Properties properties) {
        super(properties);
    }

    public void PlayPotionSound(Player player) {
        player.playSound(Sound_Register.Potion.get());
    }

    @Override
    public InteractionResult use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        boolean pass = false;
        float h = AddHealth(player);
        int m = AddMana(player);
        PlayerStatus target = player.getData(Attachment_Register.PLAYER_STATUS);
        AttributeMap map = player.getAttributes();
        float MaxHP = (float) Objects.requireNonNull(map.getInstance(Attributes.MAX_HEALTH)).getValue();
        int MP = (int) Objects.requireNonNull(map.getInstance(Attribute_Register.MANA)).getValue();
        int MaxMP = (int) Objects.requireNonNull(map.getInstance(Attribute_Register.MANA_MAX)).getValue();

        if (h > 0 && player.getHealth() < MaxHP && hand == InteractionHand.MAIN_HAND){
            pass = true;
            player.setHealth(Math.min(player.getHealth() + h, MaxHP));
        }
        if (m > 0 && MP < MaxMP && hand == InteractionHand.MAIN_HAND) {
            pass = true;
            var instance = map.getInstance(Attribute_Register.MANA);
            assert instance != null;
            instance.setBaseValue(Math.min(MP + m, MaxMP));
        }

        if(!pass) return InteractionResult.FAIL;
        else{
            PacketDistributor.sendToServer(new StatusPayload.StatusData(target.serializeNBT(player.registryAccess())));
        }

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
