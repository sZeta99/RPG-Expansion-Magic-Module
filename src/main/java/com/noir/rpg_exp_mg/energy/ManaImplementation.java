package com.noir.rpg_exp_mg.energy;

import net.minecraft.nbt.CompoundTag;

public class ManaImplementation implements EnergyInterface {

    private static final String NBT_KEY_MANA = "manaPlayer";

    private PlayerEnergy mana;

    @Override
    public int getValue() {
        return this.mana.getEnergy();
    }

    @Override
    public void setMyValue(int mana) {
        this.mana.setEnergy(mana);
    }

    // Need impruvment dipending on how many time thi get call
    @Override
    public CompoundTag serializeNBT() {
        final CompoundTag tag = new CompoundTag();
        int[] bytes = new int[3];
        bytes[0] = mana.getEnergy();
        bytes[1] = mana.getMaxEnergy();
        bytes[2] = mana.getRegEnergy();
        System.out.println("Serialize obj");

        tag.putIntArray(NBT_KEY_MANA, bytes);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        int[] bytes = nbt.getIntArray(NBT_KEY_MANA);

        mana.setEnergy(bytes[0]);
        mana.setMaxEnergy(bytes[1]);
        mana.setRegEnergy(bytes[2]);
    }
}