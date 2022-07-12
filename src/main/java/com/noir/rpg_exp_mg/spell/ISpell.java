package com.noir.rpg_exp_mg.spell;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public interface ISpell {

    /**
     * Put the logic here for instant use on left click
     * 
     * @param world
     * @param player
     * @param hand
     * @return
     */
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand);

    /**
     * Put the logic here for use un relese charge
     * 
     * @param itemstaks
     * @param world
     * @param entity
     * @param time
     */

    public default void releaseUsing(ItemStack itemstaks, Level world, LivingEntity entity, int time) {
    }

    /**
     * Override to change tipe of animention on left click
     * 
     * @param itemStack
     * @return
     */
    public default UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.BLOCK;
    }

    /**
     * Defult function , on use, dose nothing but return correctly
     * 
     * @param world
     * @param player
     * @param hand
     * @return
     */
    public default InteractionResultHolder<ItemStack> useDefaultInstant(Level world, Player player,
            InteractionHand hand) {

        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    /**
     * Tell the game that it must use the relese using function
     * 
     * @param world
     * @param player
     * @param hand
     * @return
     */
    public default InteractionResultHolder<ItemStack> useDefaultCharge(Level world, Player player,
            InteractionHand hand) {

        ItemStack itemstack = player.getItemInHand(hand);
        player.startUsingItem(hand);

        return InteractionResultHolder.consume(itemstack);

    }

    /**
     * This function must return the spell to use after the current is been use.
     * If in the main spell class must return the first spell.
     * This method can have logic in it.
     * 
     * @return The next spell in the Graph of spell
     */
    public ISpell nextSpell();

}
