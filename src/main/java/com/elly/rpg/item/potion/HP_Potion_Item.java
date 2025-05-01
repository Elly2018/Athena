package com.elly.rpg.item.potion;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;

public class HP_Potion_Item extends Item {
    public HP_Potion_Item(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        return super.onItemUseFirst(stack, context);
    }
}
