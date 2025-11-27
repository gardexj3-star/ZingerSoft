package com.zingersoft.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Command {
    private String name;
    private String description;
    private String[] aliases;

    public Command(String name, String description, String... aliases) {
        this.name = name;
        this.description = description;
        this.aliases = aliases;
    }

    public abstract void execute(String[] args);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getAliases() {
        return aliases;
    }

    public List<String> getAliasesList() {
        List<String> aliasList = new ArrayList<>();
        aliasList.add(name);
        aliasList.addAll(Arrays.asList(aliases));
        return aliasList;
    }
}

