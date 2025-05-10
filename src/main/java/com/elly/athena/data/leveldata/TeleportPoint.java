package com.elly.athena.data.leveldata;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class TeleportPoint extends SavedData {

    public static class PointData {
        public final String Name;
        public final Vec3 Position;

        public PointData(String name, Vec3 position) {
            Name = name;
            Position = position;
        }
    }

    public static TeleportPoint create() {
        return new TeleportPoint();
    }

    public static TeleportPoint load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        TeleportPoint data = TeleportPoint.create();
        // Load saved data
        int size = tag.getInt("size");
        ListTag list = tag.getList("list", 10);
        for(int i = 0; i < size; i++){
            CompoundTag buffer = list.getCompound(i);
            int id = buffer.getInt("id");
            String name = buffer.getString("name");
            double x = buffer.getDouble("x");
            double y = buffer.getDouble("y");
            double z = buffer.getDouble("z");
            data.Points.put(id, new PointData(name, new Vec3(x, y, z)));
        }
        return data;
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
    public @NotNull CompoundTag save(@NotNull CompoundTag compoundTag, HolderLookup.@NotNull Provider provider) {
        ListTag list = new ListTag();

        for(var key: Points.keySet()){
            PointData pd = Points.get(key);

            CompoundTag buffer = new CompoundTag();
            buffer.putInt("id", key);
            buffer.putString("name", pd.Name);
            buffer.putDouble("x", pd.Position.x);
            buffer.putDouble("y", pd.Position.x);
            buffer.putDouble("z", pd.Position.x);
            list.add(buffer);
        }

        compoundTag.put("list", list);
        compoundTag.putFloat("size", Points.size());
        return null;
    }
}
