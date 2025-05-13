package com.elly.athena.command;

import com.elly.athena.command.struct.ActionStruct;
import com.elly.athena.command.types.ActionType;
import com.elly.athena.command.types.DebugType;
import com.elly.athena.command.types.PlayerDataType;
import com.elly.athena.data.interfaceType.attachment.IPlayerStatus;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.neoforged.neoforge.server.command.EnumArgument;

import java.util.HashMap;
import java.util.function.Function;

public class Command_Register {

    public static HashMap<PlayerDataType, Function<IPlayerStatus, ActionStruct<Integer>>> ActionMap;

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> player_status = Commands.literal("athena")
                .requires(cs -> cs.hasPermission(4))
                .then(Commands.literal("status")
                    .then(Commands.argument("action", EnumArgument.enumArgument(ActionType.class))
                            .then(Commands.argument("PlayerDataType", EnumArgument.enumArgument(PlayerDataType.class))
                                    .executes(PlayerStatusCommand::PlayerStatus_00)
                                        .then(Commands.argument("target", EntityArgument.player())
                                            .executes(PlayerStatusCommand::PlayerStatus_0)
                                                .then(Commands.argument("value", IntegerArgumentType.integer())
                                                    .executes(PlayerStatusCommand::PlayerStatus_1)
                                        )
                                )
                            )
                    )
                );

        LiteralArgumentBuilder<CommandSourceStack> tp_setter = Commands.literal("athena")
                .requires(cs -> cs.hasPermission(4))
                .then(Commands.literal("tp")
                        .then(Commands.argument("action", EnumArgument.enumArgument(ActionType.class))
                                .executes(TeleportCommand::Teleport_0)
                        )
                );

        LiteralArgumentBuilder<CommandSourceStack> debug = Commands.literal("athena")
                .requires(cs -> cs.hasPermission(4))
                .then(Commands.literal("debug")
                        .then(Commands.argument("type", EnumArgument.enumArgument(DebugType.class))
                            .executes(DebugCommand::Debug_0)
                        )
                );

        dispatcher.register(player_status);
        dispatcher.register(tp_setter);
        dispatcher.register(debug);
        ActionMap = new HashMap<>();
        ActionMap.put(PlayerDataType.level, ActionStruct.LEVEL());
        ActionMap.put(PlayerDataType.coin, ActionStruct.COIN());
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

