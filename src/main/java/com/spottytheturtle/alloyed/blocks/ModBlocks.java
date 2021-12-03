package com.spottytheturtle.alloyed.blocks;

import com.spottytheturtle.alloyed.alloyed;
import com.spottytheturtle.alloyed.items.ModItemGroup;
import com.spottytheturtle.alloyed.items.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, alloyed.MOD_ID);



    public static final RegistryObject<Block> BRONZE_BLOCK = registerBlock("bronze_block",
            () -> new Block(AbstractBlock.Properties.of(Material.METAL).harvestLevel(3)
                    .harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(5f)));

    public static final RegistryObject<Block> STEEL_BLOCK = registerBlock("steel_block",
            () -> new Block(AbstractBlock.Properties.of(Material.METAL).harvestLevel(4)
                    .harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(6f)));



    //public static final RegistryObject<Block> LIGHTNING_CHANNELER = registerBlock("lightning_channeler",
    //        () -> new LightningChannelerBlock(AbstractBlock.Properties.create(Material.IRON)));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);

        registerBlockItem(name, toReturn);

        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModItemGroup.TEST_GROUP)));
    }



    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
