package com.noir.rpg_exp_mg.spell;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface ISpell {

    public default boolean exe(ItemStack itemstack, Level world, LivingEntity entity, int i) {
        return false;
    }

    public void releaseUsing(ItemStack itemstack, Level world, LivingEntity entity, int i);

    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand);

}
