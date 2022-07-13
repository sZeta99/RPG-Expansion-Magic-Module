package com.noir.rpg_exp_mg.spell.type;

import com.noir.rpg_exp_mg.spell.ISpell;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class ASpellTypePerpetual implements ISpell {

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        using(world, player, hand);
        player.startUsingItem(hand);
        return useDefault(player, hand);
    }

    @Override
    public void releaseUsing(ItemStack itemstaks, Level world, LivingEntity entity, int time) {

    }

}
