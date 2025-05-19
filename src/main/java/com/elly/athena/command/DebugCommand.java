package com.elly.athena.command;

import com.elly.athena.command.types.DebugType;
import com.elly.athena.data.Attribute_Register;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.*;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DebugCommand {
    public static int Debug_0(CommandContext<CommandSourceStack> command) {
        DebugType type = command.getArgument("type", DebugType.class);
        switch (type){
            case attribute -> {
                PrintAttributeList(command);
                break;
            }
        }
        return Command.SINGLE_SUCCESS;
    }

    private static void PrintAttributeList(CommandContext<CommandSourceStack> command){
        Player player = command.getSource().getPlayer();
        if(player == null) return;
        AttributeMap map = player.getAttributes();
        player.displayClientMessage(Component.literal(String.format("Player %s Status:", player.getName().getString())), false);
        for(AttributeInstance target: map.getSyncableAttributes()){
            String name = target.getAttribute().getRegisteredName();
            String value = String.valueOf(target.getValue());
            player.displayClientMessage(Component.literal(String.format("\t%s: %s", name, value)), false);
        }
        player.displayClientMessage(Component.literal("-*-- MOD --*-"), false);
        PrintModAttribute(Attribute_Register.LEVEL, map, player);
        PrintModAttribute(Attribute_Register.MANA, map, player);
        PrintModAttribute(Attribute_Register.MANA_MAX, map, player);
        PrintModAttribute(Attribute_Register.DAMAGE_MAX, map, player);
        PrintModAttribute(Attribute_Register.MAGIC_ATTACK, map, player);
        PrintModAttribute(Attribute_Register.MAGIC_ATTACK_MAX, map, player);
        PrintModAttribute(Attribute_Register.MAGIC_ARMOR, map, player);
        PrintModAttribute(Attribute_Register.DODGE, map, player);
        PrintModAttribute(Attribute_Register.ACCURACY, map, player);
    }

    private static void PrintModAttribute(DeferredHolder<Attribute, RangedAttribute> target, AttributeMap map, Player player){
        AttributeInstance instance = map.getInstance(target);
        String name = instance.getAttribute().getRegisteredName();
        String value = String.valueOf(instance.getValue());
        player.displayClientMessage(Component.literal(String.format("\t%s: %s", name, value)), false);
    }
}
