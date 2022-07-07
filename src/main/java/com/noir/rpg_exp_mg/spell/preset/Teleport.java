package com.noir.rpg_exp_mg.spell.preset;

import com.noir.rpg_exp_mg.items.Scroll;
import com.noir.rpg_exp_mg.spell.preset.Teleport;
import com.noir.rpg_exp_mg.custom.tool.RayCast;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult.Type;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;

public class Teleport {

    private Level world;
    private Player player;
    private double range;
    private int coolDown;
    private ClipContext.Fluid clip;
    private SoundEvent sound;
    private Scroll scroll;

    public Teleport(Level world, Player player, double range, int coolDown, Scroll scroll) {
        this.world = world;
        this.player = player;
        this.range = range;
        this.coolDown = coolDown;
        this.clip = ClipContext.Fluid.NONE;
        this.sound = SoundEvents.ENDERMAN_TELEPORT;
        this.scroll = scroll;
    }

    public Teleport(Level world, Player player, double range, int coolDown, ClipContext.Fluid clip,
            SoundEvent sound, Scroll scroll) {
        this.world = world;
        this.player = player;
        this.range = range;
        this.coolDown = coolDown;
        this.clip = clip;
        this.sound = sound;
        this.scroll = scroll;
    }

    public void run() {
        BlockHitResult ray = RayCast.rayTrace(this.world, this.player, this.clip, this.range);
        if (ray.getType() != Type.MISS) {
            BlockPos lookPos = ray.getBlockPos().relative(ray.getDirection());
            player.setPos(lookPos.getX(), lookPos.getY(), lookPos.getZ());
            player.getCooldowns().addCooldown(scroll, coolDown);
            player.fallDistance = 0F;
            world.playSound(player, player.getX(), player.getY(), player.getZ(),
                    sound,
                    SoundSource.PLAYERS, 1.0F, 1.0F);
        }

    }
}
