package com.elly.athena.command;

import com.elly.athena.command.types.DebugType;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;

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
        AttributeMap attris = player.getAttributes();
        player.displayClientMessage(Component.literal(String.format("Player %s Status:", player.getName().getString())), false);
        for(AttributeInstance target: attris.getSyncableAttributes()){
            String name = target.getAttribute().getRegisteredName();
            String value = String.valueOf(target.getValue());
            player.displayClientMessage(Component.literal(String.format("\t%s: %s", name, value)), false);
        }
    }
}
