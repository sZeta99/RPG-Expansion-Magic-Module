package com.noir.rpg_exp_mg;

import com.noir.rpg_exp_mg.items.ModItems;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("rpg_exp_mg")
public class RpgExpansionMagicModuleMod {

    public static final String MOD_ID = "rpg_exp_mg";

    public RpgExpansionMagicModuleMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(eventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }

    /*
     * 
     * 
     * 
     * 
     * 
     * EnergyCapability backend = new
     * EnergyCapability((event.getObject()).capability);
     * LazyOptional<IEnergyStorage> optionalStorage = LazyOptional.of(() ->
     * backend);
     * 
     * ICapabilityProvider provider = new ICapabilityProvider() {
     * 
     * @Override
     * public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable
     * Direction direction) {
     * if (cap == CapabilityEnergy.ENERGY) {
     * return optionalStorage.cast();
     * }
     * return LazyOptional.empty();
     * }
     * };
     * 
     * event.addCapability(new ResourceLocation("examplemod", "fe_compatibility"),
     * provider);
     * }
     */

}
