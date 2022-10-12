package com.noir.rpg_exp_mg.spell;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public interface ISpell {

    // Verificare che sia corretto ma non credo

    public ISpell nextSpell();

    public abstract InteractionResultHolder<ItemStack> onUse(ItemStack itemStack, Level level, Player player,
            InteractionHand hand);

    public abstract boolean onUseOnRelease(ItemStack itemStack);

    public abstract InteractionResult onUseOn(UseOnContext context);

    public abstract void onUseOnRelease(ItemStack itemStack, Level level, Player player, int time);

}
