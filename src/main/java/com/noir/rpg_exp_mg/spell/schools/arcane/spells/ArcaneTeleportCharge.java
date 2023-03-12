package com.noir.rpg_exp_mg.spell.schools.arcane.spells;

import com.noir.rpg_exp_mg.capability.PlayerMana;
import com.noir.rpg_exp_mg.capability.PlayerManaProvider;
import com.noir.rpg_exp_mg.custom.tool.CoolDown;
import com.noir.rpg_exp_mg.custom.tool.Sound;
import com.noir.rpg_exp_mg.networking.ModMessages;
import com.noir.rpg_exp_mg.networking.packet.TeleportC2SPacket;
import com.noir.rpg_exp_mg.networking.packet.ThirstDataSyncS2CPacket;
import com.noir.rpg_exp_mg.spell.ASpell;
import com.noir.rpg_exp_mg.spell.ISpell;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
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

        if (time > 9000) {

            PlayerMana mana = player.getCapability(PlayerManaProvider.PLAYER_MANA)
                    .orElseThrow(NullPointerException::new);
            if (mana.getMana() > 0) { // Once Every 10 Seconds

                ModMessages.sendToServer(new TeleportC2SPacket());
                mana.subMana(1);
                Sound.playSound(level, player, SoundEvents.ENDERMAN_TELEPORT);
                ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(mana.getMana()),
                        ((ServerPlayer) player));

                father.nextSpell(new ArcaneTeleportInstant(father));

                System.out.println("Mana: " + mana.getMana());
                System.out.println("ArcaneTeleportCharge.onRelese() " + time);
            }
        }

    }

}
