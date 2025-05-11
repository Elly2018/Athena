package com.elly.athena.item.potion;

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

public class Elixir implements Item_Register.ItemRegisterData {

    static class Elixir_Item extends RPGPotion_Base {
        public Elixir_Item(Properties p_41383_) {
            super(p_41383_);
        }

        @Override
        public float AddHealth(Player player) {
            return player.getMaxHealth() / 2F;
        }

        @Override
        public int AddMana(Player player) {
            IPlayerStatus o_target = player.getData(Attachment_Register.PLAYER_STATUS);
            if(o_target == null) return 0;
            return o_target.getManaMaximum() / 2;
        }

        @Override
        public void appendHoverText(ItemStack p_41421_, TooltipContext ctx, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(p_41421_, ctx, tooltip, flag);
            tooltip.add(Component.literal("This will give you"));
            tooltip.add(Component.literal("50% health point"));
            tooltip.add(Component.literal("50% mana point"));
        }

        @Override
        public Optional<TooltipComponent> getTooltipImage(ItemStack p_150902_) {
            return super.getTooltipImage(p_150902_);
        }
    }

    @Override
    public String get_key() {
        return "elixir";
    }

    @Override
    public Item.Properties get_behaviour() {
        List<ItemAttributeModifiers.Entry> modifiers = new ArrayList<>();
        return new Item.Properties()
                .useCooldown(0);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new Elixir_Item(props);
    }
}