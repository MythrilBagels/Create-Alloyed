package com.molybdenum.alloyed.common.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.items.ModItemGroups;
import com.molybdenum.alloyed.data.util.BlockStateUtils;
import com.molybdenum.alloyed.data.util.DataUtils;
import com.simibubi.create.content.AllSections;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.ModelGen;
import com.simibubi.create.foundation.worldgen.OxidizingBlock;
import com.simibubi.create.repack.registrate.util.entry.BlockEntry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;

public class ModBlocks {
    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate().itemGroup(() -> ModItemGroups.MAIN_GROUP);

    // Materials
    static { REGISTRATE.startSection(AllSections.MATERIALS); }

    public static final BlockEntry<OxidizingBlock> BRONZE_BLOCK = REGISTRATE
            .block("bronze_block", p -> new OxidizingBlock(p, 1 / 16f))
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .item()
            .tag(ModTags.Items.BRONZE_BLOCK)
            .transform(ModelGen.oxidizedItemModel())
            .transform(BlockStateUtils.oxidizedBronzeBlockstate())
            .tag(ModTags.Blocks.BRONZE_BLOCK)
            .lang("Block of Bronze")
            .register();

    public static final BlockEntry<Block> STEEL_BLOCK = REGISTRATE
            .block("steel_block", p -> new Block(p))
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .transform(DataUtils.tagBlockAndItem(ModTags.Blocks.STEEL_BLOCK, ModTags.Items.STEEL_BLOCK))
            .simpleItem()
            .lang("Block of Steel")
		    .register();

    // Curiosities
    static { REGISTRATE.startSection(AllSections.CURIOSITIES); }

    public static final BlockEntry<Block> BRONZE_BELL = REGISTRATE
            .block("bronze_bell", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(properties -> properties
                    .noOcclusion()
                    .sound(SoundType.ANVIL))
            .simpleItem()
            .transform(BlockStateUtils.existingModel())
            .lang("Bronze Bell")
            .register();

    // End Block Entries

    public static void register() {}
}