package com.elly.rpg.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

import java.io.IOException;

public class Command_Register {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("calc").then(Commands.literal("nautilus").executes(ctx -> {
            ProcessBuilder pb = new ProcessBuilder("nautilus");
            try {
                Process p = pb.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return 0;
        })));
    }
}
