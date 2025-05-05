package com.elly.athena.command;

import com.elly.athena.Athena;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.interfaceType.IPlayerStatus;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.server.command.EnumArgument;

import java.util.Objects;

public class Command_Register {
    enum Action {
        check,
        set,
        add;
    }
    enum DataType {
        level,
        mana,
        exp,
        max_hp,
        str,
        dex,
        _int,
        luk,
        point
    }
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> player_status = Commands.literal("athena")
                .requires(cs -> cs.hasPermission(4))
                .then(Commands.literal("status")
                    .then(Commands.argument("action", EnumArgument.enumArgument(Action.class))
                            .then(Commands.argument("datatype", EnumArgument.enumArgument(DataType.class))
                                .then(Commands.argument("target", EntityArgument.player())
                                    .executes(command -> _player_status_0(command))
                                        .then(Commands.argument("value", IntegerArgumentType.integer())
                                            .executes(command -> _player_status_1(command)))))));

        dispatcher.register(player_status);
    }

    private static int _player_status_0(CommandContext<CommandSourceStack> command) {
        Player player = command.getSource().getPlayer();
        if(player == null) return -1;

        IPlayerStatus status = player.getData(Attachment_Register.PLAYER_STATUS);
        Action action = command.getArgument("action", Action.class);
        DataType datatype = command.getArgument("datatype", DataType.class);
        switch (action){
            case set, add -> {
                player.displayClientMessage(Component.literal("You must enter value"), true);
                return -1;
            }
        }
        return _player_status(command, player, status, action, datatype, 0);
    }

    private static int _player_status_1(CommandContext<CommandSourceStack> command) {
        Player player = command.getSource().getPlayer();
        if(player == null) return -1;

        IPlayerStatus status = player.getData(Attachment_Register.PLAYER_STATUS);
        Action action = command.getArgument("action", Action.class);
        DataType datatype = command.getArgument("datatype", DataType.class);
        int value = command.getArgument("value", Integer.class);
        return _player_status(command, player, status, action, datatype, value);
    }

    private static int _player_status(CommandContext<CommandSourceStack> command, Player player, IPlayerStatus status, Action action, DataType datatype, int value){
        if(action == Action.check){
            switch(datatype){
                case level -> {
                    player.displayClientMessage(Component.literal("Your level is: %d".formatted(status.getLevel())), true);
                }
                case mana -> {
                    player.displayClientMessage(Component.literal("Your mana is: %d / %d".formatted(status.getMana(), status.getManaMaximum())), true);
                }
                case exp -> {
                    player.displayClientMessage(Component.literal("Your exp is: %d / %d".formatted(status.getExp(), status.getExpMaximum(status.getLevel()))), true);
                }
                case max_hp -> {
                    player.displayClientMessage(Component.literal("Your max hp is: %d".formatted(status.getHealthMaximum())), true);
                }
                case str -> {
                    player.displayClientMessage(Component.literal("Your str is: %d".formatted(status.getStr())), true);
                }
                case dex -> {
                    player.displayClientMessage(Component.literal("Your dex is: %d".formatted(status.getDex())), true);
                }
                case _int -> {
                    player.displayClientMessage(Component.literal("Your int is: %d".formatted(status.getInt())), true);
                }
                case luk -> {
                    player.displayClientMessage(Component.literal("Your luk is: %d".formatted(status.getLuk())), true);
                }
                case point -> {
                    player.displayClientMessage(Component.literal("Your point is: %d".formatted(status.getPoint())), true);
                }
            }
        }

        if (action == Action.add){
            switch(datatype){
                case level -> {
                    status.addLevel(value);
                    player.displayClientMessage(Component.literal("Your level now is: %d".formatted(status.getLevel())), true);
                }
                case mana -> {
                    status.addMana(value);
                    player.displayClientMessage(Component.literal("Your mana now is: %d / %d".formatted(status.getMana(), status.getManaMaximum())), true);
                }
                case exp -> {
                    status.addExp(value);
                    player.displayClientMessage(Component.literal("Your exp now is: %d / %d".formatted(status.getExp(), status.getExpMaximum(status.getLevel()))), true);
                }
                case max_hp -> {
                    status.addMaxHealth(value);
                    player.displayClientMessage(Component.literal("Your max hp now is: %d".formatted(status.getHealthMaximum())), true);
                }
                case str -> {
                }
                case dex -> {
                }
                case _int -> {
                }
                case luk -> {
                }
                case point -> {
                }
            }
        }
        else if (action == Action.set){
            switch(datatype){
                case level -> {
                    status.setLevel(value);
                    player.displayClientMessage(Component.literal("Your level now is: %d".formatted(status.getLevel())), true);
                }
                case mana -> {
                    status.setMana(value);
                    player.displayClientMessage(Component.literal("Your mana now is: %d / %d".formatted(status.getMana(), status.getManaMaximum())), true);
                }
                case exp -> {
                    status.setExp(value);
                    player.displayClientMessage(Component.literal("Your exp now is: %d / %d".formatted(status.getExp(), status.getExpMaximum(status.getLevel()))), true);
                }
                case max_hp -> {
                    status.setMaxHealth(value);
                    player.displayClientMessage(Component.literal("Your level now is: %d".formatted(status.getHealthMaximum())), true);
                }
                case str -> {
                }
                case dex -> {
                }
                case _int -> {
                }
                case luk -> {
                }
                case point -> {
                }
            }
        }
        return Command.SINGLE_SUCCESS;
    }
}
