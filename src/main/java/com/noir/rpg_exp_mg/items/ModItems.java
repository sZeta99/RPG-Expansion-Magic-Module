package com.noir.rpg_exp_mg.items;

import com.noir.rpg_exp_mg.RpgExpansionMagicModuleMod;
import com.noir.rpg_exp_mg.spell.schools.arcane.ArcaneTeleport;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            RpgExpansionMagicModuleMod.MOD_ID);

    public static final RegistryObject<Item> ARCANE_TELEPORT = ITEMS.register("arcane_teleport",
            () -> new ArcaneTeleport(new Item.Properties().tab(CreativeModeTab.TAB_MISC).durability(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
