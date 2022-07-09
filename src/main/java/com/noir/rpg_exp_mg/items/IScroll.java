package com.noir.rpg_exp_mg.items;

import com.noir.rpg_exp_mg.spell.ISpell;
import com.noir.rpg_exp_mg.spell.schools.arcane.ArcaneTeleport;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class IScroll extends BowItem {

    private ISpell spell;

    public IScroll(Properties p_41383_) {
        super(p_41383_);
        this.spell = new ArcaneTeleport();

        // TODO Auto-generated constructor stub
    }

    @Override
    public void releaseUsing(ItemStack p_40667_, Level p_40668_, LivingEntity p_40669_, int p_40670_) {

        spell.releaseUsing(p_40667_, p_40668_, p_40669_, p_40670_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {

        return spell.use(p_41432_, p_41433_, p_41434_);
    }

}
