package com.noir.rpg_exp_mg.spell.schools.arcane.spells;

import com.noir.rpg_exp_mg.spell.schools.arcane.SchoolArcane;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;

import com.noir.rpg_exp_mg.spell.ASpell;
import com.noir.rpg_exp_mg.spell.ISpell;
import com.noir.rpg_exp_mg.spell.preset.Teleport;
import com.noir.rpg_exp_mg.spell.type.ASpellTypeInstant;
import com.noir.rpg_exp_mg.spell.type.ASpellTypeRelese;

import com.noir.rpg_exp_mg.custom.tool.CoolDown;
import com.noir.rpg_exp_mg.custom.tool.Sound;
import com.noir.rpg_exp_mg.energy.EnergyInterface;
import com.noir.rpg_exp_mg.energy.MyCapability;

public class ArcaneTeleport extends ASpell {

    public ArcaneTeleport() {
        super(new SchoolArcane());
    }

    @Override
    public ISpell startingSpell() {

        return new ArcaneInstantTeleport();
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
    public String toString() {
        return "Arcane Teleport";
    }

    public class ArcaneInstantTeleport extends ASpellTypeInstant {

        @Override
        public ISpell nextSpell() {

            return new ArcaneChargeTeleport();
        }

        public void using(Level world, Player player, InteractionHand hand) {

            System.out.println("Instant");
            if (exe(world, player, player.getItemInHand(hand).getItem())) {
                System.out.println("Change spell");

                setSpell(nextSpell());

                System.out.println("Spell is Charge: " + (getSpell() instanceof ArcaneChargeTeleport));
            }

        }

        @Override
        public InteractionResultHolder<ItemStack> whenUsing(Level world, Player player, InteractionHand hand) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void whileUsing(Level world, Player player, InteractionHand hand) {
            // TODO Auto-generated method stub

        }

    }

    public class ArcaneChargeTeleport extends ASpellTypeRelese {

        @Override
        public ArcaneInstantTeleport nextSpell() {
            return new ArcaneInstantTeleport();
        }

        @Override
        public void releaseUsing(ItemStack itemstaks, Level world, LivingEntity entity, int time) {

            System.out.println("Hold relese Use");
            int i = itemstaks.getItem().getUseDuration(itemstaks) - time;
            System.out.println(i);
            if (i < 30)
                return;

            if (entity instanceof Player) {
                System.out.println("Hold");
                if (exe(world, (Player) entity, itemstaks.getItem())) {
                    System.out.println("Chanche hold");
                    setSpell(nextSpell());
                    System.out.println("Spell is instant: " + (getSpell() instanceof ArcaneInstantTeleport));
                }
            }

        }

        @Override
        public UseAnim getUseAnimation(ItemStack itemStack) {
            return UseAnim.BOW;
        }

        @Override
        public InteractionResultHolder<ItemStack> whenUsing(Level world, Player player, InteractionHand hand) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void whileUsing(Level world, Player player, InteractionHand hand) {
            // TODO Auto-generated method stub

        }

    }

    @Override
    public InteractionResultHolder<ItemStack> whenUsing(Level world, Player player, InteractionHand hand) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void whileUsing(Level world, Player player, InteractionHand hand) {
        // TODO Auto-generated method stub

    }

}
