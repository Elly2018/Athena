package com.elly.athena.item.potion;

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

public class HP_Potion_Large implements Item_Register.ItemRegisterData {

    static class HP_Potion_Large_Item extends RPGPotion_Base {
        public HP_Potion_Large_Item(Properties p_41383_) {
            super(p_41383_);
        }

        @Override
        public float AddHealth(Player player) {
            return 50.0F;
        }

        @Override
        public void appendHoverText(ItemStack p_41421_, TooltipContext ctx, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(p_41421_, ctx, tooltip, flag);
            tooltip.add(Component.literal("This will heal you with 50 health point"));
        }

        @Override
        public Optional<TooltipComponent> getTooltipImage(ItemStack p_150902_) {
            return super.getTooltipImage(p_150902_);
        }
    }

    @Override
    public String get_key() {
        return "hp_potion_large";
    }

    @Override
    public Item.Properties get_behaviour() {
        List<ItemAttributeModifiers.Entry> modifiers = new ArrayList<>();
        return new Item.Properties()
                .useCooldown(0);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new HP_Potion_Large_Item(props);
    }
}