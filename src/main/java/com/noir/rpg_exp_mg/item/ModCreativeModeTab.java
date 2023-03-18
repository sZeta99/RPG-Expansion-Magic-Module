package com.noir.rpg_exp_mg.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab MAGIC_TAB = new CreativeModeTab("rpg_exp_mg") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.MANA_CRYSTAL.get());
        }
    };
}
