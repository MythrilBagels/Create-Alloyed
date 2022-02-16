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
import net.minecraft.data.ShapedRecipeBuilder;

import static com.molybdenum.alloyed.data.util.RecipeUtils.*;

public class ModBlocks {
    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate().itemGroup(() -> ModItemGroups.MAIN_GROUP);

    // Materials
    static { REGISTRATE.startSection(AllSections.MATERIALS); }

    public static final BlockEntry<OxidizingBlock> BRONZE_BLOCK = REGISTRATE
            .block("bronze_block", p -> new OxidizingBlock(p, 1 / 16f))
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .blockstate(BlockStateUtils::oxidizedBronzeBlockstate)
            .item()
            .tag(ModTags.Items.BRONZE_BLOCK)
            .transform(ModelGen.oxidizedItemModel())
            .tag(ModTags.Blocks.BRONZE_BLOCK)
            .recipe(Crafting.metalBlockRecipe(ModTags.Items.BRONZE_INGOT, "bronze_ingot"))
            .lang("Block of Bronze")
            .register();

    public static final BlockEntry<Block> STEEL_BLOCK = REGISTRATE
            .block("steel_block", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .transform(DataUtils.tagBlockAndItem(
                    ModTags.Blocks.STEEL_BLOCK,
                    ModTags.Items.STEEL_BLOCK
            ))
            .recipe(Crafting.metalBlockRecipe(ModTags.Items.STEEL_INGOT, "steel_ingot"))
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
            .blockstate(BlockStateUtils::existingModel)
            .transform(DataUtils.tagBlockAndItem(
                    ModTags.Blocks.BRONZE_INSTRUMENTS,
                    ModTags.Items.BRONZE_INSTRUMENTS
            ))
            .recipe((ctx, prov) -> {
                ShapedRecipeBuilder.shaped(ctx.get())
                        .pattern("#")
                        .pattern("-")
                        .define('#', ModTags.Items.BRONZE_BLOCK)
                        .define('-', ModTags.Items.BRONZE_SHEET)
                        .unlockedBy("has_bronze_ingot", Criterion.has(ModTags.Items.BRONZE_INGOT))
                        .save(prov, Alloyed.asResource("crafting/" + ctx.getName()));
            })
            .lang("Bronze Bell")
            .register();

    public static void register() { Alloyed.LOGGER.info("Registering ModBlocks!"); }
}