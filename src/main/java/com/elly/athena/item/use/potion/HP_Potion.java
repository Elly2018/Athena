package com.elly.athena.item.use.potion;

import com.elly.athena.item.Item_Register;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HP_Potion implements Item_Register.ItemRegisterData {

    static class HP_Potion_Item extends RPGPotion_Base {
        public HP_Potion_Item(Properties p_41383_) {
            super(p_41383_);
        }

        @Override
        public float AddHealth(Player player) {
            return 10.0F;
        }

        @Override
        public void appendHoverText(ItemStack p_41421_, TooltipContext ctx, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(p_41421_, ctx, tooltip, flag);
            tooltip.add(Component.translatable("item.athena.hp_potion.text"));
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
        return new Item.Properties()
                .stacksTo(99);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new HP_Potion_Item(props);
    }
}