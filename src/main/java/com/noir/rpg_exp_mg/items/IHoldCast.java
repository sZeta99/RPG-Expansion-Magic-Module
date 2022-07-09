package com.noir.rpg_exp_mg.items;

import com.noir.rpg_exp_mg.spell.ISpell;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IHoldCast extends ISpell {

    public default void releaseUsing(ItemStack itemstack, Level world, LivingEntity entity, int i) {

        if (entity instanceof Player) {
            exe(itemstack, world, entity, i);
        }
    }

    public default InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        System.out.println("Used");
        ItemStack itemstack = player.getItemInHand(hand);
        player.startUsingItem(hand);

        return InteractionResultHolder.consume(itemstack);

    }

}
