package com.elly.athena.data.implementation;

import com.elly.athena.data.interfaceType.attachment.IDamageRecord;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

import java.util.HashMap;
import java.util.UUID;

public class DamageSource implements IDamageRecord, INBTSerializable<CompoundTag> {
    private final HashMap<UUID, Float> sourceDict;

    public DamageSource() {
        this.sourceDict = new HashMap<UUID, Float>();
    }

    @Override
    public void clearSource() {
        sourceDict.clear();
    }

    @Override
    public void addPlayerSource(Player player, float value) {
        if(sourceDict.containsKey(player.getUUID())){
            float b = sourceDict.get(player.getUUID());
            b += value;
            sourceDict.put(player.getUUID(), b);
        }else{
            sourceDict.put(player.getUUID(), value);
        }
    }

    @Override
    public HashMap<UUID, Float> getPlayerTable() {
        return this.sourceDict;
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        ListTag nbtTagList = new ListTag();

        for(var i: sourceDict.keySet()){
            CompoundTag elementTag = new CompoundTag();
            elementTag.putString("uuid", i.toString());
            elementTag.putFloat("value", sourceDict.get(i));
            nbtTagList.add(elementTag);
        }

        CompoundTag nbt = new CompoundTag();
        nbt.put("damage_source", nbtTagList);
        nbt.putInt("size", sourceDict.size());
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag compoundTag) {
        sourceDict.clear();
        ListTag nbtTagList = compoundTag.getList("damage_source", 10);
        int amount = compoundTag.getInt("size");
        for(int i = 0; i < amount; i++){
            CompoundTag elementTag = nbtTagList.getCompound(i);
            String uuid = elementTag.getString("uuid");
            float value = elementTag.getFloat("value");
            sourceDict.put(UUID.fromString(uuid), value);
        }
    }
}
