package com.elly.athena.item.use.potion;

import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.attachment.IPlayerStatus;
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

public class Power_Elixir implements Item_Register.ItemRegisterData {

    static class Power_Elixir_Item extends RPGPotion_Base {
        public Power_Elixir_Item(Properties p_41383_) {
            super(p_41383_);
        }

        @Override
        public float AddHealth(Player player) {
            return player.getMaxHealth();
        }

        @Override
        public int AddMana(Player player) {
            IPlayerStatus o_target = player.getData(Attachment_Register.PLAYER_STATUS);
            if(o_target != null) return 0;
            return o_target.getManaMaximum();
        }

        @Override
        public void appendHoverText(ItemStack p_41421_, TooltipContext ctx, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(p_41421_, ctx, tooltip, flag);
            tooltip.add(Component.literal("This will give you"));
            tooltip.add(Component.literal("100% health point"));
            tooltip.add(Component.literal("100% mana point"));
        }

        @Override
        public Optional<TooltipComponent> getTooltipImage(ItemStack p_150902_) {
            return super.getTooltipImage(p_150902_);
        }
    }

    @Override
    public String get_key() {
        return "power_elixir";
    }

    @Override
    public Item.Properties get_behaviour() {
        return new Item.Properties()
                .stacksTo(99);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new Power_Elixir_Item(props);
    }
}