package com.noir.rpg_exp_mg.item;

import com.noir.rpg_exp_mg.RpgExpantionMagicModule;
import com.noir.rpg_exp_mg.block.ModBlocks;
import com.noir.rpg_exp_mg.item.custom.EightBallItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
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

        public static final RegistryObject<Item> EIGHT_BALL = ITEMS.register("eight_ball",
                        () -> new EightBallItem(
                                        new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB).stacksTo(1)));

        public static final RegistryObject<Item> BLUEBERRY_SEEDS = ITEMS.register("blueberry_seeds",
                        () -> new ItemNameBlockItem(ModBlocks.BLUEBERRY_CROP.get(),
                                        new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

        public static final RegistryObject<Item> BLUEBERRY = ITEMS.register("blueberry",
                        () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)
                                        .food(new FoodProperties.Builder().nutrition(2).saturationMod(2f).build())));

        public static void register(IEventBus eventBus) {
                ITEMS.register(eventBus);
        }
}
