package com.elly.athena.event.server;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.Attribute_Register;
import com.elly.athena.data.implementation.BattleHotbar;
import com.elly.athena.data.implementation.PlayerEquipment;
import com.elly.athena.data.implementation.PlayerStatus;
import com.elly.athena.item.use.RPGUse_Base;
import com.elly.athena.system.BattleSystem;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber(modid = Athena.MODID)
public class RPG_PlayerEvent {

    @SubscribeEvent
    public static void respawn(PlayerEvent.PlayerRespawnEvent event){
        BattleSystem.ApplyChange(event.getEntity());
        Player player = event.getEntity();
        Athena.LOGGER.debug(String.format("Player Respawn: %s", player.getDisplayName().getString()));
        player.setHealth(player.getMaxHealth());
        AttributeMap map = player.getAttributes();
        float maxMana = (float) map.getInstance(Attribute_Register.MANA_MAX).getValue();
        map.getInstance(Attribute_Register.MANA).setBaseValue(maxMana);
    }

    @SubscribeEvent
    public static void playerClone(PlayerEvent.Clone event){
        Athena.LOGGER.debug(String.format("Player Clone: %s %b", event.getEntity().getDisplayName().getString(), event.isWasDeath()));
        boolean keep = event.getEntity().getServer().getGameRules().getRule(GameRules.RULE_KEEPINVENTORY).get();
        if(keep){
            event.getEntity().setData(Attachment_Register.PLAYER_STATUS, event.getOriginal().getData(Attachment_Register.PLAYER_STATUS));
            event.getEntity().setData(Attachment_Register.PLAYER_SKILL, event.getOriginal().getData(Attachment_Register.PLAYER_SKILL));
            event.getEntity().setData(Attachment_Register.PLAYER_EQUIPMENT, event.getOriginal().getData(Attachment_Register.PLAYER_EQUIPMENT));
            event.getEntity().setData(Attachment_Register.PLAYER_QUEST, event.getOriginal().getData(Attachment_Register.PLAYER_QUEST));
            event.getEntity().setData(Attachment_Register.BATTLE_HOTBAR, event.getOriginal().getData(Attachment_Register.BATTLE_HOTBAR));
        }else{
            event.getEntity().setData(Attachment_Register.PLAYER_SKILL, event.getOriginal().getData(Attachment_Register.PLAYER_SKILL));
            event.getEntity().setData(Attachment_Register.PLAYER_QUEST, event.getOriginal().getData(Attachment_Register.PLAYER_QUEST));

            PlayerStatus ips = event.getOriginal().getData(Attachment_Register.PLAYER_STATUS);
            PlayerEquipment ipe = event.getOriginal().getData(Attachment_Register.PLAYER_EQUIPMENT);
            BattleHotbar ibh = event.getOriginal().getData(Attachment_Register.BATTLE_HOTBAR);

            int maxExp = ips.getExpMaximum(ips.getLevel());
            int currentExp = ips.getExp();
            ips.setExp(Math.max((int)(currentExp - maxExp * 0.2F), 0));
            if(ipe.hasMain()) event.getOriginal().drop(ipe.getMain(), true); ipe.setMain(ItemStack.EMPTY);
            if(ipe.hasSecondary()) event.getOriginal().drop(ipe.getSecondary(), true); ipe.setSecondary(ItemStack.EMPTY);
            if(ipe.hasRing0()) event.getOriginal().drop(ipe.getRing0(), true); ipe.setRing0(ItemStack.EMPTY);
            if(ipe.hasRing1()) event.getOriginal().drop(ipe.getRing1(), true); ipe.setRing1(ItemStack.EMPTY);
            if(ipe.hasRing2()) event.getOriginal().drop(ipe.getRing2(), true); ipe.setRing2(ItemStack.EMPTY);
            if(ipe.hasRing3()) event.getOriginal().drop(ipe.getRing3(), true); ipe.setRing3(ItemStack.EMPTY);
            if(ipe.hasCape()) event.getOriginal().drop(ipe.getCape(), true); ipe.setCape(ItemStack.EMPTY);
            if(ipe.hasBelt()) event.getOriginal().drop(ipe.getBelt(), true); ipe.setBelt(ItemStack.EMPTY);
            if(ipe.hasFaceWear()) event.getOriginal().drop(ipe.getFaceWear(), true); ipe.setFaceWear(ItemStack.EMPTY);
            if(ipe.hasNecklace()) event.getOriginal().drop(ipe.getNecklace(), true); ipe.setNecklace(ItemStack.EMPTY);
            if(ipe.hasGlove()) event.getOriginal().drop(ipe.getGlove(), true); ipe.setGlove(ItemStack.EMPTY);
            if(ipe.hasOrb()) event.getOriginal().drop(ipe.getOrb(), true); ipe.setOrb(ItemStack.EMPTY);

            for(int i = 0; i < 9; i++){
                if(ibh.getSlot(i).getItem() instanceof RPGUse_Base target){
                    event.getOriginal().drop(ibh.getSlot(i), true);
                    ibh.setSlot(i, ItemStack.EMPTY);
                }
            }

            event.getEntity().setData(Attachment_Register.PLAYER_STATUS, ips);
            event.getEntity().setData(Attachment_Register.PLAYER_EQUIPMENT, ipe);
            event.getEntity().setData(Attachment_Register.BATTLE_HOTBAR, ibh);
        }
    }
}
