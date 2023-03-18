package com.noir.rpg_exp_mg.block;

import com.noir.rpg_exp_mg.RpgExpantionMagicModule;
import com.noir.rpg_exp_mg.block.custom.MageBench;
import com.noir.rpg_exp_mg.item.ModCreativeModeTab;
import com.noir.rpg_exp_mg.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
                        RpgExpantionMagicModule.MOD_ID);

        public static final RegistryObject<Block> MANA_CRYSTAL_ORE = registerBlock("mana_crystal_ore",
                        () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                                        .strength(6f).requiresCorrectToolForDrops(),
                                        UniformInt.of(3, 7)),
                        ModCreativeModeTab.MAGIC_TAB);
        public static final RegistryObject<Block> DEEPSLATE_MANA_CRYSTAL_ORE = registerBlock(
                        "deepslate_mana_crystal_ore",
                        () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                                        .strength(6f).requiresCorrectToolForDrops(),
                                        UniformInt.of(3, 7)),
                        ModCreativeModeTab.MAGIC_TAB);
        public static final RegistryObject<Block> ENDSTONE_MANA_CRYSTAL_ORE = registerBlock("endstone_mana_crystal_ore",
                        () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                                        .strength(6f).requiresCorrectToolForDrops(),
                                        UniformInt.of(3, 7)),
                        ModCreativeModeTab.MAGIC_TAB);
        public static final RegistryObject<Block> NETHERRACK_MANA_CRYSTAL_ORE = registerBlock(
                        "netherrack_mana_crystal_ore",
                        () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                                        .strength(6f).requiresCorrectToolForDrops(),
                                        UniformInt.of(3, 7)),
                        ModCreativeModeTab.MAGIC_TAB);

        public static final RegistryObject<Block> MAGE_BENCH = registerBlock("mage_bench",
                        () -> new MageBench(BlockBehaviour.Properties.of(Material.STONE)
                                        .strength(6f).requiresCorrectToolForDrops()),
                        ModCreativeModeTab.MAGIC_TAB);

        private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block,
                        CreativeModeTab tab) {
                RegistryObject<T> toReturn = BLOCKS.register(name, block);
                registerBlockItem(name, toReturn, tab);
                return toReturn;
        }

        private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                        CreativeModeTab tab) {
                return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
        }

        public static void register(IEventBus eventBus) {
                BLOCKS.register(eventBus);
        }
}
