package com.zingersoft.config;

import com.zingersoft.ZingerSoft;
import com.zingersoft.module.Module;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigManager {
    private File configDir = new File("zingersoft");
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ConfigManager() {
        if (!configDir.exists()) {
            configDir.mkdirs();
        }
    }

    public void save(String name) {
        try {
            File configFile = new File(configDir, name + ".json");
            JsonObject json = new JsonObject();

            // Save modules
            JsonObject modules = new JsonObject();
            for (Module module : ZingerSoft.moduleManager.getModules()) {
                JsonObject moduleJson = new JsonObject();
                moduleJson.addProperty("toggled", module.isToggled());
                moduleJson.addProperty("key", module.getKey());
                modules.add(module.getName(), moduleJson);
            }
            json.add("modules", modules);

            // Save prefix
            json.addProperty("prefix", ZingerSoft.commandManager.getPrefix());

            FileWriter writer = new FileWriter(configFile);
            gson.toJson(json, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        load("default");
    }

    public void load(String name) {
        try {
            File configFile = new File(configDir, name + ".json");
            if (!configFile.exists()) {
                return;
            }

            FileReader reader = new FileReader(configFile);
            JsonObject json = new JsonParser().parse(reader).getAsJsonObject();
            reader.close();

            // Load modules
            if (json.has("modules")) {
                JsonObject modules = json.getAsJsonObject("modules");
                for (Module module : ZingerSoft.moduleManager.getModules()) {
                    if (modules.has(module.getName())) {
                        JsonObject moduleJson = modules.getAsJsonObject(module.getName());
                        if (moduleJson.has("toggled")) {
                            module.setToggled(moduleJson.get("toggled").getAsBoolean());
                        }
                        if (moduleJson.has("key")) {
                            module.setKey(moduleJson.get("key").getAsInt());
                        }
                    }
                }
            }

            // Load prefix
            if (json.has("prefix")) {
                ZingerSoft.commandManager.setPrefix(json.get("prefix").getAsString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

