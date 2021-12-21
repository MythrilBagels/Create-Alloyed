package com.molybdenum.alloyed.blocks;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.items.ModItemGroup;
import com.molybdenum.alloyed.items.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Alloyed.MOD_ID);


    // Bronze
    public static final RegistryObject<Block> BRONZE_BLOCK = registerBlock("bronze_block",
            () -> new Block(Block.Properties
                    .of(Material.METAL)
                    //.harvestLevel(2)
                    .requiresCorrectToolForDrops()
                    .strength(5f)));

    public static final RegistryObject<Block> BRONZE_BELL = registerBlock("bronze_bell",
            () -> new Block(Block.Properties
                    .of(Material.METAL)
                    //.harvestLevel(1)
                    //.harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .strength(3f)
                    .sound(SoundType.ANVIL)
                    .noOcclusion()));

    // Steel
    public static final RegistryObject<Block> STEEL_BLOCK = registerBlock("steel_block",
            () -> new Block(Block.Properties
                    .of(Material.METAL)
                    //.harvestLevel(2)
                    .requiresCorrectToolForDrops()
                    .strength(6f)));



    //public static final RegistryObject<Block> LIGHTNING_CHANNELER = registerBlock("lightning_channeler",
    //        () -> new LightningChannelerBlock(AbstractBlock.Properties.create(Material.IRON)));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);

        registerBlockItem(name, toReturn);

        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModItemGroup.MAIN_GROUP)));
    }



    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
