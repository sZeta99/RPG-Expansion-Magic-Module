package com.noir.rpg_exp_mg.energy;

import com.noir.rpg_exp_mg.RpgExpansionMagicModuleMod;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = RpgExpansionMagicModuleMod.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class MyCapability {

    public static final Capability<EnergyInterface> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {
    });

    @SubscribeEvent
    public static void register(RegisterCapabilitiesEvent event) {
        System.out.println("rg rg");
        event.register(EnergyInterface.class);
    }

    public MyCapability() {
    }
}