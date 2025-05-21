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
import org.jetbrains.annotations.Nullable;

public class AdvancedArrow extends Arrow {
    int tick;
    ItemStack pickup = null;

    public AdvancedArrow(EntityType<? extends Arrow> entityType, Level level) {
        super(entityType, level);
        this.pickup = ItemStack.EMPTY;
    }

    public AdvancedArrow(Level level, double x, double y, double z, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(level, x, y, z, pickupItemStack, firedFromWeapon);
        this.pickup = pickupItemStack;
    }

    public AdvancedArrow(Level level, LivingEntity owner, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(level, owner, pickupItemStack, firedFromWeapon);
        this.pickup = pickupItemStack;
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() {
        return pickup;
    }
}
