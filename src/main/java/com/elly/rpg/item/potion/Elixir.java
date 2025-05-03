package com.elly.rpg.item.potion;

import com.elly.rpg.capability.CapabilitySystem;
import com.elly.rpg.capability.capability.IMana;
import com.elly.rpg.item.Item_Register;
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
            Optional<IMana> o_target = (Optional<IMana>) CapabilitySystem.GetDataFromPlayer(player, "mana");
            if(o_target.isEmpty()) return 0;
            return o_target.get().getManaMaximum() / 2;
        }

        @Override
        public void appendHoverText(ItemStack p_41421_, TooltipContext ctx, List<Component> tooltip, TooltipFlag flag) {
            super.appendHoverText(p_41421_, ctx, tooltip, flag);
            tooltip.add(Component.literal("This will heal you with 50% health point and 50% mana point"));
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
                .overrideDescription("Elixir")
                .useCooldown(0);
    }

    @Override
    public Item get_binding(Item.Properties props) {
        return new Elixir_Item(props);
    }
}