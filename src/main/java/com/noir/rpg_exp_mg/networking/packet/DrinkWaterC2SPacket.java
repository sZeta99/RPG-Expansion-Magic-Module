package com.noir.rpg_exp_mg.networking.packet;

import com.noir.rpg_exp_mg.energy.PlayerManaProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DrinkWaterC2SPacket {
    private static final String MESSAGE_DRINK_WATER = "message.tutorialmod.drink_water";
    private static final String MESSAGE_NO_WATER = "message.tutorialmod.no_water";

    public DrinkWaterC2SPacket() {

    }

    public DrinkWaterC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            if (hasWaterAroundThem(player, level, 2)) {
                // Notify the player that water has been drunk
                player.sendMessage(Component.nullToEmpty("You drank some water!"), player.getUUID());
                // play the drinking sound
                level.playSound(null, player.getOnPos(), SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS,
                        0.5F, level.random.nextFloat() * 0.1F + 0.9F);

                // increase the water level / Mana level of player
                player.getCapability(PlayerManaProvider.PLAYER_MANA_CAPABILITY).ifPresent(mana -> {
                    mana.addMana(1);
                    player.sendMessage(Component.nullToEmpty("Current Mana " + mana.getMana()), player.getUUID());
                });

                // Output the current Mana level

            } else {
                // Notify the player that there is no water around!
                player.sendMessage(Component.nullToEmpty("There is no water around!"), player.getUUID());
                // Output the current Mana level
                player.getCapability(PlayerManaProvider.PLAYER_MANA_CAPABILITY).ifPresent(mana -> {
                    player.sendMessage(Component.nullToEmpty("Current Mana " + mana.getMana()), player.getUUID());
                });
            }
        });
        return true;
    }

    private boolean hasWaterAroundThem(ServerPlayer player, ServerLevel level, int size) {
        return level.getBlockStates(player.getBoundingBox().inflate(size))
                .filter(state -> state.is(Blocks.WATER)).toArray().length > 0;
    }
}
