package com.elly.athena.data.implementation;

import com.elly.athena.data.interfaceType.IDamage_Record;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

import java.util.HashMap;
import java.util.UUID;

public class Damage_Source implements IDamage_Record, INBTSerializable<CompoundTag> {
    private final HashMap<UUID, Float> sourceDict;

    public Damage_Source() {
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

        nbtTagList.add(IntTag.valueOf(sourceDict.size()));

        for(var i: sourceDict.keySet()){
            ListTag element = new ListTag();

            CompoundTag element_uuid = new CompoundTag();
            element_uuid.putString("uuid", i.toString());

            CompoundTag element_amount = new CompoundTag();
            element_amount.putFloat("value", sourceDict.get(i));

            element.add(element_uuid);
            element.add(element_amount);
            nbtTagList.add(element);
        }

        CompoundTag nbt = new CompoundTag();
        nbt.put("damage_source", nbtTagList);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag compoundTag) {
        sourceDict.clear();
        ListTag nbtTagList = compoundTag.getList("damage_source", 10);
        int amount = nbtTagList.getInt(0);
        for(int i = 0; i < amount; i++){
            ListTag element = nbtTagList.getList(i + 1);
            String uuid = element.getString(0);
            float value = element.getFloat(1);
            sourceDict.put(UUID.fromString(uuid), value);
        }
    }
}
