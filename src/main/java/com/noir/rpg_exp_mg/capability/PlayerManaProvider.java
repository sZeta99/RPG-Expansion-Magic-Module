package com.noir.rpg_exp_mg.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerManaProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerMana> PLAYER_MANA = CapabilityManager.get(new CapabilityToken<PlayerMana>() {
    });

    private PlayerMana thirst = null;
    private final LazyOptional<PlayerMana> optional = LazyOptional.of(this::createPlayerThirst);

    private PlayerMana createPlayerThirst() {
        if (this.thirst == null) {
            this.thirst = new PlayerMana();
        }

        return this.thirst;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_MANA) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerThirst().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerThirst().loadNBTData(nbt);
    }
}
