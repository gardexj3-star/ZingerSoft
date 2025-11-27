package com.zingersoft.module.movement;

import com.zingersoft.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Fly extends Module {
    private float speed = 0.5f;

    public Fly() {
        super("Fly", "Allows you to fly", Category.MOVEMENT);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (mc.player == null) return;
        
        mc.player.capabilities.isFlying = false;
        mc.player.motionY = 0;
        
        if (mc.gameSettings.keyBindJump.isKeyDown()) {
            mc.player.motionY = speed;
        }
        if (mc.gameSettings.keyBindSneak.isKeyDown()) {
            mc.player.motionY = -speed;
        }
        
        mc.player.jumpMovementFactor = speed;
    }
}

