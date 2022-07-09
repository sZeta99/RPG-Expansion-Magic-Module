package com.noir.rpg_exp_mg.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface ISpell {

    public void releaseUsing(ItemStack itemstaks, Level world, LivingEntity entity, int i);

    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand);

    public default InteractionResultHolder<ItemStack> defaultuse(Level world, Player player, InteractionHand hand) {

        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

}
