package com.elly.rpg.item.potion;

import com.elly.rpg.item.Item_Register;
import com.elly.rpg.sound.Sound_Register;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HP_Potion implements Item_Register.ItemRegisterData {

    static class HP_Potion_Item extends RPGPotion_Base {
        public HP_Potion_Item(Properties p_41383_) {
            super(p_41383_);
        }

        @Override
        public InteractionResult use(Level level, Player player, InteractionHand hand) {
            if (player.getHealth() < player.getMaxHealth() && hand == InteractionHand.MAIN_HAND){
                player.setHealth(player.getHealth() + 10.0F);

                ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
                if(stack.getCount() > 1){
                    stack.setCount(stack.getCount() - 1);
                }else{
                    player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                }
                PlayPotionSound(player);
                return InteractionResult.SUCCESS;
            }else{
                return InteractionResult.FAIL;
            }
        }

        @Override
        public void appendHoverText(ItemStack p_41421_, TooltipContext ctx, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(p_41421_, ctx, tooltip, flag);
            tooltip.add(Component.literal("This will heal you with 10 health point"));
        }

        @Override
        public Optional<TooltipComponent> getTooltipImage(ItemStack p_150902_) {
            return super.getTooltipImage(p_150902_);
        }
    }

    @Override
    public String get_key() {
        return "hp_potion";
    }

    @Override
    public Item.Properties get_behaviour() {
        List<ItemAttributeModifiers.Entry> modifiers = new ArrayList<>();
        return new Item.Properties()
                .overrideDescription("HP Potion")
                .useCooldown(0);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new HP_Potion_Item(props);
    }
}