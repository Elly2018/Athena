package com.elly.athena.data.interfaceType.attachment;

import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.UUID;

public interface IDamageRecord {
    void clearSource();
    void addPlayerSource(Player player, float value);
    HashMap<UUID, Float> getPlayerTable();
}
