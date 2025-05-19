package com.elly.athena.entity.spell;

import com.elly.athena.entity.Entity_Register;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class AdvancedArrow extends AbstractArrow {
    int tick;
    Item pickup = null;

    public AdvancedArrow(EntityType<? extends AdvancedArrow> entityType, Level level) {
        super(entityType, level);
        init(ItemStack.EMPTY);
    }

    public AdvancedArrow(Level level, LivingEntity owner, ItemStack pickupItem, ItemStack item) {
        super(Entity_Register.ADVANCEDARROW, owner, level, pickupItem, item);
        init(pickupItem);
    }

    public AdvancedArrow(Level level, double x, double y, double z, ItemStack pickupItem, ItemStack item) {
        super(Entity_Register.ADVANCEDARROW, x, y, z, level, pickupItem, item);
        init(pickupItem);
    }

    private void init(ItemStack pickupItem){
        this.pickup = pickupItem.getItem();
        if(this.getOwner() instanceof Player player){

        }
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() {
        return pickup == null ? ItemStack.EMPTY : new ItemStack(pickup);
    }
}
