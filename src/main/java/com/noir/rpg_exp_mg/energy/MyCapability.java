package com.noir.rpg_exp_mg.energy;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MyCapability {

    public static final Capability<EnergyInterface> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {
    });

    @SubscribeEvent
    public static void register(RegisterCapabilitiesEvent event) {
        // System.out.println("Called register");
        event.register(EnergyInterface.class);
    }

    public MyCapability() {
    }
}