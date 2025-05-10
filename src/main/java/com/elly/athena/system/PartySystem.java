package com.elly.athena.system;

import com.elly.athena.data.leveldata.PlayerParty;
import org.jetbrains.annotations.Nullable;

public class PartySystem {

    static @Nullable PlayerParty.PartyData clientData;

    public static void onClientUpdate(@Nullable PlayerParty.PartyData data){
        clientData = data;
    }
}
