package com.elly.athena.system;

import com.elly.athena.Athena;
import com.elly.athena.data.Attribute_Register;
import com.elly.athena.data.types.ModContainer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;

public class BattleSystem {

    private static final ResourceLocation GLOBAL_MANA_MAX = ResourceLocation.fromNamespaceAndPath(Athena.MODID, "modifier.global.max_mana");

    public static void ApplyModAttribute(LivingEquipmentChangeEvent event, Player player){
        AttributeMap map = player.getAttributes();
        RemoveModAttribute(event.getFrom(), map);
        ModContainer container = new ModContainer(player);
        for(int i = 0; i < 12; i++){
            ItemStack iss = container.getItem(i);
            if(iss.isEmpty() || iss.isBroken()) continue;
            iss.getAttributeModifiers().modifiers().forEach(entry -> {
                AttributeInstance attributeinstance = map.getInstance(entry.attribute());
                if (attributeinstance != null) {
                    attributeinstance.removeModifier(entry.modifier().id());
                    attributeinstance.addTransientModifier(entry.modifier());
                }
            });
        }
    }

    private static void RemoveModAttribute(ItemStack target, AttributeMap map){
        if(target.isEmpty()) return;
        target.getAttributeModifiers().modifiers().forEach(entry -> {
            AttributeInstance attributeinstance = map.getInstance(entry.attribute());
            if (attributeinstance != null) {
                attributeinstance.removeModifier(entry.modifier().id());
            }
        });
    }
}

