package com.noir.rpg_exp_mg.custom.tool;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

public class CoolDown {

    public static void addCoolDown(Player player, Item item, int time) {
        // add cooldown
        player.getCooldowns().addCooldown(item, time);
    }

    public static boolean isCoolDown(Player player, Item item) {
        System.out.println("PlayerCools : " + player.getCooldowns());
        System.out.println("Item : " + item);
        System.out.println("CoolDown : " + player.getCooldowns().isOnCooldown(item));
        return true;
    }

}
