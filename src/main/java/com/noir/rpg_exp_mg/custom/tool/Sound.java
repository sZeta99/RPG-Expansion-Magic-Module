package com.noir.rpg_exp_mg.custom.tool;

import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

public class Sound {

    public static void playSound(Level world, Player player, SoundEvent sound) {

        world.playSound(player, player.getX(), player.getY(), player.getZ(),
                sound,
                SoundSource.PLAYERS, 1.0F, 1.0F);

    }

}
