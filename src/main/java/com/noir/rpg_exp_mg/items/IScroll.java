package com.noir.rpg_exp_mg.items;

import com.noir.rpg_exp_mg.spell.ISpell;
import com.noir.rpg_exp_mg.spell.schools.arcane.spells.ArcaneTeleport;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class IScroll extends BowItem {

    private ISpell spell;

    public IScroll(Properties p_41383_) {
        super(p_41383_);
        System.out.println("Construct");
        this.spell = new ArcaneTeleport();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {

        return spell.use(world, player, hand);
    }

    @Override
    public void releaseUsing(ItemStack itemstaks, Level world, LivingEntity entity, int i) {

        spell.releaseUsing(itemstaks, world, entity, i);

    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {

        return spell.getUseAnimation(itemStack);
    }

}
