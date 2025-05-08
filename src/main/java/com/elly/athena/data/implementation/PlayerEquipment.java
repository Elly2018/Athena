package com.elly.athena.data.implementation;

import com.elly.athena.data.interfaceType.IPlayerEquipment;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

public class PlayerEquipment implements IPlayerEquipment, INBTSerializable<CompoundTag> {

    private ItemStack Main = ItemStack.EMPTY;
    private ItemStack Secondary = ItemStack.EMPTY;
    private ItemStack Ring0 = ItemStack.EMPTY;
    private ItemStack Ring1 = ItemStack.EMPTY;
    private ItemStack Ring2 = ItemStack.EMPTY;
    private ItemStack Ring3 = ItemStack.EMPTY;
    private ItemStack Cape = ItemStack.EMPTY;
    private ItemStack Belt = ItemStack.EMPTY;
    private ItemStack FaceWear = ItemStack.EMPTY;
    private ItemStack Necklace = ItemStack.EMPTY;
    private ItemStack Glove = ItemStack.EMPTY;
    private ItemStack Orb = ItemStack.EMPTY;

    @Override
    public NonNullList<ItemStack> getList() {
        NonNullList<ItemStack> r = NonNullList.withSize(12, ItemStack.EMPTY);
        r.set(0, Main);
        r.set(1, Secondary);
        r.set(2, Ring0);
        r.set(3, Ring1);
        r.set(4, Ring2);
        r.set(5, Ring3);
        r.set(6, Cape);
        r.set(7, Belt);
        r.set(8, FaceWear);
        r.set(9, Necklace);
        r.set(10, Glove);
        r.set(11, Orb);
        return r;
    }
    @Override public ItemStack getMain() { return Main; }
    @Override public ItemStack getSecondary() { return Secondary; }
    @Override public ItemStack getRing0() { return Ring0; }
    @Override public ItemStack getRing1() { return Ring1; }
    @Override public ItemStack getRing2() { return Ring2; }
    @Override public ItemStack getRing3() { return Ring3; }
    @Override public ItemStack getCape() { return Cape; }
    @Override public ItemStack getBelt() { return Belt; }
    @Override public ItemStack getFaceWear() { return FaceWear; }
    @Override public ItemStack getNecklace() { return Necklace; }
    @Override public ItemStack getGlove() { return Glove; }
    @Override public ItemStack getOrb() { return Orb; }

    @Override public void setMain(ItemStack value) { Main = value; }
    @Override public void setSecondary(ItemStack value) { Secondary = value; }
    @Override public void setRing0(ItemStack value) { Ring0 = value; }
    @Override public void setRing1(ItemStack value) { Ring1 = value; }
    @Override public void setRing2(ItemStack value) { Ring2 = value; }
    @Override public void setRing3(ItemStack value) { Ring3 = value; }
    @Override public void setCape(ItemStack value) { Cape = value; }
    @Override public void setBelt(ItemStack value) { Belt = value; }
    @Override public void setFaceWear(ItemStack value) { FaceWear = value; }
    @Override public void setNecklace(ItemStack value) { Necklace = value; }
    @Override public void setGlove(ItemStack value) { Glove = value; }
    @Override public void setOrb(ItemStack value) { Orb = value; }

    @Override public boolean hasMain() { return Main != null; }
    @Override public boolean hasSecondary() { return Secondary != null; }
    @Override public boolean hasRing0() { return Ring0 != null; }
    @Override public boolean hasRing1() { return Ring1 != null; }
    @Override public boolean hasRing2() { return Ring2 != null; }
    @Override public boolean hasRing3() { return Ring3 != null; }
    @Override public boolean hasCape() { return Cape != null; }
    @Override public boolean hasBelt() { return Belt != null; }
    @Override public boolean hasFaceWear() { return FaceWear != null; }
    @Override public boolean hasNecklace() { return Necklace != null; }
    @Override public boolean hasGlove() { return Glove != null; }
    @Override public boolean hasOrb() { return Orb != null; }

