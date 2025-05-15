package com.elly.athena.entity.spell;

import com.elly.athena.item.Item_Register;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class MagicBall extends ThrowableItemProjectile {
    private static final float MIN_CAMERA_DISTANCE_SQUARED = 12.25F;
    private static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK = SynchedEntityData.defineId(MagicBall.class, EntityDataSerializers.ITEM_STACK);

    public MagicBall(EntityType<? extends ThrowableItemProjectile> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }

    public MagicBall(EntityType<? extends ThrowableItemProjectile> entityType, LivingEntity owner, Level level, ItemStack item) {
        super(entityType, owner, level, item);
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return Item_Register.RegisterDict.get("entity_magicball").get();
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
    }
}
