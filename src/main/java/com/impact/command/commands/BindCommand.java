package com.zingersoft.command.commands;

import com.zingersoft.ZingerSoft;
import com.zingersoft.command.Command;
import com.zingersoft.module.Module;
import org.lwjgl.input.Keyboard;

public class BindCommand extends Command {
    public BindCommand() {
        super("bind", "Binds a key to a module", "b");
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            ZingerSoft.commandManager.sendMessage("Usage: .bind <module> <key>");
            return;
        }

        String moduleName = args[0];
        String keyName = args[1].toUpperCase();
        
        Module module = ZingerSoft.moduleManager.getModule(moduleName);
        if (module == null) {
            ZingerSoft.commandManager.sendMessage("§cModule not found: " + moduleName);
            return;
        }

        int key = Keyboard.getKeyIndex(keyName);
        if (key == Keyboard.KEY_NONE && !keyName.equals("NONE")) {
            ZingerSoft.commandManager.sendMessage("§cInvalid key: " + keyName);
            return;
        }

        module.setKey(key);
        ZingerSoft.commandManager.sendMessage("§7Bound §f" + module.getName() + 
                " §7to §f" + (key == Keyboard.KEY_NONE ? "NONE" : keyName));
    }
}

