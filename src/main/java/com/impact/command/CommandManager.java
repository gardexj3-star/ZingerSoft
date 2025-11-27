package com.zingersoft.command;

import com.zingersoft.ZingerSoft;
import com.zingersoft.command.commands.*;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager {
    private List<Command> commands = new ArrayList<>();
    private String prefix = ".";

    public CommandManager() {
        registerCommands();
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void registerCommands() {
        addCommand(new HelpCommand());
        addCommand(new ToggleCommand());
        addCommand(new BindCommand());
        addCommand(new ConfigCommand());
        addCommand(new FriendCommand());
        addCommand(new PrefixCommand());
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public List<Command> getCommands() {
        return commands;
    }

    public Command getCommand(String name) {
        return commands.stream()
                .filter(cmd -> cmd.getName().equalsIgnoreCase(name) || 
                        Arrays.asList(cmd.getAliases()).contains(name.toLowerCase()))
                .findFirst()
                .orElse(null);
    }

    @SubscribeEvent
    public void onChat(ClientChatEvent event) {
        String message = event.getMessage();
        
        if (message.startsWith(prefix)) {
            event.setCanceled(true);
            Minecraft.getMinecraft().ingameGUI.getChatGUI().addToSentMessages(message);
            
            String[] args = message.substring(prefix.length()).split(" ");
            String commandName = args[0];
            
            Command command = getCommand(commandName);
            if (command != null) {
                String[] commandArgs = Arrays.copyOfRange(args, 1, args.length);
                command.execute(commandArgs);
            } else {
                sendMessage("Unknown command. Use .help for help.");
            }
        }
    }

    public void sendMessage(String message) {
        if (Minecraft.getMinecraft().player != null) {
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString("§7[§6ZingerSoft§7] §r" + message));
        }
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}

