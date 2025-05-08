package com.elly.athena.data.types;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EquipmentSlot;

public enum ModEquipmentSlot implements StringRepresentable {
    RING0(EquipmentSlot.Type.HUMANOID_ARMOR, 0, 1, 1, "ring0"),
    RING1(EquipmentSlot.Type.HUMANOID_ARMOR, 1, 1, 1, "ring1"),
    RING2(EquipmentSlot.Type.HUMANOID_ARMOR, 2, 1, 1, "ring2"),
    RING3(EquipmentSlot.Type.HUMANOID_ARMOR, 3, 1, 1, "ring3");

    private final EquipmentSlot.Type type;
    private final int index;
    private final int countLimit;
    private final int id;
    private final String name;

    private ModEquipmentSlot(EquipmentSlot.Type type, int index, int countLimit, int id, String name){
        this.type = type;
        this.index = index;
        this.countLimit = countLimit;
        this.id = id;
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
