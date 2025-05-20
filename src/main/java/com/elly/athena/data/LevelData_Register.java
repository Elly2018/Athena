package com.elly.athena.data;

import com.elly.athena.Athena;
import com.elly.athena.event.ServerHandler;
import com.elly.athena.data.leveldata.NPCRegister;
import com.elly.athena.data.leveldata.PlayerParty;
import com.elly.athena.data.leveldata.QuestRegister;
import com.elly.athena.data.leveldata.TeleportPoint;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class LevelData_Register {

    public static HashMap<String, SavedData> Datas = new HashMap<>();

    private static<T extends SavedData> T GetData(Class<T> instance, ServerLevel level, String key) {
        Method _create = null;
        Method _load = null;
        try {
            _create = instance.getMethod("create");
            _load = instance.getMethod("load", CompoundTag.class, HolderLookup.Provider.class);
        } catch (NoSuchMethodException e) {
            Athena.LOGGER.error("Level Register Casting create and load failed");
            throw new RuntimeException(e);
        }
        return level.getDataStorage().computeIfAbsent(new SavedData.Factory<T>(
                GetSave(_create),
                GetLoad(_load)
        ), key);
    }
    private static<T extends SavedData> Supplier<T> GetSave(Method t){
        return new Supplier<T>() {
            @Override
            public T get() {
                try {
                    return (T) t.invoke(null);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
    private static<T extends SavedData> BiFunction<CompoundTag, HolderLookup.Provider, T> GetLoad(Method t){
        return new BiFunction<CompoundTag, HolderLookup.Provider, T>() {
            @Override
            public T apply(CompoundTag compoundTag, HolderLookup.Provider provider) {
                try {
                    return (T) t.invoke(compoundTag, provider);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
    private static<T extends SavedData> void SetData(ServerLevel level, T data, String key){
        level.getDataStorage().set(key, data);
    }
    public static ServerLevel GetOverworld(MinecraftServer server){
        return server.getLevel(Level.OVERWORLD);
    }

    public static void CleanEvent(){
        Datas.clear();
    }

    public static void LoadEvent() {
        if(ServerHandler.m_Server == null) return;
        ServerLevel sl = GetOverworld(ServerHandler.m_Server);
        Datas.put("tp", GetData(TeleportPoint.class, sl, "tp"));
        Datas.put("party", GetData(PlayerParty.class, sl, "party"));
        Datas.put("npc", GetData(NPCRegister.class, sl, "npc"));
        Datas.put("quest", GetData(QuestRegister.class, sl, "quest"));
    }
}
