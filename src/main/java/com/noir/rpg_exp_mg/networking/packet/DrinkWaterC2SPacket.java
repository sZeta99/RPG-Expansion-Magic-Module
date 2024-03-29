package com.noir.rpg_exp_mg.networking.packet;

import com.noir.rpg_exp_mg.capability.PlayerManaProvider;
import com.noir.rpg_exp_mg.networking.ModMessages;

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

    private static final String MESSAGE_DRINK_WATER = "message.rpg_exp_mg.drink_water";
    private static final String MESSAGE_NO_WATER = "message.rpg_exp_mg.no_water";

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
                player.sendSystemMessage(
                        Component.translatable(MESSAGE_DRINK_WATER).withStyle(ChatFormatting.DARK_AQUA));
                // play the drinking sound
                level.playSound(null, player.getOnPos(), SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS,
                        0.5F, level.random.nextFloat() * 0.1F + 0.9F);

                // increase the water level / thirst level of player
                // Output the current thirst level
                player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                    mana.addMana(1);
                    player.sendSystemMessage(Component.literal("Current Thirst " + mana.getMana())
                            .withStyle(ChatFormatting.AQUA));
                    ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(mana.getMana()), player);
                });

            } else {
                // Notify the player that there is no water around!
                player.sendSystemMessage(Component.translatable(MESSAGE_NO_WATER).withStyle(ChatFormatting.RED));
                // Output the current thirst level
                player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                    player.sendSystemMessage(Component.literal("Current Thirst " + mana.getMana())
                            .withStyle(ChatFormatting.AQUA));
                    ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(mana.getMana()), player);
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
