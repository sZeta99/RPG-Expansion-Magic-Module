package com.noir.rpg_exp_mg.networking;

import com.noir.rpg_exp_mg.RpgExpantionMagicModule;
import com.noir.rpg_exp_mg.networking.packet.DrinkWaterC2SPacket;
import com.noir.rpg_exp_mg.networking.packet.ExampleC2SPacket;
import com.noir.rpg_exp_mg.networking.packet.TeleportC2SPacket;
import com.noir.rpg_exp_mg.networking.packet.ThirstDataSyncS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(RpgExpantionMagicModule.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(ExampleC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ExampleC2SPacket::new)
                .encoder(ExampleC2SPacket::toBytes)
                .consumerMainThread(ExampleC2SPacket::handle)
                .add();

        net.messageBuilder(DrinkWaterC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(DrinkWaterC2SPacket::new)
                .encoder(DrinkWaterC2SPacket::toBytes)
                .consumerMainThread(DrinkWaterC2SPacket::handle)
                .add();
        net.messageBuilder(TeleportC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(TeleportC2SPacket::new)
                .encoder(TeleportC2SPacket::toBytes)
                .consumerMainThread(TeleportC2SPacket::handle)
                .add();
        net.messageBuilder(ThirstDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ThirstDataSyncS2CPacket::new)
                .encoder(ThirstDataSyncS2CPacket::toBytes)
                .consumerMainThread(ThirstDataSyncS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
