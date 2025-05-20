package com.elly.athena.entity.spell;

import com.elly.athena.entity.Entity_Register;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class AdvancedArrow extends Arrow {
    int tick;
    Item pickup = null;

    public AdvancedArrow(EntityType<? extends Arrow> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() {
        return pickup == null ? ItemStack.EMPTY : new ItemStack(pickup);
    }
}
