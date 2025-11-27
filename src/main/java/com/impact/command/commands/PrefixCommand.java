package com.zingersoft.command.commands;

import com.zingersoft.ZingerSoft;
import com.zingersoft.command.Command;

public class PrefixCommand extends Command {
    public PrefixCommand() {
        super("prefix", "Sets command prefix", "p");
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            ZingerSoft.commandManager.sendMessage("Current prefix: §f" + ZingerSoft.commandManager.getPrefix());
            ZingerSoft.commandManager.sendMessage("Usage: .prefix <new prefix>");
            return;
        }

        String newPrefix = args[0];
        ZingerSoft.commandManager.setPrefix(newPrefix);
        ZingerSoft.commandManager.sendMessage("§7Prefix set to: §f" + newPrefix);
    }
}

