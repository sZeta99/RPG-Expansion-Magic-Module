package com.noir.rpg_exp_mg.spell.schools.arcane;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import com.noir.rpg_exp_mg.spell.preset.Teleport;
import com.noir.rpg_exp_mg.custom.tool.CoolDown;
import com.noir.rpg_exp_mg.custom.tool.Sound;
import com.noir.rpg_exp_mg.items.IHoldCast;

public class ArcaneTeleport implements IHoldCast {

    @Override
    public boolean exe(ItemStack itemstack, Level world, LivingEntity entity, int i) {
        Teleport tp = new Teleport(world, (Player) entity, 50);
        if (tp.run()) {

            Sound.playSound(world, (Player) entity, SoundEvents.ENDERMAN_TELEPORT);
            CoolDown.addCoolDown((Player) entity, itemstack.getItem(), 5);
            itemstack.hurtAndBreak(1, (Player) entity, (arg0) -> {
                arg0.broadcastBreakEvent(entity.getUsedItemHand());
            });
        }
        return true;
    }

}
