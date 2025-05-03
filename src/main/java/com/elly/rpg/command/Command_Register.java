package com.elly.rpg.command;

import com.elly.rpg.capability.CapabilitySystem;
import com.elly.rpg.capability.capability.IMana;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

public class Command_Register {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("bruh")
                .executes(command -> {
                    return execute(command);
                })
        );
    }

    private static int execute(CommandContext<CommandSourceStack> command){
        if(command.getSource().getEntity() instanceof Player){
            Player player = (Player) command.getSource().getEntity();
            IMana target = player.getCapability(CapabilitySystem.MANA);

            int _mana = 0;
            if(target != null) _mana = target.getMana();
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 2));
            player.displayClientMessage(Component.literal("Your mana: %d".formatted(_mana)), true);
        }
        return Command.SINGLE_SUCCESS;
    }
}
