package com.noir.rpg_exp_mg.items;

import com.noir.rpg_exp_mg.RpgExpansionMagicModuleMod;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            RpgExpansionMagicModuleMod.MOD_ID);

    public static final RegistryObject<Item> SCROLL = ITEMS.register("scroll",
            () -> new Scroll(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
