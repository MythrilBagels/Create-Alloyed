package com.molybdenum.alloyed.blocks;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.items.ModItemGroup;
import com.molybdenum.alloyed.util.RegistryUtils;
import com.molybdenum.alloyed.util.ModTags;
import com.simibubi.create.Create;
import com.simibubi.create.content.AllSections;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.ModelGen;
import com.simibubi.create.foundation.worldgen.OxidizingBlock;
import com.simibubi.create.repack.registrate.util.entry.BlockEntry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class ModBlocks {
    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate().itemGroup(() -> ModItemGroup.MAIN_GROUP);

    // Block Entries

    public static final BlockEntry<OxidizingBlock> BRONZE_BLOCK = REGISTRATE
            .block("bronze_block", p -> new OxidizingBlock(p, 1 / 16f))
            .initialProperties(Material.METAL)
            .properties(properties -> properties
                    .harvestTool(ToolType.PICKAXE)
                    .harvestLevel(1)
                    .requiresCorrectToolForDrops()
                    .strength(5f))
            .item()
            .tag(ModTags.Items.BRONZE_BLOCK)
            .transform(ModelGen.oxidizedItemModel())
            .transform(RegistryUtils.oxidizedBronzeBlockstate())
            .tag(ModTags.Blocks.BRONZE_BLOCK)
            .defaultLoot()
            .lang("Block of Bronze")
            .register();

    public static final BlockEntry<Block> STEEL_BLOCK = REGISTRATE
            .block("steel_block", p -> new Block(p))
            .initialProperties(Material.METAL)
            .properties(properties -> properties
                    .harvestTool(ToolType.PICKAXE)
                    .harvestLevel(1)
                    .requiresCorrectToolForDrops()
                    .strength(5f))
		    .simpleItem()
            .defaultBlockstate()
            .transform(RegistryUtils.tagBlockAndItem(ModTags.Blocks.STEEL_BLOCK, ModTags.Items.STEEL_BLOCK))
            .defaultLoot()
            .lang("Block of Steel")
		    .register();

    public static final BlockEntry<Block> BRONZE_BELL = REGISTRATE
            .block("bronze_bell", p -> new Block(p))
            .initialProperties(Material.METAL)
            .properties(properties -> properties
                    .noOcclusion()
                    .sound(SoundType.ANVIL)
                    .harvestTool(ToolType.PICKAXE)
                    .harvestLevel(1)
                    .requiresCorrectToolForDrops()
                    .strength(5f))
            .simpleItem()
            .transform(RegistryUtils.existingModel())
            .defaultLoot()
            .lang("Bronze Bell")
            .register();

    // End Block Entries

    public ModBlocks() {}

    public static void register() {
        Create.registrate().addToSection(BRONZE_BLOCK,AllSections.MATERIALS);
        Create.registrate().addToSection(STEEL_BLOCK, AllSections.MATERIALS);
        Create.registrate().addToSection(BRONZE_BELL, AllSections.CURIOSITIES);
    }
}