package com.noir.rpg_exp_mg.spell.preset;

import com.noir.rpg_exp_mg.spell.preset.Teleport;
import com.noir.rpg_exp_mg.custom.tool.RayCast;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;

public class Teleport {

    private Level world;
    private Player player;
    private double range;
    private ClipContext.Fluid clip;
    private BlockPos position;

    public Teleport(Level world, Player player, double range) {
        this(world, player, range, ClipContext.Fluid.NONE, null);
    }

    public Teleport(Level world, Player player, double range, ClipContext.Fluid clip, BlockPos position) {
        this.world = world;
        this.player = player;
        this.range = range;
        this.position = position;
        this.clip = clip;
    }

    /**
     * 
     * @return If the teleport was succesful
     */
    public boolean run() {
        this.setPositionByLook();
        if (position != null) { // Refactoring needed
            player.setPos(position.getX(), position.getY(), position.getZ());
            player.fallDistance = 0F;
            return true;
        }
        return false;
    }

    public void setPositionByLook() {
        this.position = RayCast.getPositionByLook(world, player, clip, range);
    }

}
