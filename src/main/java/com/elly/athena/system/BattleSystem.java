package com.elly.athena.system;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.attachment.IPlayerStatus;
import com.elly.athena.data.types.ModContainer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import org.jetbrains.annotations.NotNull;

public class BattleSystem {
    public static void ApplyEquipmentChange(@NotNull LivingEquipmentChangeEvent event, @NotNull Player player){
        AttributeMap map = player.getAttributes();
        RemoveModAttribute(event.getFrom(), map);
        ssApplyModAttribute(map, player);
    }

    public static void ApplyChange(@NotNull Player player){
        AttributeMap map = player.getAttributes();
        ssApplyModAttribute(map, player);
    }

    private static void ssApplyModAttribute(AttributeMap map, Player player){
        Athena.LOGGER.debug("Battle System: ApplyModAttribute");
        ModContainer container = new ModContainer(player);
        IPlayerStatus ps = player.getData(Attachment_Register.PLAYER_STATUS);
        if(ps.getMode() == 1){
            RemoveHandModAttribute(player, map);
            RemoveOffHandModAttribute(player, map);
        }
        for(int i = 0; i < 12; i++){
            if(ps.getMode() == 0 && i < 2) continue;
            ItemStack iss = container.getItem(i);
            if(iss.isEmpty() || iss.isBroken()) continue;
            iss.getAttributeModifiers().modifiers().forEach(entry -> {
                AttributeInstance attributeinstance = map.getInstance(entry.attribute());
                if (attributeinstance != null) {
                    attributeinstance.addOrUpdateTransientModifier(entry.modifier());
                }
            });
        }
    }

    private static void RemoveHandModAttribute(@NotNull Player player, AttributeMap map){
        ItemStack iss = player.getInventory().getSelected();
        RemoveModAttribute(iss, map);
    }

    private static void RemoveOffHandModAttribute(@NotNull Player player, AttributeMap map){
        ItemStack iss = player.getInventory().getItem(45);
        RemoveModAttribute(iss, map);
    }

    private static void RemoveModAttribute(@NotNull ItemStack target, AttributeMap map){
        if(target.isEmpty()) return;
        target.getAttributeModifiers().modifiers().forEach(entry -> {
            AttributeInstance attributeinstance = map.getInstance(entry.attribute());
            if (attributeinstance != null) {
                attributeinstance.removeModifier(entry.modifier().id());
            }
        });
    }
}

