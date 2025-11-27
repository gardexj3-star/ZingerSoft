package com.zingersoft.command.commands;

import com.zingersoft.ZingerSoft;
import com.zingersoft.command.Command;

public class ConfigCommand extends Command {
    public ConfigCommand() {
        super("config", "Manages configs", "cfg");
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            ZingerSoft.commandManager.sendMessage("Usage: .config <save/load> [name]");
            return;
        }

        String action = args[0].toLowerCase();
        String name = args.length > 1 ? args[1] : "default";

        if (action.equals("save")) {
            ZingerSoft.configManager.save(name);
            ZingerSoft.commandManager.sendMessage("§7Config saved: §f" + name);
        } else if (action.equals("load")) {
            ZingerSoft.configManager.load(name);
            ZingerSoft.commandManager.sendMessage("§7Config loaded: §f" + name);
        } else {
            ZingerSoft.commandManager.sendMessage("§cUnknown action: " + action);
        }
    }
}

