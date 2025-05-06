package com.elly.athena.data.leveldata;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;

public class TeleportPoint extends SavedData {

    public class PointData {
        public final String Name;
        public final Vec3 Position;

        public PointData(String name, Vec3 position) {
            Name = name;
            Position = position;
        }
    }

    public final HashMap<Integer, PointData> Points = new HashMap<Integer, PointData>();

    public void AddPoint(String name, Vec3 pos){
        int size = Points.size();
        int id = size;
        for(int i = 0; i < size; i++){
            if(!Points.containsKey(i)){
                id = i;
                break;
            }
        }

        Points.put(id, new PointData(name, pos));
    }

    @Override
    public CompoundTag save(CompoundTag compoundTag, HolderLookup.Provider provider) {
        ListTag list = new ListTag();

        for(var key: Points.keySet()){
            PointData pd = Points.get(key);

            CompoundTag elementTag = new CompoundTag();
            elementTag.putInt("id", key);
            elementTag.putString("name", pd.Name);
            elementTag.putDouble("x", pd.Position.x);
            elementTag.putDouble("y", pd.Position.x);
            elementTag.putDouble("z", pd.Position.x);
            list.add(elementTag);
        }

        compoundTag.putFloat("size", Points.size());
        return null;
    }
}
