package com.elly.athena.gui.menu.slot;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.inventory.Slot;

import javax.annotation.Nullable;

public class ModArmorSlot extends Slot {
    private final LivingEntity owner;
    @Nullable
    private final ResourceLocation emptyIcon;

    public ModArmorSlot(Container container, int slot, int x, int y, LivingEntity owner, @Nullable ResourceLocation emptyIcon) {
        super(container, slot, x, y);
        this.owner = owner;
        this.emptyIcon = emptyIcon;
    }
}
