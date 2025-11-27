package com.zingersoft.command.commands;

import com.zingersoft.ZingerSoft;
import com.zingersoft.command.Command;
import com.zingersoft.module.Module;

public class ToggleCommand extends Command {
    public ToggleCommand() {
        super("toggle", "Toggles a module", "t");
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            ZingerSoft.commandManager.sendMessage("Usage: .toggle <module>");
            return;
        }

        String moduleName = String.join(" ", args);
        Module module = ZingerSoft.moduleManager.getModule(moduleName);
        
        if (module != null) {
            module.toggle();
            ZingerSoft.commandManager.sendMessage("§7" + module.getName() + " §7" + 
                    (module.isToggled() ? "§aenabled" : "§cdisabled"));
        } else {
            ZingerSoft.commandManager.sendMessage("§cModule not found: " + moduleName);
        }
    }
}

