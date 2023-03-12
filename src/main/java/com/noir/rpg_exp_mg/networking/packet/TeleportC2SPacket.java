package com.noir.rpg_exp_mg.networking.packet;

import java.util.function.Supplier;

import com.noir.rpg_exp_mg.capability.PlayerManaProvider;
import com.noir.rpg_exp_mg.custom.tool.RayCast;
import com.noir.rpg_exp_mg.networking.ModMessages;
import com.noir.rpg_exp_mg.spell.preset.Teleport;

import ca.weblite.objc.Message;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.ClipContext;

public class TeleportC2SPacket {

    public TeleportC2SPacket() {

    }

    public TeleportC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            BlockPos BlockHitResult = RayCast.getPositionByLook(level, player, ClipContext.Fluid.NONE, 50);
            player.moveTo(BlockHitResult.getX(), BlockHitResult.getY(), BlockHitResult.getZ());
        });
        return true;
    }

}
