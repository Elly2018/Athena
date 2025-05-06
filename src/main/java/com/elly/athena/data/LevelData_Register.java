package com.elly.athena.data;

import com.elly.athena.data.leveldata.TeleportPoint;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public class LevelData_Register {
    public static TeleportPoint GetTeleportData(ServerLevel level){
        return level.getDataStorage().computeIfAbsent(new SavedData.Factory<TeleportPoint>(
                TeleportPoint::create,
                TeleportPoint::load
        ), "teleport");
    }
    public static void SetTeleportData(ServerLevel level, TeleportPoint data){
        level.getDataStorage().set("teleport", data);
    }
}
