package com.zingersoft.command.commands;

import com.zingersoft.ZingerSoft;
import com.zingersoft.command.Command;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help", "Shows all commands", "h");
    }

    @Override
    public void execute(String[] args) {
        ZingerSoft.commandManager.sendMessage("§6=== Commands ===");
        ZingerSoft.commandManager.getCommands().forEach(cmd -> {
            ZingerSoft.commandManager.sendMessage("§7" + ZingerSoft.commandManager.getPrefix() + cmd.getName() + 
                    " §8- §f" + cmd.getDescription());
        });
    }
}

