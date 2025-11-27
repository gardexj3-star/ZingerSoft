package com.zingersoft.module.movement;

import com.zingersoft.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", "Automatically sprints", Category.MOVEMENT);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (mc.player == null) return;
        if (mc.player.moveForward > 0 && !mc.player.isSneaking()) {
            mc.player.setSprinting(true);
        }
    }
}

