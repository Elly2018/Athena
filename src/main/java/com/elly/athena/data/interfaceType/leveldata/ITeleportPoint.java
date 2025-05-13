package com.elly.athena.data.interfaceType.leveldata;

import net.minecraft.world.phys.Vec3;

public interface ITeleportPoint {
    boolean AddPoint(String name, Vec3 pos);
    void RemovePoint(String name);
    boolean Exist(String name);
    Vec3 GetPoint(String name);
}
