package com.elly.athena.data.leveldata;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

public class PlayerParty extends SavedData {

    public static class PartyData {
        public final ArrayList<String> Players;

        public PartyData(ArrayList<String> players) {
            Players = players;
        }
    }

    public static PlayerParty create() {
        return new PlayerParty();
    }

    public static PlayerParty load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        PlayerParty data = PlayerParty.create();
        // Load saved data
        int size = tag.getInt("size");
        ListTag list = tag.getList("list", 10);
        for(int i = 0; i < size; i++){
            CompoundTag buffer = list.getCompound(i);
            int id = buffer.getInt("id");
            int p_size = buffer.getInt("p_size");
            ArrayList<String> p = new ArrayList<>();
            for(int j = 0; j < p_size; j++){
                p.add(buffer.getString(String.valueOf(j)));
            }
            data.Party.put(id, new PartyData(p));
        }
        return data;
    }

    private final HashMap<Integer, PartyData> Party = new HashMap<Integer, PartyData>();

    public boolean IsPlayerInParty(String player_uuid){
        return GetPartyId(player_uuid) != -1;
    }

    public int GetPartyId(String player_uuid){
        for(var party: Party.entrySet()){
            for(var player: party.getValue().Players){
                if(Objects.equals(player, player_uuid)) return party.getKey();
            }
        }
        return -1;
    }

    public void Create(String player_uuid){
        if(IsPlayerInParty(player_uuid)) return;
        ArrayList<String> b = new ArrayList<>(5);
        b.add(player_uuid);
        Party.put(GetId(), new PartyData(b));
    }

    public boolean Join(String player_uuid, int PartyId){
        if(!Party.containsKey(PartyId)) return false;
        PartyData pd = Party.get(PartyId);
        if(pd.Players.size() >= 5) return false;
        pd.Players.add(player_uuid);
        return true;
    }

    public void Leave(String player_uuid){
        int id = GetPartyId(player_uuid);
        if(id == -1) return;
        PartyData pd = Party.get(id);
        pd.Players.removeIf(x -> x.equals(player_uuid));
    }

    public Collection<String> GetMember(int PartyId){
        if(!Party.containsKey(PartyId)) return null;
        PartyData pd = Party.get(PartyId);
        return pd.Players;
    }

    private int GetId(){
        int k = 0;
        while (Party.containsKey(k)){
            k++;
        }
        return k;
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag compoundTag, HolderLookup.@NotNull Provider provider) {
        ListTag list = new ListTag();
        for(var key: Party.keySet()){
            PartyData pd = Party.get(key);

            CompoundTag buffer = new CompoundTag();
            buffer.putInt("id", key);
            buffer.putInt("size", pd.Players.size());
            for(int i = 0; i < pd.Players.size(); i++){
                buffer.putString(String.valueOf(i), pd.Players.get(i));
            }
            list.add(buffer);
        }
        compoundTag.put("list", list);
        compoundTag.putFloat("size", Party.size());
        return compoundTag;
    }
}
