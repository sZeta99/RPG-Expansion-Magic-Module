package com.noir.rpg_exp_mg.spell.schools.arcane.spells;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import com.noir.rpg_exp_mg.spell.ASpell;
import com.noir.rpg_exp_mg.spell.preset.Teleport;
import com.noir.rpg_exp_mg.custom.tool.CoolDown;
import com.noir.rpg_exp_mg.custom.tool.Sound;

//test class with teleport
public class ArcaneTeleport extends ASpell {

    // keep in mind enum for simil state machine

    // static factory method

    // comstructor
    public ArcaneTeleport(Properties properties) {
        super(properties);
        nextSpell(new ArcaneTeleportInstant(this));
    }

    /**
     * Execute the spell must return true if the spell is executed else false
     * 
     * @param world
     * @param player
     * @param item
     * @return
     */
    // Controllare l'output della funzione, provare magari a spostare il tutto nelle
    // classi membro anche se è un duplicato
    public static boolean exe(Level world, Player player, Item item) {
        if (canExe(world, player, item)) {
            // LazyOptional<EnergyInterface> myCapability =
            // player.getCapability(MyCapability.INSTANCE);

            // if (myCapability.isPresent())
            // System.out.println("Exiting : " + myCapability.isPresent());
            // System.out.println("---------------- " +
            // MyCapability.INSTANCE.isRegistered());

            Teleport tp = new Teleport(world, player, 50);
            if (tp.run()) {
                CoolDown.addCoolDown(player, item, 50);
                Sound.playSound(world, player, SoundEvents.ENDERMAN_TELEPORT);

                return true;
            }
        }
        return false;
    }

    public static boolean canExe(Level world, Player player, Item item) {
        return !player.getCooldowns().isOnCooldown(item);
    }

}