package com.zingersoft.module.combat;

import com.zingersoft.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class KillAura extends Module {
    private float range = 4.5f;
    private boolean players = true;
    private boolean mobs = false;
    private boolean invisible = false;
    private int delay = 0;
    private int ticks = 0;

    public KillAura() {
        super("KillAura", "Automatically attacks nearby entities", Category.COMBAT);
    }

    @SubscribeEvent
    public void onUpdate(net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent event) {
        if (mc.player == null || mc.world == null) return;
        
        ticks++;
        if (ticks < delay) return;
        ticks = 0;

        List<Entity> targets = mc.world.loadedEntityList.stream()
                .filter(entity -> entity instanceof EntityLivingBase)
                .filter(entity -> entity != mc.player)
                .filter(entity -> !entity.isDead)
                .filter(entity -> mc.player.getDistance(entity) <= range)
                .filter(entity -> {
                    if (entity instanceof EntityPlayer && !players) return false;
                    if (!(entity instanceof EntityPlayer) && !mobs) return false;
                    if (entity.isInvisible() && !invisible) return false;
                    return true;
                })
                .sorted(Comparator.comparing(entity -> mc.player.getDistance(entity)))
                .collect(Collectors.toList());

        if (!targets.isEmpty()) {
            Entity target = targets.get(0);
            if (target instanceof EntityLivingBase) {
                mc.playerController.attackEntity(mc.player, target);
                mc.player.swingArm(EnumHand.MAIN_HAND);
            }
        }
    }
}

