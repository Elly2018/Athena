package com.elly.athena.data.types;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EquipmentSlot;

public enum ModEquipmentSlot implements StringRepresentable {
    MAIN(EquipmentSlot.Type.HUMANOID_ARMOR, 0, 1, 1, "main"),
    SECONDARY(EquipmentSlot.Type.HUMANOID_ARMOR, 1, 1, 1, "secondary"),
    RING0(EquipmentSlot.Type.HUMANOID_ARMOR, 2, 1, 1, "ring0"),
    RING1(EquipmentSlot.Type.HUMANOID_ARMOR, 3, 1, 1, "ring1"),
    RING2(EquipmentSlot.Type.HUMANOID_ARMOR, 4, 1, 1, "ring2"),
    RING3(EquipmentSlot.Type.HUMANOID_ARMOR, 5, 1, 1, "ring3"),
    CAPE(EquipmentSlot.Type.HUMANOID_ARMOR, 6, 1, 1, "cape"),
    BELT(EquipmentSlot.Type.HUMANOID_ARMOR, 7, 1, 1, "belt"),
    FACE(EquipmentSlot.Type.HUMANOID_ARMOR, 8, 1, 1, "face"),
    NECK(EquipmentSlot.Type.HUMANOID_ARMOR, 9, 1, 1, "neck"),
    GLOVE(EquipmentSlot.Type.HUMANOID_ARMOR, 10, 1, 1, "glove"),
    ORB(EquipmentSlot.Type.HUMANOID_ARMOR, 11, 1, 1, "orb");

    public final EquipmentSlot.Type type;
    public final int index;
    public final int countLimit;
    public final int id;
    public final String name;

    private ModEquipmentSlot(EquipmentSlot.Type type, int index, int countLimit, int id, String name){
        this.type = type;
        this.index = index;
        this.countLimit = countLimit;
        this.id = id;
        this.name = name;
    }

    public static boolean checkEquipable(int menuSlot, int equipState){
        if(menuSlot >= 2 && menuSlot <= 5 && equipState == 2) return true;
        else return menuSlot == equipState;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
