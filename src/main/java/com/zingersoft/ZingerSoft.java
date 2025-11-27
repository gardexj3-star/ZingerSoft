package com.zingersoft;

import com.zingersoft.command.CommandManager;
import com.zingersoft.config.ConfigManager;
import com.zingersoft.gui.ClickGUI;
import com.zingersoft.gui.HUD;
import com.zingersoft.module.Module;
import com.zingersoft.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = ZingerSoft.MODID, name = ZingerSoft.NAME, version = ZingerSoft.VERSION)
public class ZingerSoft {
    public static final String MODID = "zingersoft";
    public static final String NAME = "ZingerSoft";
    public static final String VERSION = "1.0.0";

    public static ZingerSoft instance;
    public static ModuleManager moduleManager;
    public static CommandManager commandManager;
    public static ConfigManager configManager;
    public static ClickGUI clickGUIScreen;
    public static HUD hud;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        instance = this;
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        moduleManager = new ModuleManager();
        commandManager = new CommandManager();
        configManager = new ConfigManager();
        clickGUIScreen = new ClickGUI();
        hud = new HUD();

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(moduleManager);
        MinecraftForge.EVENT_BUS.register(hud);

        configManager.load();
        System.out.println("[" + NAME + "] Initialized successfully!");
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            int key = Keyboard.getEventKey();
            if (key == Keyboard.KEY_NONE) return;

            for (Module module : moduleManager.getModules()) {
                if (module.getKey() == key) {
                    module.toggle();
                }
            }

            // Open ClickGUI
            Module clickGUIModule = moduleManager.getModule("ClickGUI");
            if (clickGUIModule != null && clickGUIModule.getKey() == key) {
                Minecraft.getMinecraft().displayGuiScreen(clickGUIScreen);
            }
        }
    }
}

