package com.noir.rpg_exp_mg.event;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

import net.minecraft.world.entity.player.Player;

import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import com.noir.rpg_exp_mg.RpgExpansionMagicModuleMod;
import com.noir.rpg_exp_mg.energy.PlayerMana;
import com.noir.rpg_exp_mg.energy.PlayerManaProvider;

@Mod.EventBusSubscriber(modid = RpgExpansionMagicModuleMod.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(PlayerManaProvider.PLAYER_MANA_CAPABILITY).isPresent()) {
                event.addCapability(new ResourceLocation(
                        RpgExpansionMagicModuleMod.MOD_ID, "properties"), new PlayerManaProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerManaProvider.PLAYER_MANA_CAPABILITY).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerManaProvider.PLAYER_MANA_CAPABILITY).ifPresent(newStore -> {
                    newStore.copyMana(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerMana.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
            event.player.getCapability(PlayerManaProvider.PLAYER_MANA_CAPABILITY).ifPresent(mana -> {
                if (mana.getMana() > 0 && event.player.getRandom().nextFloat() < 0.005f) { // Once Every 10 Seconds
                                                                                           // on Avg
                    mana.subMana(1);
                    System.out.println("Mana: " + mana.getMana());
                    event.player.sendMessage(Component.nullToEmpty("You feel your mana draining..."),
                            event.player.getUUID());
                }
            });
        }
    }
}
