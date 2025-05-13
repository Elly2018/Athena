package com.elly.athena.data.leveldata;

import com.elly.athena.data.interfaceType.leveldata.ITeleportPoint;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class TeleportPoint extends SavedData implements ITeleportPoint {

    public static class PointData {
        public final Vec3 Position;

        public PointData(Vec3 position) {
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
            String name = buffer.getString("name");
            double x = buffer.getDouble("x");
            double y = buffer.getDouble("y");
            double z = buffer.getDouble("z");
            data.Points.put(name, new PointData(new Vec3(x, y, z)));
        }
        return data;
    }

    private final HashMap<String, PointData> Points = new HashMap<String, PointData>();

    public boolean AddPoint(String name, Vec3 pos){
        if(Exist(name)) return false;
        Points.put(name, new PointData(pos));
        return true;
    }

    @Override
    public void RemovePoint(String name) {
        if(!Exist(name)) return;
        Points.remove(name);
    }

    @Override
    public boolean Exist(String name) {
        return Points.containsKey(name);
    }

    @Override
    @Nullable
    public Vec3 GetPoint(String name) {
        if(!Exist(name)) return null;
        return Points.get(name).Position;
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag compoundTag, HolderLookup.@NotNull Provider provider) {
        ListTag list = new ListTag();

        for(var key: Points.keySet()){
            PointData pd = Points.get(key);

            CompoundTag buffer = new CompoundTag();
            buffer.putString("name", key);
            buffer.putDouble("x", pd.Position.x);
            buffer.putDouble("y", pd.Position.x);
            buffer.putDouble("z", pd.Position.x);
            list.add(buffer);
        }

        compoundTag.put("list", list);
        compoundTag.putFloat("size", Points.size());
        return compoundTag;
    }
}
