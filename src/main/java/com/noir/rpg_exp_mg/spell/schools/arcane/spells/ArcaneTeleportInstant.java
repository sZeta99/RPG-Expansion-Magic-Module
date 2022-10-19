package com.noir.rpg_exp_mg.spell.schools.arcane.spells;

import com.noir.rpg_exp_mg.custom.tool.CoolDown;
import com.noir.rpg_exp_mg.spell.ASpell;
import com.noir.rpg_exp_mg.spell.ISpell;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class ArcaneTeleportInstant implements ISpell {
    private ASpell father;
    private boolean exe;

    public ArcaneTeleportInstant(ASpell father) {
        this.father = father;
        exe = false;

    }

    // Use a local spell to save next spelle and call next spell to chenge father
    @Override
    public InteractionResultHolder<ItemStack> onUse(ItemStack itemStack, Level level, Player player,
            InteractionHand hand) {
        if (ArcaneTeleport.canExe(level, player, father)) {
            ArcaneTeleport.exe(level, player, father);
            exe = true;
            System.out.println("ArcaneTeleportInstant.onUse()");
        } else {
            System.out.println("ArcaneTeleportInstant.onUse() not done");
        }
        return null;
    }

    @Override
    public boolean onUseOnRelease(ItemStack itemStack) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public InteractionResult onUseOn(UseOnContext context) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onRelease(ItemStack itemStack, Level level, Player player, int time) {
        if (exe) {
            // Look if exit a method to stop using item
            CoolDown.addCoolDown(player, itemStack.getItem(), 50);
            father.nextSpell(new ArcaneTeleportCharge(father));
            exe = false;
        }

    }

}
