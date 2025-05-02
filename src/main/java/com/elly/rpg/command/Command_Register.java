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
import net.minecraftforge.common.util.LazyOptional;

import java.util.Optional;

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
            LazyOptional<IMana> mana = player.getCapability(CapabilitySystem.MANA);
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 2));
            int _mana = 0;
            Optional<IMana> target = mana.resolve();
            if(!target.isEmpty()){
                _mana = target.get().getMana();
            }
            player.displayClientMessage(Component.literal("Your mana: %d".formatted(_mana)), true);

        }
        return Command.SINGLE_SUCCESS;
    }
}
