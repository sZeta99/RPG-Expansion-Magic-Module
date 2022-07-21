package com.noir.rpg_exp_mg.energy;

import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

// Exemple EventHandler
public class MyForgeEventHandelr {
    @SubscribeEvent
    public void pickupItem(EntityItemPickupEvent event) {
        System.out.println("Chanched game mode uuuuuuuuuuuuuuu");
    }
}
