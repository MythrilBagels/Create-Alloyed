package com.molybdenum.alloyed.blocks;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.items.ModItemGroup;
import com.molybdenum.alloyed.util.AssetUtils;
import com.molybdenum.alloyed.util.DataUtils;
import com.molybdenum.alloyed.util.ModTags;
import com.simibubi.create.Create;
import com.simibubi.create.content.AllSections;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.entry.BlockEntry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class ModBlocks {

    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate().creativeModeTab(() -> ModItemGroup.MAIN_GROUP);

    // Bronze Oxidization Stages
    public static final BlockEntry<WeatheringBronzeFullBlock> BRONZE_BLOCK = REGISTRATE
            .block("bronze_block",
                    properties -> new WeatheringBronzeFullBlock(
                            WeatheringBronze.WeatherState.UNAFFECTED,
                            properties
                    )
            )
            .initialProperties(Material.METAL)
            .properties(BlockBehaviour.Properties::requiresCorrectToolForDrops)
            .transform(DataUtils.tagBlockAndItem(
                    ModTags.Blocks.BRONZE_BLOCK,
                    ModTags.Items.BRONZE_BLOCK
            ))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(BlockTags.NEEDS_STONE_TOOL)
            .defaultBlockstate()
            .defaultLoot()
            .lang("Block of Bronze")
            .register();

    public static final BlockEntry<WeatheringBronzeFullBlock> EXPOSED_BRONZE_BLOCK = REGISTRATE
            .block("exposed_bronze_block",
                    properties -> new WeatheringBronzeFullBlock(
                            WeatheringBronze.WeatherState.EXPOSED,
                            properties
                    )
            )
            .initialProperties(Material.METAL)
            .properties(BlockBehaviour.Properties::requiresCorrectToolForDrops)
            .simpleItem()
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(BlockTags.NEEDS_STONE_TOOL)
            .defaultBlockstate()
            .defaultLoot()
            .lang("Exposed Bronze")
            .register();

    public static final BlockEntry<WeatheringBronzeFullBlock> OXIDIZED_BRONZE_BLOCK = REGISTRATE
            .block("oxidized_bronze_block",
                    properties -> new WeatheringBronzeFullBlock(
                            WeatheringBronze.WeatherState.OXIDIZED,
                            properties
                    )
            )
            .initialProperties(Material.METAL)
            .properties(BlockBehaviour.Properties::requiresCorrectToolForDrops)
            .simpleItem()
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(BlockTags.NEEDS_STONE_TOOL)
            .defaultBlockstate()
            .defaultLoot()
            .lang("Oxidized Bronze")
            .register();

    // Waxed Bronze Oxidization Stages
    public static final BlockEntry<Block> WAXED_BRONZE_BLOCK = REGISTRATE
            .block("waxed_bronze_block", Block::new)
            .initialProperties(Material.METAL)
            .properties(BlockBehaviour.Properties::requiresCorrectToolForDrops)
            .transform(DataUtils.tagBlockAndItem(
                    ModTags.Blocks.BRONZE_BLOCK,
                    ModTags.Items.BRONZE_BLOCK
            ))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(BlockTags.NEEDS_STONE_TOOL)
            .transform(AssetUtils.copyModel("bronze_block"))
            .defaultLoot()
            .lang("Waxed Block of Bronze")
            .register();

    public static final BlockEntry<Block> WAXED_EXPOSED_BRONZE_BLOCK = REGISTRATE
            .block("waxed_exposed_bronze_block",Block::new)
            .initialProperties(Material.METAL)
            .properties(BlockBehaviour.Properties::requiresCorrectToolForDrops)
            .simpleItem()
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(BlockTags.NEEDS_STONE_TOOL)
            .transform(AssetUtils.copyModel("exposed_bronze_block"))
            .defaultLoot()
            .lang("Waxed Exposed Bronze")
            .register();

    public static final BlockEntry<Block> WAXED_OXIDIZED_BRONZE_BLOCK = REGISTRATE
            .block("waxed_oxidized_bronze_block",Block::new)
            .initialProperties(Material.METAL)
            .properties(BlockBehaviour.Properties::requiresCorrectToolForDrops)
            .simpleItem()
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(BlockTags.NEEDS_STONE_TOOL)
            .transform(AssetUtils.copyModel("oxidized_bronze_block"))
            .defaultLoot()
            .lang("Waxed Oxidized Bronze")
            .register();

    // Bronze instruments
    public static final BlockEntry<Block> BRONZE_BELL = REGISTRATE
            .block("bronze_bell", Block::new)
            .initialProperties(Material.METAL)
            .properties(properties -> properties
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.ANVIL))
            .simpleItem()
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(BlockTags.NEEDS_STONE_TOOL)
            .transform(AssetUtils.existingModel())
            .defaultLoot()
            .defaultLang()
            .register();

    // Steel
    public static final BlockEntry<Block> STEEL_BLOCK = REGISTRATE
            .block("steel_block", Block::new)
            .initialProperties(Material.METAL)
            .properties(BlockBehaviour.Properties::requiresCorrectToolForDrops)
            .transform(DataUtils.tagBlockAndItem(
                    ModTags.Blocks.STEEL_BLOCK,
                    ModTags.Items.STEEL_BLOCK
            ))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(BlockTags.NEEDS_STONE_TOOL)
            .defaultBlockstate()
            .defaultLoot()
            .lang("Block of Steel")
            .register();

    public static void register() {
        Create.registrate().addToSection(BRONZE_BLOCK, AllSections.MATERIALS);
        Create.registrate().addToSection(EXPOSED_BRONZE_BLOCK, AllSections.MATERIALS);
        Create.registrate().addToSection(OXIDIZED_BRONZE_BLOCK, AllSections.MATERIALS);

        Create.registrate().addToSection(WAXED_BRONZE_BLOCK, AllSections.MATERIALS);
        Create.registrate().addToSection(WAXED_EXPOSED_BRONZE_BLOCK, AllSections.MATERIALS);
        Create.registrate().addToSection(WAXED_OXIDIZED_BRONZE_BLOCK, AllSections.MATERIALS);

        Create.registrate().addToSection(STEEL_BLOCK, AllSections.MATERIALS);
        Create.registrate().addToSection(BRONZE_BELL, AllSections.CURIOSITIES);
    }
}
