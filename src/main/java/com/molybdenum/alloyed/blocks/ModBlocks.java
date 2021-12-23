package com.molybdenum.alloyed.blocks;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.items.ModItemGroup;
import com.simibubi.create.Create;
import com.simibubi.create.content.AllSections;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.ModelGen;
import com.simibubi.create.foundation.worldgen.OxidizingBlock;
import com.simibubi.create.repack.registrate.builders.BlockBuilder;
import com.simibubi.create.repack.registrate.builders.ItemBuilder;
import com.simibubi.create.repack.registrate.util.entry.BlockEntry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;

import static com.simibubi.create.AllTags.tagBlockAndItem;

/* Leaving the main class in here for safe keeping
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Alloyed.MOD_ID);


    // Bronze
    public static final RegistryObject<Block> BRONZE_BLOCK = registerBlock("bronze_block",
            () -> new Block(AbstractBlock.Properties
                    .of(Material.METAL)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .strength(5f)));

    public static final RegistryObject<Block> BRONZE_BELL = registerBlock("bronze_bell",
            () -> new Block(AbstractBlock.Properties
                    .of(Material.METAL)
                    .harvestLevel(1)
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .strength(3f)
                    .sound(SoundType.ANVIL)
                    .noOcclusion()));

    // Steel
    public static final RegistryObject<Block> STEEL_BLOCK = registerBlock("steel_block",
            () -> new Block(AbstractBlock.Properties
                    .of(Material.METAL)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
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
*/

public class ModBlocks {
    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate().itemGroup(() -> {
        return ModItemGroup.MAIN_GROUP;
    });

    // Block Entries

    public static final BlockEntry<OxidizingBlock> BRONZE_BLOCK = REGISTRATE
            .block("bronze_block", p -> new OxidizingBlock(p, 1 / 16f))
            .initialProperties(ModBlocks::hardMetal)
            .item()
            .transform(ModelGen.oxidizedItemModel())
            .transform(BlockStateGen.oxidizedBlockstate())
            .lang("Block of Bronze")
            .register();

    public static final BlockEntry<Block> STEEL_BLOCK = REGISTRATE
            .block("steel_block", p -> new Block(p))
            .initialProperties(() -> Blocks.IRON_BLOCK)
		    .simpleItem()
            .lang("Block of Steel")
		    .register();

    public static final BlockEntry<Block> BRONZE_BELL = REGISTRATE
            .block("bronze_bell", p -> new Block(p))
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(properties -> properties
                    .noOcclusion()
                    .sound(SoundType.ANVIL))
            .simpleItem()
            .lang("Bronze Bell")
            .register();

    // End Block Entries

    public ModBlocks() {}

    public static void register() {
        Create.registrate().addToSection(BRONZE_BLOCK,AllSections.MATERIALS);
        Create.registrate().addToSection(STEEL_BLOCK, AllSections.MATERIALS);
        Create.registrate().addToSection(BRONZE_BELL, AllSections.CURIOSITIES);
    }

    @Nonnull
    public static Block hardMetal() {
        return Blocks.IRON_BLOCK;
    } // Neatness' sake
}