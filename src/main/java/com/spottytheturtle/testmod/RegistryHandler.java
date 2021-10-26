package com.spottytheturtle.testmod;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {
    // create DeferredRegister object

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod.MOD_ID);



    public static void init() {
        // attach DeferredRegister to the event bus
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    // register block
    //public static final RegistryObject<Block> TEST_BLOCK = BLOCKS.register("test_block", () -> new Block(Block.Properties.of(Material.STONE).tab(ItemGroup.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Block> TEST_BLOCK = BLOCKS.register("test_block",
            () -> new Block(Block.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Item> TEST_BLOCK_ITEM = ITEMS.register("test_block",
            () -> new BlockItem(TEST_BLOCK.get(), new Item.Properties().tab(TestMod.TEST_GROUP)));




    public static final RegistryObject<Block> TEST_BLOCK_2 = BLOCKS.register("test_block_2",
            () -> new Block(Block.Properties.of(Material.DIRT).harvestTool(ToolType.SHOVEL)));

    public static final RegistryObject<Item> TEST_BLOCK_ITEM_2 = ITEMS.register("test_block_2",
            () -> new BlockItem(TEST_BLOCK_2.get(), new Item.Properties().tab(TestMod.TEST_GROUP)));



    // register item
    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item", () -> new Item(new Item.Properties().tab(TestMod.TEST_GROUP)));
    public static final RegistryObject<Item> TEST_ITEM_2 = ITEMS.register("test_item_2", () -> new Item(new Item.Properties().tab(TestMod.TEST_GROUP)));

}
