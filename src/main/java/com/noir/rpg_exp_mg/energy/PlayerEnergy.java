package com.noir.rpg_exp_mg.energy;

public class PlayerEnergy {

    private final int STARTING_ENERGY = 5;

    private int energy;
    private int maxEnergy;

    public PlayerEnergy() {
        energy = 0;
        maxEnergy = STARTING_ENERGY;
    }

    public int getEnergy() {
        return this.energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getSTARTING_ENERGY() {
        return this.STARTING_ENERGY;
    }

    public int getMaxEnergy() {
        return this.maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public void copyFrom(PlayerEnergy source) {
        this.energy = source.energy;
    }

}
