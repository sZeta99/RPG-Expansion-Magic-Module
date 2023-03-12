package com.noir.rpg_exp_mg.spell;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

//Interface for the method of the spell, evry spell must have this method
public interface ISpell {

    public abstract InteractionResultHolder<ItemStack> onUse(ItemStack itemStack, Level level, Player player,
            InteractionHand hand);

    public abstract boolean onUseOnRelease(ItemStack itemStack);

    public abstract InteractionResult onUseOn(UseOnContext context);

    public abstract void onRelease(ItemStack itemStack, Level level, Player player, int time);

}
