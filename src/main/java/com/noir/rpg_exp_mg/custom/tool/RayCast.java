package com.noir.rpg_exp_mg.custom.tool;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult.Type;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;

/**
 * @author Noir
 *         {@summary} Collection of method that operate on ray casting and ray
 *         tracing for selecting position in the space, of object and entity
 */
public class RayCast {

    /**
     * Standard range at 15
     * 
     * @param world
     * @param player
     * @param fluidMode
     * @return
     */
    public static BlockHitResult rayTrace(Level world, Player player, ClipContext.Fluid fluidMode) {

        double range = 15;
        float f = player.getXRot();
        float f1 = player.getYRot();
        Vec3 vector3d = player.getEyePosition(1.0F);
        float f2 = Mth.cos(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
        float f3 = Mth.sin(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
        float f4 = -Mth.cos(-f * ((float) Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float) Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        Vec3 vector3d1 = vector3d.add((double) f6 * range, (double) f5 * range, (double) f7 * range);
        return world.clip(new ClipContext(vector3d, vector3d1, ClipContext.Block.OUTLINE, fluidMode, player));
    }

    /**
     * Method from: https://moddingtutorials.org/o19/advanced-items
     * 
     * @param world
     * @param player
     * @param fluidMode
     * @param rng       Range of the rayCast
     * @return
     */
    public static BlockHitResult rayTrace(Level world, Player player, ClipContext.Fluid fluidMode, double rng) {

        double range = rng;
        float f = player.getXRot();
        float f1 = player.getYRot();
        Vec3 vector3d = player.getEyePosition(1.0F);
        float f2 = Mth.cos(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
        float f3 = Mth.sin(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
        float f4 = -Mth.cos(-f * ((float) Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float) Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        Vec3 vector3d1 = vector3d.add((double) f6 * range, (double) f5 * range, (double) f7 * range);
        return world.clip(new ClipContext(vector3d, vector3d1, ClipContext.Block.OUTLINE, fluidMode, player));
    }

    public static BlockPos getPositionByLook(Level world, Player player, ClipContext.Fluid fluidMode, double rng) {

        BlockHitResult ray = RayCast.rayTrace(world, player, fluidMode, rng);
        if (ray.getType() != Type.MISS) {
            return ray.getBlockPos().relative(ray.getDirection());
        }
        return null;

    }
}
