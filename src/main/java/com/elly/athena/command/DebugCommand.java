package com.elly.athena.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;

public class DebugCommand {
    public static int Debug_0(CommandContext<CommandSourceStack> command) {
        return Command.SINGLE_SUCCESS;
    }
}
