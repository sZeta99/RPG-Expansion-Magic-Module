package com.noir.rpg_exp_mg.energy;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface EnergyInterface extends INBTSerializable<CompoundTag> {

    int getValue();

    void setMyValue(int mana);
}