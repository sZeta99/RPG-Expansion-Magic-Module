package com.noir.rpg_exp_mg.energy;

import net.minecraft.core.Direction;
import net.minecraft.nbt.IntTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

public class EnergyCapability implements IEnergyStorage, ICapabilitySerializable<IntTag> {

    PlayerEnergy pe;

    public EnergyCapability() {
        pe = new PlayerEnergy();
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int energy = pe.getEnergy();
        int maxEnergy = pe.getMaxEnergy();

        int em = energy + maxReceive;

        if (em > maxEnergy)
            em = maxEnergy;

        if (simulate)
            return em;

        pe.setEnergy(em);
        return em;

    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int energy = pe.getEnergy();
        if (energy >= maxExtract)
            energy = energy - maxExtract;

        if (simulate)
            return energy;

        pe.setEnergy(energy);
        return energy;
    }

    @Override
    public int getEnergyStored() {
        return pe.getEnergy();
    }

    @Override
    public int getMaxEnergyStored() {

        return pe.getMaxEnergy();
    }

    @Override
    public boolean canExtract() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean canReceive() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {

        return null; // See note after snippet
    }

    @Override
    public IntTag serializeNBT() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deserializeNBT(IntTag nbt) {
        // TODO Auto-generated method stub

    }

}
