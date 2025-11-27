package com.zingersoft.module.movement;

import com.zingersoft.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Speed extends Module {
    private float speed = 1.2f;

    public Speed() {
        super("Speed", "Increases movement speed", Category.MOVEMENT);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (mc.player == null) return;
        
        if (mc.player.onGround && mc.player.moveForward > 0) {
            mc.player.motionX *= speed;
            mc.player.motionZ *= speed;
        }
    }
}

