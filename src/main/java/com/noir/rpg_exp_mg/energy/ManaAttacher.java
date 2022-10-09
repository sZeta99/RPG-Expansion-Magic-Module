package com.noir.rpg_exp_mg.energy;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import com.noir.rpg_exp_mg.RpgExpansionMagicModuleMod;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = RpgExpansionMagicModuleMod.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class ManaAttacher {

    public class ManaProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

        public static final ResourceLocation IDENTIFIER = new ResourceLocation(RpgExpansionMagicModuleMod.MOD_ID,
                "myCap");

        private final EnergyInterface backend = new ManaImplementation();
        private final LazyOptional<EnergyInterface> optionalData = LazyOptional.of(() -> backend);

        @NotNull
        @Override
        public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            return MyCapability.INSTANCE.orEmpty(cap, this.optionalData);
        }

        /*
         * void invalidate() {
         * this.optionalData.invalidate();
         * }
         */

        @Override
        public CompoundTag serializeNBT() {
            return this.backend.serializeNBT();
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            this.backend.deserializeNBT(nbt);
        }
    }

    @SubscribeEvent
    public static void attach(final AttachCapabilitiesEvent<Entity> event) {
        // System.out.println("attach called");
        if (event.getObject() instanceof Player) {
            // ManaProvider provider = (new ManaAttacher()).new ManaProvider();
            System.out.println("attach called ON pLAYER");

            // event.addCapability(ManaProvider.IDENTIFIER, manaProvider);
            // System.out.println("Capability Attached : " + ManaProvider.IDENTIFIER);
        }
    }

    public ManaAttacher() {
    }
}