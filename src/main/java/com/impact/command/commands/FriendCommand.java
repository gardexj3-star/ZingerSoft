package com.zingersoft.command.commands;

import com.zingersoft.ZingerSoft;
import com.zingersoft.command.Command;

public class FriendCommand extends Command {
    public FriendCommand() {
        super("friend", "Manages friends", "f");
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            ZingerSoft.commandManager.sendMessage("Usage: .friend <add/remove/list> [name]");
            return;
        }

        String action = args[0].toLowerCase();
        
        if (action.equals("list")) {
            ZingerSoft.commandManager.sendMessage("§7Friends list (not implemented)");
        } else if (args.length < 2) {
            ZingerSoft.commandManager.sendMessage("Usage: .friend <add/remove> <name>");
        } else {
            String name = args[1];
            ZingerSoft.commandManager.sendMessage("§7Friend " + action + " (not implemented): " + name);
        }
    }
}

