package com.noir.rpg_exp_mg.item;

import com.noir.rpg_exp_mg.RpgExpantionMagicModule;
import com.noir.rpg_exp_mg.spell.schools.arcane.spells.ArcaneTeleport;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
                        RpgExpantionMagicModule.MOD_ID);

        public static final RegistryObject<Item> ZIRCON = ITEMS.register("zircon",
                        () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));
        public static final RegistryObject<Item> RAW_ZIRCON = ITEMS.register("raw_zircon",
                        () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));
        public static final RegistryObject<Item> ARCANE_TELEPORT = ITEMS.register("arcane_teleport",
                        () -> new ArcaneTeleport(
                                        new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB).durability(1)));

        public static void register(IEventBus eventBus) {
                ITEMS.register(eventBus);
        }
}
