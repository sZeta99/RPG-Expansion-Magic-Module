package com.noir.rpg_exp_mg.spell.schools.arcane.spells;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;

import com.noir.rpg_exp_mg.spell.ASpell;
import com.noir.rpg_exp_mg.spell.preset.Teleport;
import com.noir.rpg_exp_mg.custom.tool.Sound;
import com.noir.rpg_exp_mg.networking.ModMessages;
import com.noir.rpg_exp_mg.networking.packet.DrinkWaterC2SPacket;
import com.noir.rpg_exp_mg.networking.packet.ExampleC2SPacket;
import com.noir.rpg_exp_mg.networking.packet.ThirstDataSyncS2CPacket;
import com.noir.rpg_exp_mg.capability.PlayerMana;
import com.noir.rpg_exp_mg.capability.PlayerManaProvider;
import com.noir.rpg_exp_mg.custom.tool.CoolDown;

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

            Teleport tp = new Teleport(world, player, 50);
            PlayerMana mana = player.getCapability(PlayerManaProvider.PLAYER_MANA)
                    .orElseThrow(NullPointerException::new);
            if (mana.getMana() > 0) { // Once Every 10 Seconds

                ModMessages.sendToServer(new ExampleC2SPacket());
                mana.subMana(1);
                CoolDown.addCoolDown(player, item, 50);
                Sound.playSound(world, player, SoundEvents.ENDERMAN_TELEPORT);
                ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(mana.getMana()),
                        ((ServerPlayer) player));

                result = true;

                System.out.println("Mana: " + mana.getMana());
            }
            result = true;
        }

        return result;
    }

    public static boolean canExe(Level world, Player player, Item item) {
        return !player.getCooldowns().isOnCooldown(item);
    }

}