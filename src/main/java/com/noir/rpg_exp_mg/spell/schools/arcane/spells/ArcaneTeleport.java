package com.noir.rpg_exp_mg.spell.schools.arcane.spells;

import com.noir.rpg_exp_mg.spell.schools.arcane.SchoolArcane;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;

import com.noir.rpg_exp_mg.spell.ISpell;
import com.noir.rpg_exp_mg.spell.preset.Teleport;

import com.noir.rpg_exp_mg.custom.tool.CoolDown;
import com.noir.rpg_exp_mg.custom.tool.Sound;
import com.noir.rpg_exp_mg.energy.EnergyInterface;
import com.noir.rpg_exp_mg.energy.MyCapability;

public class ArcaneTeleport implements ISpell {

    // Vedere se si può astrearre
    enum State {

        MAGIARITORNO(new ArcaneTeleport());

        private ISpell spell;

        State(ISpell spell) {
            this.spell = spell;
        }

        public ISpell getSpell() {
            return spell;
        }

    }

    public ArcaneTeleport() {

    }

    /**
     * Execute the spell
     * 
     * @param world
     * @param player
     * @param item
     * @return
     */
    public static boolean exe(Level world, Player player, Item item) {
        if (!player.getCooldowns().isOnCooldown(item)) {
            LazyOptional<EnergyInterface> myCapability = player.getCapability(MyCapability.INSTANCE);

            // if (myCapability.isPresent())
            System.out.println("Exiting : " + myCapability.isPresent());
            System.out.println("---------------- " + MyCapability.INSTANCE.isRegistered());

            Teleport tp = new Teleport(world, player, 50);
            if (tp.run()) {
                CoolDown.addCoolDown(player, item, 50);
                Sound.playSound(world, player, SoundEvents.ENDERMAN_TELEPORT);

                return true;
            }
        }
        return false;
    }

    @Override
    public ISpell nextSpell() {
        // TODO Auto-generated method stub
        return null;
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
    public void onUseOnRelease(ItemStack itemStack, Level level, Player player, int time) {
        // TODO Auto-generated method stub

    }

}
