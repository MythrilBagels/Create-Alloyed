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

public class ModBlocks {
    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate().itemGroup(() -> {
        return ModItemGroup.MAIN_GROUP;
    });

    // Block Entries

    public static final BlockEntry<OxidizingBlock> BRONZE_BLOCK = REGISTRATE
            .block("bronze_block", p -> new OxidizingBlock(p, 1 / 16f))
            .initialProperties(Material.METAL)
            .item()
            .transform(ModelGen.oxidizedItemModel())
            .transform(BlockStateGen.oxidizedBlockstate())
            .lang("Block of Bronze")
            .register();

    public static final BlockEntry<Block> STEEL_BLOCK = REGISTRATE
            .block("steel_block", p -> new Block(p))
            .initialProperties(Material.METAL)
		    .simpleItem()
            .lang("Block of Steel")
		    .register();

    public static final BlockEntry<Block> BRONZE_BELL = REGISTRATE
            .block("bronze_bell", p -> new Block(p))
            .initialProperties(Material.METAL)
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
}