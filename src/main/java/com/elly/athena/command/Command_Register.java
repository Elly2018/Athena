package com.elly.athena.command;

import com.elly.athena.Athena;
import com.elly.athena.command.types.ActionType;
import com.elly.athena.command.types.PlayerDataType;
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

import java.util.HashMap;
import java.util.function.Function;

public class Command_Register {

    private static HashMap<PlayerDataType, Function<IPlayerStatus, ActionStruct<Integer>>> ActionMap;

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> player_status = Commands.literal("athena")
                .requires(cs -> cs.hasPermission(4))
                .then(Commands.literal("status")
                    .then(Commands.argument("action", EnumArgument.enumArgument(ActionType.class))
                            .then(Commands.argument("PlayerDataType", EnumArgument.enumArgument(PlayerDataType.class))
                                .then(Commands.argument("target", EntityArgument.player())
                                    .executes(command -> _player_status_0(command))
                                        .then(Commands.argument("value", IntegerArgumentType.integer())
                                            .executes(command -> _player_status_1(command)))))));

        dispatcher.register(player_status);
        ActionMap = new HashMap<>();
        ActionMap.put(PlayerDataType.level, ActionStruct.LEVEL());
        ActionMap.put(PlayerDataType.coin, ActionStruct.COIN());
        ActionMap.put(PlayerDataType.mana, ActionStruct.MANA());
        ActionMap.put(PlayerDataType.max_mana, ActionStruct.MAXMANA());
        ActionMap.put(PlayerDataType.exp, ActionStruct.EXP());
        ActionMap.put(PlayerDataType.max_hp, ActionStruct.MAXHP());
        ActionMap.put(PlayerDataType.str, ActionStruct.STR());
        ActionMap.put(PlayerDataType.dex, ActionStruct.DEX());
        ActionMap.put(PlayerDataType._int, ActionStruct.INT());
        ActionMap.put(PlayerDataType.luk, ActionStruct.LUK());
        ActionMap.put(PlayerDataType.point, ActionStruct.POINT());
    }

    private static int _player_status_0(CommandContext<CommandSourceStack> command) {
        Player player = command.getSource().getPlayer();
        if(player == null) return -1;

        IPlayerStatus status = player.getData(Attachment_Register.PLAYER_STATUS);
        ActionType action = command.getArgument("action", ActionType.class);
        PlayerDataType PlayerDataType = command.getArgument("PlayerDataType", PlayerDataType.class);
        switch (action){
            case set, add -> {
                player.displayClientMessage(Component.literal("You must enter value"), true);
                return -1;
            }
        }
        return _player_status(command, player, status, action, PlayerDataType, 0);
    }

    private static int _player_status_1(CommandContext<CommandSourceStack> command) {
        Player player = command.getSource().getPlayer();
        if(player == null) return -1;

        IPlayerStatus status = player.getData(Attachment_Register.PLAYER_STATUS);
        ActionType action = command.getArgument("action", ActionType.class);
        PlayerDataType PlayerDataType = command.getArgument("PlayerDataType", PlayerDataType.class);
        int value = command.getArgument("value", Integer.class);
        return _player_status(command, player, status, action, PlayerDataType, value);
    }

    private static int _player_status(CommandContext<CommandSourceStack> command, Player player, IPlayerStatus status, ActionType action, PlayerDataType PlayerDataType, int value){
        if (action == ActionType.add){
            addStatus(PlayerDataType, status, value);
        }
        else if (action == ActionType.set){
            setStatus(PlayerDataType, status, value);
        }

        switch(PlayerDataType){
            case level -> { player.displayClientMessage(Component.literal("Your level is: %d".formatted(status.getLevel())), true); }
            case coin -> { player.displayClientMessage(Component.literal("Your coin is: %d".formatted(status.getCoin())), true); }
            case mana, max_mana -> { player.displayClientMessage(Component.literal("Your mana is: %d / %d".formatted(status.getMana(), status.getManaMaximum())), true); }
            case exp -> { player.displayClientMessage(Component.literal("Your exp is: %d / %d".formatted(status.getExp(), status.getExpMaximum(status.getLevel()))), true); }
            case max_hp -> { player.displayClientMessage(Component.literal("Your max hp is: %d".formatted(status.getHealthMaximum())), true); }
            case str -> { player.displayClientMessage(Component.literal("Your str is: %d".formatted(status.getStr())), true); }
            case dex -> { player.displayClientMessage(Component.literal("Your dex is: %d".formatted(status.getDex())), true); }
            case _int -> { player.displayClientMessage(Component.literal("Your int is: %d".formatted(status.getInt())), true); }
            case luk -> { player.displayClientMessage(Component.literal("Your luk is: %d".formatted(status.getLuk())), true); }
            case point -> { player.displayClientMessage(Component.literal("Your point is: %d".formatted(status.getPoint())), true); }
        }
        return Command.SINGLE_SUCCESS;
    }

    private static void addStatus(PlayerDataType type, IPlayerStatus ps, int value){
        if(!ActionMap.containsKey(type)) {
            Athena.LOGGER.warn(String.format("Key does not exist: %s", type.toString()));
            return;
        }
        Function<IPlayerStatus, ActionStruct<Integer>> p = ActionMap.get(type);
        p.apply(ps).adder.accept(value);
    }

    private static void setStatus(PlayerDataType type, IPlayerStatus ps, int value){
        if(!ActionMap.containsKey(type)) {
            Athena.LOGGER.warn(String.format("Key does not exist: %s", type.toString()));
            return;
        }
        Function<IPlayerStatus, ActionStruct<Integer>> p = ActionMap.get(type);
        p.apply(ps).setter.accept(value);
    }
}

