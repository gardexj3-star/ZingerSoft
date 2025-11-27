package com.zingersoft.gui;

import com.zingersoft.ZingerSoft;
import com.zingersoft.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class HUD {
    private Minecraft mc = Minecraft.getMinecraft();

    public HUD() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.TEXT) return;
        if (mc.player == null || mc.world == null) return;

        ScaledResolution sr = new ScaledResolution(mc);
        int y = 2;

        // Draw ZingerSoft watermark
        mc.fontRenderer.drawStringWithShadow("§6ZingerSoft §7v" + ZingerSoft.VERSION, 2, y, 0xFFFFFF);
        y += 12;

        // Draw active modules
        List<Module> activeModules = ZingerSoft.moduleManager.getToggledModules();
        for (Module module : activeModules) {
            if (module.isVisible() && !module.getName().equals("HUD") && !module.getName().equals("ClickGUI")) {
                mc.fontRenderer.drawStringWithShadow("§7" + module.getName(), 2, y, 0xFFFFFF);
                y += 10;
            }
        }
    }
}

