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

public class ArcaneTeleportCharge implements ISpell {

    private ASpell father;

    public ArcaneTeleportCharge(ASpell father) {
        this.father = father;

    }

    @Override
    public InteractionResultHolder<ItemStack> onUse(ItemStack itemStack, Level level, Player player,
            InteractionHand hand) {
        // TODO Auto-generated method stub
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

        if (ArcaneTeleport.canExe(level, player, father) && time > 2000) {
            ArcaneTeleport.exe(level, player, father);
            CoolDown.addCoolDown(player, itemStack.getItem(), 50);
            father.nextSpell(new ArcaneTeleportInstant(father));

            System.out.println("ArcaneTeleportCharge.onRelese() " + time);
        } else {
            System.out.println("ArcaneTeleportCharge.onRelese() not done");
        }

    }

}
