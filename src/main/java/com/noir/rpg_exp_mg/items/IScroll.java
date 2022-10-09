package com.noir.rpg_exp_mg.items;

import com.noir.rpg_exp_mg.spell.ISpell;
import com.noir.rpg_exp_mg.spell.schools.arcane.spells.ArcaneTeleport;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class IScroll extends Item {

    // private ISpell spell;

    public IScroll(Properties p_41383_) {
        super(p_41383_);
        System.out.println("Construct");
        // this.spell = new ArcaneTeleport();
    }

    public void releaseUsing(ItemStack p_40667_, Level p_40668_, LivingEntity p_40669_, int p_40670_) {
        System.out.println("--------------Relese-----------------");
        if (p_40669_ instanceof Player) {
            Player player = (Player) p_40669_;

            int i = this.getUseDuration(p_40667_) - p_40670_;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(p_40667_, p_40668_, player, i,
                    true);
            if (i < 0)
                return;

        }
    }

    public static float getPowerForTime(int p_40662_) {
        float f = (float) p_40662_ / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    public int getUseDuration(ItemStack p_40680_) {
        return 72000;
    }

    public InteractionResultHolder<ItemStack> use(Level p_40672_, Player p_40673_, InteractionHand p_40674_) {
        ItemStack itemstack = p_40673_.getItemInHand(p_40674_);

        p_40673_.startUsingItem(p_40674_);
        return InteractionResultHolder.consume(itemstack);

    }

    public int getDefaultProjectileRange() {
        return 15;
    }

}
