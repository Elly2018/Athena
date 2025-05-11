package com.elly.athena.data.leveldata;

import com.elly.athena.data.interfaceType.leveldata.INPCRegister;
import com.elly.athena.data.leveldata.npc.NPCControl;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class NPCRegister extends SavedData implements INPCRegister {
    public static NPCRegister create() {
        return new NPCRegister();
    }

    public static NPCRegister load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        NPCRegister data = NPCRegister.create();
        return data;
    }

    private final HashMap<String, NPCControl> NPCs = new HashMap<>();

    @Override
    public boolean CreateNPC(String UUID, LivingEntity target) {
        if(NPCs.containsKey(UUID)) return false;
        NPCControl n = new NPCControl(target);
        NPCs.put("UUID", n);
        return true;
    }

    @Override
    @Nullable
    public INPCControl GetNPC(String UUID) {
        if(!NPCs.containsKey(UUID)) return null;
        return NPCs.get(UUID);
    }

    @Override
    public int GetNPCSize() {
        return NPCs.size();
    }

    @Override
    public CompoundTag save(@NotNull CompoundTag compoundTag, HolderLookup.@NotNull Provider provider) {
        return compoundTag;
    }
}
