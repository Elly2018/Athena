package com.elly.athena.command;

import com.elly.athena.Athena;
import com.elly.athena.command.struct.ActionStruct;
import com.elly.athena.command.types.ActionType;
import com.elly.athena.command.types.PlayerDataType;
import com.elly.athena.data.Attachment_Register;
import com.elly.athena.data.Attribute_Register;
import com.elly.athena.data.interfaceType.attachment.IPlayerStatus;
import com.elly.athena.system.BattleSystem;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import java.util.Objects;
import java.util.function.Function;

import static com.elly.athena.command.Command_Register.ActionMap;

public class PlayerStatusCommand {
    public static int PlayerStatus_00(CommandContext<CommandSourceStack> command) {
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

    public static int PlayerStatus_0(CommandContext<CommandSourceStack> command) throws CommandSyntaxException {
        Player player = EntityArgument.getPlayer(command, "target");
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

    public static int PlayerStatus_1(CommandContext<CommandSourceStack> command) {
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
        AttributeMap map = player.getAttributes();
        switch(PlayerDataType){
            case level -> { player.displayClientMessage(Component.literal("Your level is: %d".formatted(status.getLevel())), true); }
            case coin -> { player.displayClientMessage(Component.literal("Your coin is: %d".formatted(status.getCoin())), true); }
            case max_mana -> { player.displayClientMessage(Component.literal("Your mana is: %d / %d".formatted((int) Objects.requireNonNull(map.getInstance(Attribute_Register.MANA)).getValue(), (int) Objects.requireNonNull(map.getInstance(Attribute_Register.MANA_MAX)).getValue())), true); }
            case exp -> { player.displayClientMessage(Component.literal("Your exp is: %d / %d".formatted(status.getExp(), status.getExpMaximum(status.getLevel()))), true); }
            case max_hp -> { player.displayClientMessage(Component.literal("Your max hp is: %d".formatted((int) Objects.requireNonNull(map.getInstance(Attributes.MAX_HEALTH)).getValue())), true); }
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
