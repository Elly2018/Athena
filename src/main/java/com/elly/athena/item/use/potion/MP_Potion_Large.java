package com.elly.athena.item.use.potion;

import com.elly.athena.item.Item_Register;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;
import java.util.Optional;


public class MP_Potion_Large implements Item_Register.ItemRegisterData {

    static class MP_Potion_Large_Item extends RPGPotion_Base {
        public MP_Potion_Large_Item(Properties p_41383_) {
            super(p_41383_);
        }

        @Override
        public int AddMana(Player player) {
            return 50;
        }

        @Override
        public void appendHoverText(ItemStack p_41421_, TooltipContext ctx, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(p_41421_, ctx, tooltip, flag);
            tooltip.add(Component.translatable("item.athena.mp_potion_large.text"));
        }

        @Override
        public Optional<TooltipComponent> getTooltipImage(ItemStack p_150902_) {
            return super.getTooltipImage(p_150902_);
        }
    }


    @Override
    public String get_key() {
        return "mp_potion_large";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties()
                .stacksTo(99);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new MP_Potion_Large_Item(props);
    }
}