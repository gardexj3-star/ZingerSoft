package com.zingersoft.module;

import com.zingersoft.module.combat.*;
import com.zingersoft.module.movement.*;
import com.zingersoft.module.player.*;
import com.zingersoft.module.render.*;
import com.zingersoft.module.world.*;
import com.zingersoft.module.misc.*;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    private List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        // Combat
        addModule(new KillAura());
        addModule(new Aura());
        addModule(new AutoArmor());
        addModule(new AutoTotem());
        addModule(new Criticals());
        addModule(new Regen());
        addModule(new Velocity());

        // Movement
        addModule(new Fly());
        addModule(new Speed());
        addModule(new Sprint());
        addModule(new Step());
        addModule(new NoSlow());
        addModule(new LongJump());
        addModule(new Jesus());
        addModule(new AirJump());
        addModule(new Phase());
        addModule(new Spider());

        // Player
        addModule(new NoFall());
        addModule(new Blink());
        addModule(new Freecam());
        addModule(new AutoRespawn());
        addModule(new FastPlace());
        addModule(new FastBreak());
        addModule(new NoRotate());
        addModule(new AutoTool());
        addModule(new AutoEat());

        // Render
        addModule(new ESP());
        addModule(new Tracers());
        addModule(new Nametags());
        addModule(new Chams());
        addModule(new FullBright());
        addModule(new NoRender());
        addModule(new XRay());
        addModule(new StorageESP());
        addModule(new Search());
        addModule(new ItemESP());
        addModule(new MobESP());

        // World
        addModule(new Scaffold());
        addModule(new Nuker());
        addModule(new AutoMine());
        addModule(new Flatten());
        addModule(new Tunnel());
        addModule(new Filler());
        addModule(new AutoBuild());

        // Misc
        addModule(new ClickGUIModule());
        addModule(new HUD());
        addModule(new AutoGG());
        addModule(new AutoReconnect());
        addModule(new ChatSuffix());
        addModule(new MiddleClick());
        addModule(new TabGUI());
        addModule(new Notifications());
    }

    public void addModule(Module module) {
        modules.add(module);
    }

    public List<Module> getModules() {
        return modules;
    }

    public Module getModule(String name) {
        return modules.stream()
                .filter(module -> module.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public List<Module> getModulesByCategory(Module.Category category) {
        return modules.stream()
                .filter(module -> module.getCategory() == category)
                .collect(Collectors.toList());
    }

    public List<Module> getToggledModules() {
        return modules.stream()
                .filter(Module::isToggled)
                .collect(Collectors.toList());
    }
}

