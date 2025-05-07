package com.elly.athena.data.interfaceType;

import net.minecraft.world.entity.item.ItemEntity;

public interface IPlayerErquipment {
    ItemEntity getRing0();
    ItemEntity getRing1();
    ItemEntity getRing2();
    ItemEntity getRing3();

    void setRing0(ItemEntity target);
    void setRing1(ItemEntity target);
    void setRing2(ItemEntity target);
    void setRing3(ItemEntity target);
}
