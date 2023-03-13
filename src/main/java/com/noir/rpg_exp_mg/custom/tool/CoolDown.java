package com.noir.rpg_exp_mg.custom.tool;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

public class CoolDown {

    public static void addCoolDown(Player player, Item item, int time) {
        // Control for good or bad result needed
        player.getCooldowns().addCooldown(item, time);
    }

    public static boolean isCoolDown(Player player, Item item) {
        return player.getCooldowns().isOnCooldown(item);
    }

}
