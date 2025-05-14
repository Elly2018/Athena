package com.elly.athena.system;

import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.LevelData_Register;
import com.elly.athena.data.interfaceType.attachment.IPlayerQuest;
import com.elly.athena.data.leveldata.QuestRegister;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

import java.util.Collection;
import java.util.UUID;

public class QuestSystem {

    public static void registerQuest(MinecraftServer server, Collection<Player> players){
        ServerLevel level = LevelData_Register.GetOverworld(server);
        QuestRegister qr = (QuestRegister) LevelData_Register.Datas.get("quest");
        UUID uuid = qr.GetQuestUUID(0);
        for(Player player: players){
            IPlayerQuest ipq = player.getData(Attachment_Register.PLAYER_QUEST);
            if(ipq.GetRecord(uuid) == null){
                ipq.AddRecord(uuid);
            }
        }

    }
}
