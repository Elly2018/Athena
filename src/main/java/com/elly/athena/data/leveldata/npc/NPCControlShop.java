package com.elly.athena.data.leveldata.npc;

import com.elly.athena.data.interfaceType.leveldata.INPCRegister;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

public class NPCControlShop implements INPCRegister.INPCControlShop {
    private final NPCControl control;

    public NPCControlShop(NPCControl control) {
        this.control = control;
    }

    @Override
    public void SetCanSell(boolean value) {

    }

    @Override
    public boolean GetCanSell() {
        return false;
    }

    @Override
    public void AddItem(INPCRegister.INPCControlShopContent item) {

    }

    @Override
    public INPCRegister.INPCControlShopContent GetItem(int index) {
        return null;
    }

    @Override
    public boolean Remove(int index) {
        return false;
    }

    @Override
    public int Size() {
        return 0;
    }

    @Override
    public void OpenShop(Player player) {

    }

    @Override
    public void Sell(Player player, ItemStack target) {

    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
        return null;
    }

    @Override
    public void deserializeNBT(HolderLookup.@NotNull Provider provider, @NotNull CompoundTag compoundTag) {

    }
}
