package com.noir.rpg_exp_mg.energy;

public class PlayerEnergy {

    private final int STARTING_ENERGY = 100;
    private final int REGENERATION_ENERGY = 1;

    private int energy;
    private int maxEnergy;
    private int regEnergy;

    public PlayerEnergy() {
        energy = 0;
        maxEnergy = STARTING_ENERGY;
        regEnergy = REGENERATION_ENERGY;
    }

    public int getSTARTING_ENERGY() {
        return this.STARTING_ENERGY;
    }

    public int getREGENERATION_ENERGY() {
        return this.REGENERATION_ENERGY;
    }

    public int getEnergy() {
        return this.energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getRegEnergy() {
        return this.regEnergy;
    }

    public void setRegEnergy(int regEnergy) {
        this.regEnergy = regEnergy;
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
