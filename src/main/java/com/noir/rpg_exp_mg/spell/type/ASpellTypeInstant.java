package com.noir.rpg_exp_mg.spell.type;

import com.noir.rpg_exp_mg.spell.ISpell;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class ASpellTypeInstant implements ISpell {

    @Override
    public void releaseUsing(ItemStack itemstaks, Level world, LivingEntity entity, int time) {

    }

}
