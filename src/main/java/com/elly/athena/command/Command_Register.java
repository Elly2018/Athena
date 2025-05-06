package com.elly.athena.command;

import com.elly.athena.command.struct.ActionStruct;
import com.elly.athena.command.types.ActionType;
import com.elly.athena.command.types.PlayerDataType;
import com.elly.athena.data.interfaceType.IPlayerStatus;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.neoforged.neoforge.server.command.EnumArgument;

import java.util.HashMap;
import java.util.function.Function;

import static com.elly.athena.command.PlayerStatusCommand.PlayerStatus_0;
import static com.elly.athena.command.PlayerStatusCommand.PlayerStatus_1;
import static com.elly.athena.command.TeleportCommand.Teleport_0;

public class Command_Register {

    public static HashMap<PlayerDataType, Function<IPlayerStatus, ActionStruct<Integer>>> ActionMap;

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> player_status = Commands.literal("athena")
                .requires(cs -> cs.hasPermission(4))
                .then(Commands.literal("status")
                    .then(Commands.argument("action", EnumArgument.enumArgument(ActionType.class))
                            .then(Commands.argument("PlayerDataType", EnumArgument.enumArgument(PlayerDataType.class))
                                .then(Commands.argument("target", EntityArgument.player())
                                    .executes(command -> PlayerStatus_0(command))
                                        .then(Commands.argument("value", IntegerArgumentType.integer())
                                            .executes(command -> PlayerStatus_1(command))
                                        )
                                )
                            )
                    )
                );

        LiteralArgumentBuilder<CommandSourceStack> tp_setter = Commands.literal("athena")
                .requires(cs -> cs.hasPermission(4))
                .then(Commands.literal("tp")
                        .then(Commands.argument("action", EnumArgument.enumArgument(ActionType.class))
                                .executes(command -> Teleport_0(command))
                        )
                );


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
}

