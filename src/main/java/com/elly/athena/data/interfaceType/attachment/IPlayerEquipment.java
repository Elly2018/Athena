package com.elly.athena.data.interfaceType.attachment;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;

public interface IPlayerEquipment {
    NonNullList<ItemStack> getList();
    ItemStack getSlot(int index);
    void setSlot(int index, ItemStack value);

    ItemStack getMain();
    ItemStack getSecondary();
    ItemStack getRing0();
    ItemStack getRing1();
    ItemStack getRing2();
    ItemStack getRing3();
    ItemStack getCape();
    ItemStack getBelt();
    ItemStack getFaceWear();
    ItemStack getNecklace();
    ItemStack getGlove();
    ItemStack getOrb();

    void setMain(ItemStack target);
    void setSecondary(ItemStack target);
    void setRing0(ItemStack target);
    void setRing1(ItemStack target);
    void setRing2(ItemStack target);
    void setRing3(ItemStack target);
    void setCape(ItemStack target);
    void setBelt(ItemStack target);
    void setFaceWear(ItemStack target);
    void setNecklace(ItemStack target);
    void setGlove(ItemStack target);
    void setOrb(ItemStack target);

    boolean hasMain();
    boolean hasSecondary();
    boolean hasRing0();
    boolean hasRing1();
    boolean hasRing2();
    boolean hasRing3();
    boolean hasCape();
    boolean hasBelt();
    boolean hasFaceWear();
    boolean hasNecklace();
    boolean hasGlove();
    boolean hasOrb();
}
