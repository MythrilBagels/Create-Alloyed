package com.molybdenum.alloyed.common.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.items.ModItemGroup;
import com.molybdenum.alloyed.data.registrate.PostRegistrationHelper;
import com.molybdenum.alloyed.data.util.BlockStateUtils;
import com.molybdenum.alloyed.data.util.DataUtils;
import com.molybdenum.alloyed.data.util.LangUtils;
import com.molybdenum.alloyed.data.util.RecipeUtils;
import com.simibubi.create.content.AllSections;
import com.simibubi.create.foundation.block.CopperBlockSet;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.providers.RegistrateRecipeProvider;
import com.simibubi.create.repack.registrate.util.entry.BlockEntry;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;

public class ModBlocks {

    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate().creativeModeTab(() -> ModItemGroup.MAIN_GROUP);

    // Materials
    static { REGISTRATE.startSection(AllSections.MATERIALS); }

    public static final CopperBlockSet BRONZE_BLOCKS = new CopperBlockSet( // Ignore that it says COPPER block set. The code works for any oxidizing metal.
            REGISTRATE,
            "bronze_block",
            "bronze_block",
            new CopperBlockSet.Variant<?>[] { CopperBlockSet.BlockVariant.INSTANCE },
            "bronze/"
    );

    public static final BlockEntry<Block> STEEL_BLOCK = REGISTRATE
            .block("steel_block", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .transform(DataUtils.tagBlockAndItem(
                    ModTags.Blocks.STEEL_BLOCK,
                    ModTags.Items.STEEL_BLOCK
            ))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(BlockTags.NEEDS_STONE_TOOL)
            .recipe(RecipeUtils.Crafting.metalBlockRecipe(ModTags.Items.STEEL_INGOT, "steel_ingot"))
            .lang("Block of Steel")
            .register();

    // Curiosities
    static { REGISTRATE.startSection(AllSections.MATERIALS); }

    public static final BlockEntry<Block> BRONZE_BELL = REGISTRATE
            .block("bronze_bell", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(properties -> properties
                    .noOcclusion()
                    .sound(SoundType.ANVIL))
            .blockstate(BlockStateUtils::existingModel)
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(BlockTags.NEEDS_STONE_TOOL)
            .simpleItem()
            .recipe((ctx, prov) -> {
                ShapedRecipeBuilder.shaped(ctx.get(), 1)
                        .pattern("#")
                        .pattern("-")
                        .define('#', ModTags.Items.BRONZE_BLOCK)
                        .define('-', ModTags.Items.BRONZE_SHEET)
                        .unlockedBy("has_bronze_ingot", RegistrateRecipeProvider.has(ModTags.Items.BRONZE_INGOT))
                        .save(prov, Alloyed.asResource("crafting/" + ctx.getName()));
            })
            .register();

    public static void register() {
        System.out.println("Registering ModBlocks!");
    }

    public static void fixBronzeBlocks() {
        LangUtils.correctOxidizingMetalLang("bronze_block", "Bronze");
        PostRegistrationHelper.addMetalBlockRecipe("bronze_block", ModTags.Items.BRONZE_INGOT, "bronze_ingot", "bronze/");
    }
}
