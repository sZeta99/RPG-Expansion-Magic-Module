package com.noir.rpg_exp_mg.energy;

import net.minecraft.nbt.CompoundTag;

public class PlayerMana {

    private final int MAX_MANA = 100;
    private final int MIN_MANA = 0;
    private int mana;

    public int getMana() {
        return mana;
    }

    // ? Da risorse usano questa interfaccia, verificare che abbia senso (es. se è
    // ? quella usata da minecraft)
    public void addMana(int manaToAdd) {
        manaToAdd = Math.abs(manaToAdd);
        this.mana = Math.min(this.mana + manaToAdd, MAX_MANA);
    }

    public void subMana(int manaToAdd) {
        manaToAdd = Math.abs(manaToAdd);
        this.mana = Math.min(this.mana - manaToAdd, MIN_MANA);
    }

    public void copyMana(PlayerMana playerMana) {
        this.mana = playerMana.getMana();
    }

    public void saveNBTDate(CompoundTag nbt) {
        nbt.putInt("mana", this.mana);
    }

    public void loadNBTDate(CompoundTag nbt) {
        this.mana = nbt.getInt("mana");
    }

}
