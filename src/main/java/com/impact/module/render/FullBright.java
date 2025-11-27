package com.zingersoft.module.render;

import com.zingersoft.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class FullBright extends Module {
    private float originalGamma;

    public FullBright() {
        super("FullBright", "Full brightness", Category.RENDER);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        originalGamma = mc.gameSettings.gammaSetting;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (mc.gameSettings != null) {
            mc.gameSettings.gammaSetting = 1000f;
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        if (mc.gameSettings != null) {
            mc.gameSettings.gammaSetting = originalGamma;
        }
    }
}

