package com.noir.rpg_exp_mg.spell.schools.arcane.spells;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import com.noir.rpg_exp_mg.spell.ASpell;
import com.noir.rpg_exp_mg.spell.preset.Teleport;
import com.noir.rpg_exp_mg.custom.tool.Sound;
import com.noir.rpg_exp_mg.energy.PlayerManaProvider;

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
        boolean result = false;
        if (canExe(world, player, item)) {
        boolean capPresent = player.getCapability(PlayerManaProvider.PLAYER_MANA_CAPABILITY).isPresent();
        if (!capPresent) {
            System.out.println("Cap not present");
            return false;
        }

            player.getCapability(PlayerManaProvider.PLAYER_MANA_CAPABILITY).ifPresent(mana -> {
                if (mana.getMana() > 0) { // Once Every 10 Seconds
                                          // on Avg
                    mana.subMana(1);
                    System.out.println("Mana: " + mana.getMana());
                }
            });
        return result;
    }

    public static boolean canExe(Level world, Player player, Item item) {
        return !player.getCooldowns().isOnCooldown(item);
    }

}