    @Override
    public ItemStack getSlot(int index) {
        if(index == 0) return Main;
        else if(index == 1) return Secondary;
        else if(index == 2) return Ring0;
        else if(index == 3) return Ring1;
        else if(index == 4) return Ring2;
        else if(index == 5) return Ring3;
        else if(index == 6) return Cape;
        else if(index == 7) return Belt;
        else if(index == 8) return FaceWear;
        else if(index == 9) return Necklace;
        else if(index == 10) return Glove;
        else if(index == 11) return Orb;
        else return ItemStack.EMPTY;
    }

    @Override
    public void setSlot(int index, ItemStack value) {
        if(index == 0) Main = value;
        else if(index == 1) Secondary = value;
        else if(index == 2) Ring0 = value;
        else if(index == 3) Ring1 = value;
        else if(index == 4) Ring2 = value;
        else if(index == 5) Ring3 = value;
        else if(index == 6) Cape = value;
        else if(index == 7) Belt = value;
        else if(index == 8) FaceWear = value;
        else if(index == 9) Necklace = value;
        else if(index == 10) Glove = value;
        else if(index == 11) Orb = value;
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        if(hasMain()) nbt.put("Main", AddTag(provider, Main));
        if(hasSecondary()) nbt.put("Secondary", AddTag(provider, Secondary));
        if(hasRing0()) nbt.put("Ring0", AddTag(provider, Ring0));
        if(hasRing1()) nbt.put("Ring0", AddTag(provider, Ring0));
        if(hasRing2()) nbt.put("Ring0", AddTag(provider, Ring0));
        if(hasRing3()) nbt.put("Ring0", AddTag(provider, Ring0));
        if(hasCape()) nbt.put("Cape", AddTag(provider, Cape));
        if(hasBelt()) nbt.put("Belt", AddTag(provider, Belt));
        if(hasFaceWear()) nbt.put("FaceWear", AddTag(provider, FaceWear));
        if(hasNecklace()) nbt.put("Necklace", AddTag(provider, Necklace));
        if(hasGlove()) nbt.put("Glove", AddTag(provider, Glove));
        if(hasOrb()) nbt.put("Orb", AddTag(provider, Orb));
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag compoundTag) {
        if(compoundTag.contains("Main")) { Main = ItemStack.parseOptional(provider, compoundTag.getCompound("Main")); }
        if(compoundTag.contains("Secondary")) { Secondary = ItemStack.parseOptional(provider, compoundTag.getCompound("Secondary")); }
        if(compoundTag.contains("Ring0")) { Ring0 = ItemStack.parseOptional(provider, compoundTag.getCompound("Ring0")); }
        if(compoundTag.contains("Ring0")) { Ring1 = ItemStack.parseOptional(provider, compoundTag.getCompound("Ring0")); }
        if(compoundTag.contains("Ring0")) { Ring2 = ItemStack.parseOptional(provider, compoundTag.getCompound("Ring0")); }
        if(compoundTag.contains("Ring0")) { Ring3 = ItemStack.parseOptional(provider, compoundTag.getCompound("Ring0")); }
        if(compoundTag.contains("Cape")) { Cape = ItemStack.parseOptional(provider, compoundTag.getCompound("Cape")); }
        if(compoundTag.contains("Belt")) { Belt = ItemStack.parseOptional(provider, compoundTag.getCompound("Belt")); }
        if(compoundTag.contains("FaceWear")) { FaceWear = ItemStack.parseOptional(provider, compoundTag.getCompound("FaceWear")); }
        if(compoundTag.contains("Necklace")) { Necklace = ItemStack.parseOptional(provider, compoundTag.getCompound("Necklace")); }
        if(compoundTag.contains("Glove")) { Glove = ItemStack.parseOptional(provider, compoundTag.getCompound("Glove")); }
        if(compoundTag.contains("Orb")) { Orb = ItemStack.parseOptional(provider, compoundTag.getCompound("Orb")); }
    }

    private Tag AddTag(HolderLookup.Provider provider, ItemStack t){
        CompoundTag b = new CompoundTag();
        if(!t.isEmpty()){
            t.save(provider, b);
        }
        return b;
    }
}
