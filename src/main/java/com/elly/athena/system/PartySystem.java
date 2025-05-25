package com.elly.athena.system;

import com.elly.athena.data.LevelData_Register;
import com.elly.athena.data.leveldata.PlayerParty;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PartySystem {

    static @Nullable PlayerParty.PartyData clientData;

    public static void onClientUpdate(@Nullable PlayerParty.PartyData data){
        clientData = data;
    }

    public static Collection<Player> GetPartyMemberInRange(Player target, int range){
        ArrayList<Player> r = new ArrayList<>();
        if(target.level() instanceof ServerLevel level){
            PlayerParty pp = (PlayerParty) LevelData_Register.Datas.get("party");
            if(pp.IsPlayerInParty(target.getUUID().toString())){
                int id = pp.GetPartyId(target.getUUID().toString());
                Collection<String> uuids = pp.GetMember(id);
                for(String _uuid: uuids){
                    ServerPlayer spp = GetPlayerByUUID(level.players(), _uuid);
                    if(spp == null) continue;
                    double dist = spp.position().distanceTo(target.position());
                    if(dist <= range) r.add(spp);
                }
            }
        }
        return r;
    }

    private static ServerPlayer GetPlayerByUUID(List<ServerPlayer> players, String uuid){
        for(ServerPlayer p: players){
            if(p.getUUID().toString().equals(uuid)) return p;
        }
        return null;
    }
}
