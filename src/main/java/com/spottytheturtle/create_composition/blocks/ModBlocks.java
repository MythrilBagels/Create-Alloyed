package com.spottytheturtle.create_composition.blocks;

import com.spottytheturtle.create_composition.create_composition;
import com.spottytheturtle.create_composition.items.ModItemGroup;
import com.spottytheturtle.create_composition.items.ModItems;
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

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, create_composition.MOD_ID);



    public static final RegistryObject<Block> BRONZE_BLOCK = registerBlock("bronze_block",
            () -> new Block(AbstractBlock.Properties.create(Material.IRON).harvestLevel(3)
                    .harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(5f)));

    public static final RegistryObject<Block> STEEL_BLOCK = registerBlock("steel_block",
            () -> new Block(AbstractBlock.Properties.create(Material.IRON).harvestLevel(4)
                    .harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(6f)));

    public static final RegistryObject<Block> TIN_BLOCK = registerBlock("tin_block",
            () -> new Block(AbstractBlock.Properties.create(Material.IRON).harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(4f)));

    public static final RegistryObject<Block> TIN_ORE = registerBlock("tin_ore",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(3f)));



    //public static final RegistryObject<Block> LIGHTNING_CHANNELER = registerBlock("lightning_channeler",
    //        () -> new LightningChannelerBlock(AbstractBlock.Properties.create(Material.IRON)));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);

        registerBlockItem(name, toReturn);

        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(ModItemGroup.TEST_GROUP)));
    }



    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
