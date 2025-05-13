package com.elly.athena.data.types;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EquipmentSlot;

public enum ModEquipmentSlot implements StringRepresentable {
    MAIN(EquipmentSlot.Type.HAND, 0, "main"),
    SECONDARY(EquipmentSlot.Type.HAND, 1, "secondary"),
    RING0(EquipmentSlot.Type.HUMANOID_ARMOR, 2, "ring0"),
    RING1(EquipmentSlot.Type.HUMANOID_ARMOR, 3, "ring1"),
    RING2(EquipmentSlot.Type.HUMANOID_ARMOR, 4, "ring2"),
    RING3(EquipmentSlot.Type.HUMANOID_ARMOR, 5, "ring3"),
    CAPE(EquipmentSlot.Type.HUMANOID_ARMOR, 6, "cape"),
    BELT(EquipmentSlot.Type.HUMANOID_ARMOR, 7, "belt"),
    FACE(EquipmentSlot.Type.HUMANOID_ARMOR, 8, "face"),
    NECK(EquipmentSlot.Type.HUMANOID_ARMOR, 9, "neck"),
    GLOVE(EquipmentSlot.Type.HUMANOID_ARMOR, 10, "glove"),
    ORB(EquipmentSlot.Type.HUMANOID_ARMOR, 11, "orb");

    public final EquipmentSlot.Type type;
    public final int index;
    public final String name;

    private ModEquipmentSlot(EquipmentSlot.Type type, int index, String name){
        this.type = type;
        this.index = index;
        this.name = name;
    }

    public static boolean checkEquipable(int menuSlot, int equipState){
        if(menuSlot >= 2 && menuSlot <= 5 && equipState >= 2 && equipState <= 5) return true;
        else return menuSlot == equipState;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
