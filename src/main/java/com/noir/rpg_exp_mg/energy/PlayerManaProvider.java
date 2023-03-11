package com.noir.rpg_exp_mg.energy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerManaProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerMana> PLAYER_MANA_CAPABILITY = CapabilityManager
            .get(new CapabilityToken<PlayerMana>() {
            });

    private PlayerMana playerMana = null;
    private final LazyOptional<PlayerMana> playerManaOptional = LazyOptional.of(this::createPlayerMana);

    private PlayerMana createPlayerMana() {
        if (playerMana == null) {
            playerMana = new PlayerMana();
        }
        return this.playerMana;
    }

    @Override
    public CompoundTag serializeNBT() {

        CompoundTag nbt = new CompoundTag();
        createPlayerMana().saveNBTDate(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {

        createPlayerMana().loadNBTDate(nbt);
    }

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_MANA_CAPABILITY) {
            return playerManaOptional.cast();
        }
        return LazyOptional.empty();
    }

}
