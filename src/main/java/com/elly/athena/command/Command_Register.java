package com.elly.athena.command;

import com.elly.athena.capability.Attachment_Register;
import com.elly.athena.capability.implementation.PlayerStatus;
import com.elly.athena.capability.interfaceType.IPlayerStatus;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.Objects;

public class Command_Register {
    enum Action {
        check,
        set,
        add
    }
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        var action = Commands.argument("action", StringArgumentType.string());
        action.then(Commands.literal(Action.check.name()));
        action.then(Commands.literal(Action.set.name()));
        action.then(Commands.literal(Action.add.name()));

        LiteralArgumentBuilder<CommandSourceStack> player_status = Commands.literal("rpg_ps")
                .requires(cs -> cs.hasPermission(4))
                .then(action
                    .then(Commands.argument("target", EntityArgument.player())
                            .executes(command -> _player_status(command))
                                .then(Commands.argument("value", IntegerArgumentType.integer())
                                    .executes(command -> _player_status(command)))));

        dispatcher.register(player_status);
    }

    private static int _player_status(CommandContext<CommandSourceStack> command){
        Player player = command.getSource().getPlayer();
        if(player == null) return -1;

        if(!player.hasData(Attachment_Register.PLAYER_STATUS)){
            player.setData(Attachment_Register.PLAYER_STATUS.get(), new PlayerStatus(0));
        }
        IPlayerStatus target = player.getData(Attachment_Register.PLAYER_STATUS);
        String action = command.getArgument("action", String.class);
        int value = 0;

        if(Objects.equals(action, Action.check.name())){
            player.displayClientMessage(Component.literal("Your mana is: %d / %d".formatted(target.getMana(), target.getManaMaximum())), true);
        }else{
            value = command.getArgument("value", Integer.class);
        }

        if (Objects.equals(action, Action.add.name())){
            target.addMana(value);
            player.displayClientMessage(Component.literal("Your mana now is: %d / %d".formatted(target.getMana(), target.getManaMaximum())), true);
        }
        else if (Objects.equals(action, Action.set.name())){
            target.setMana(value);
            player.displayClientMessage(Component.literal("Your mana now is: %d / %d".formatted(target.getMana(), target.getManaMaximum())), true);
        }
        return Command.SINGLE_SUCCESS;
    }
}
