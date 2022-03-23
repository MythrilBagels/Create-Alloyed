package com.molybdenum.alloyed.common.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.compat.createdeco.connected.SteelSheetMetalCTBehaviour;
import com.molybdenum.alloyed.common.compat.createdeco.connected.SteelSheetSlabCTBehaviour;
import com.molybdenum.alloyed.common.items.ModItemGroups;
import com.molybdenum.alloyed.data.util.BlockStateUtils;
import com.molybdenum.alloyed.data.util.DataUtils;
import com.molybdenum.alloyed.data.util.ModelUtils;
import com.molybdenum.alloyed.data.util.RecipeUtils;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.AllSections;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.ModelGen;
import com.simibubi.create.foundation.worldgen.OxidizingBlock;
import com.simibubi.create.repack.registrate.providers.RegistrateRecipeProvider;
import com.simibubi.create.repack.registrate.util.entry.BlockEntry;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.ToolType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;

import static com.molybdenum.alloyed.data.util.RecipeUtils.Crafting;
import static com.molybdenum.alloyed.data.util.RecipeUtils.Criterion;

public class ModBlocks {
    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate().itemGroup(() -> ModItemGroups.MAIN_GROUP);

    // BRONZE
    public static final BlockEntry<OxidizingBlock> BRONZE_BLOCK = REGISTRATE
            .block("bronze_block", p -> new OxidizingBlock(p, 1 / 16f))
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .blockstate(BlockStateUtils.Unique::oxidizedBronzeBlockstate)
            .item()
            .tag(ModTags.Items.BRONZE_BLOCK)
            .transform(ModelGen.oxidizedItemModel())
            .tag(ModTags.Blocks.BRONZE_BLOCK)
            .recipe(Crafting.metalBlockRecipe(ModTags.Items.BRONZE_INGOT))
            .lang("Block of Bronze")
            .register();

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
            .recipe((ctx, prov) -> ShapedRecipeBuilder.shaped(ctx.get())
                    .pattern("#")
                    .pattern("-")
                    .define('#', ModTags.Items.BRONZE_BLOCK)
                    .define('-', ModTags.Items.BRONZE_SHEET)
                    .unlockedBy("has_bronze_ingot", Criterion.has(ModTags.Items.BRONZE_INGOT))
                    .save(prov, Alloyed.asResource("crafting/" + ctx.getName())))
            .lang("Bronze Bell")
            .register();



    // STEEL
    public static final BlockEntry<Block> STEEL_BLOCK = REGISTRATE
            .block("steel_block", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(ModBlocks::steelProperties)
            .transform(DataUtils.tagBlockAndItem(
                    ModTags.Blocks.STEEL_BLOCK,
                    ModTags.Items.STEEL_BLOCK
            ))
            .recipe(Crafting.metalBlockRecipe(ModTags.Items.STEEL_INGOT))
            .lang("Block of Steel")
		    .register();

    public static final BlockEntry<DoorBlock> STEEL_DOOR =
            steelDoorBlock("steel_door", "Steel Door", false, null);

    public static final BlockEntry<DoorBlock> LOCKED_STEEL_DOOR =
            steelDoorBlock("locked_steel_door", "Locked Steel Door", true, STEEL_DOOR);

    public static final BlockEntry<Block> STEEL_SHEET_METAL = REGISTRATE
            .block("steel_sheet_metal",Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(ModBlocks::steelProperties)
            .simpleItem()
            .recipe(RecipeUtils.Stonecutting
                    .customDefaultLang(ModTags.Items.STEEL_BLOCK, 4, "steel_block"))
            .onRegister(CreateRegistrate.connectedTextures(new SteelSheetMetalCTBehaviour()))
            .register();

    public static final BlockEntry<StairsBlock> STEEL_SHEET_STAIRS = REGISTRATE
            .block("steel_sheet_stairs", properties ->
                    new StairsBlock(Blocks.BRICK_STAIRS::defaultBlockState, properties))
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(ModBlocks::steelProperties)
            .item().tag(ItemTags.STAIRS).build()
            .tag(BlockTags.STAIRS)
            .blockstate((ctx, prov) -> prov.stairsBlock(ctx.get(),
                    prov.modLoc("block/steel_sheet_metal")))
            .recipe((ctx, prov) -> {
                RecipeUtils.toFunction(ctx, prov, RecipeUtils.Crafting
                        .stairs(STEEL_SHEET_METAL.get()));
                RecipeUtils.toFunction(ctx, prov, RecipeUtils.Stonecutting.
                        customDefaultLang(STEEL_SHEET_METAL.get(), 1));
            })
            .onRegister(CreateRegistrate.connectedTextures(new SteelSheetMetalCTBehaviour()))
            .register();

    public static final BlockEntry<SlabBlock> STEEL_SHEET_SLAB = REGISTRATE
            .block("steel_sheet_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(ModBlocks::steelProperties)
            .item().tag(ItemTags.SLABS).build()
            .tag(BlockTags.SLABS)
            .blockstate((ctx, prov) -> prov.slabBlock(ctx.get(),
                    prov.modLoc("block/steel_sheet_metal"),
                    prov.modLoc("block/steel_sheet_metal")))
            .recipe((ctx, prov) -> {
                RecipeUtils.toFunction(ctx, prov, RecipeUtils.Stonecutting.
                        customDefaultLang(STEEL_SHEET_METAL.get(), 2));
                ShapedRecipeBuilder.shaped(ctx.get())
                        .pattern("###")
                        .define('#', STEEL_SHEET_METAL.get())
                        .unlockedBy("has_ingredient",
                                RecipeUtils.Criterion.has(STEEL_SHEET_METAL.get()))
                        .save(prov, Alloyed.asResource("crafting/" + ctx.getName()));
            })
            .onRegister(CreateRegistrate.connectedTextures(new SteelSheetSlabCTBehaviour()))
            .register();

    public static final BlockEntry<PaneBlock> STEEL_BARS = REGISTRATE
            .block("steel_bars", PaneBlock::new)
            .initialProperties(() -> Blocks.IRON_BARS)
            .properties(ModBlocks::steelProperties)
            .blockstate(BlockStateUtils.Unique::steelBarsBlockstate)
            .tag(BlockTags.WALLS)
            .item()
            .model((ctx, prov) -> ModelUtils.customModel(ctx, prov, "block/steel_bars/block"))
            .tag(ItemTags.WALLS).build()
            .addLayer(() -> RenderType::cutoutMipped)
            .tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
            .recipe((ctx, prov) -> ShapedRecipeBuilder.shaped(ctx.get(), 16)
                    .pattern("###")
                    .pattern("###")
                    .define('#', ModTags.Items.STEEL_INGOT)
                    .unlockedBy("has_ingredient", Criterion.has(ModTags.Items.STEEL_INGOT))
                    .save(prov, Alloyed.asResource("crafting/" + ctx.getName())))
            .register();


    public static void register() {
        Alloyed.getRegistrate().addToSection(BRONZE_BLOCK, AllSections.MATERIALS);
        Alloyed.getRegistrate().addToSection(STEEL_BLOCK, AllSections.MATERIALS);

        Alloyed.LOGGER.debug("Registering ModBlocks!");
    }

    public static AbstractBlock.@NotNull Properties steelProperties(AbstractBlock.Properties properties) {
        return properties.sound(SoundType.NETHERITE_BLOCK).strength(5, 14);
    }

    private static BlockEntry<DoorBlock> steelDoorBlock(
            String name, String lang, boolean locked, @Nullable BlockEntry<DoorBlock> normalDoor) {
        return metalDoorBlock(name, "block/" + (locked ? "locked_" : "") + "steel_door/", lang, ModTags.Items.STEEL_INGOT, locked, normalDoor);
    }

    private static BlockEntry<DoorBlock> metalDoorBlock(String name,
                                                        String path,
                                                        String lang,
                                                        ITag.INamedTag<Item> tag,
                                                        boolean locked,
                                                        @Nullable BlockEntry<DoorBlock> normalDoor) {
        return REGISTRATE
                .block(name, DoorBlock::new)
                .initialProperties(locked ? Material.METAL : Material.NETHER_WOOD)
                .properties(properties -> properties
                        .noOcclusion()
                        .sound(SoundType.METAL)
                        .strength(5)
                        .harvestTool(ToolType.PICKAXE)
                        .requiresCorrectToolForDrops())
                .blockstate((ctx, prov) -> prov.doorBlock(ctx.get(), path + ctx.getName(),
                        prov.modLoc("block/" + name + "/bottom"),
                        prov.modLoc("block/" + name + "/top"))
                )
                .item().model(ModelUtils::customTexture).build()
                .recipe((ctx, prov) -> {
                    if (!locked) {
                        ShapedRecipeBuilder.shaped(ctx.get())
                                .pattern("##")
                                .pattern("##")
                                .pattern("##")
                                .define('#', tag)
                                .unlockedBy("has_ingredient", RegistrateRecipeProvider.hasItem(tag))
                                .save(prov, Alloyed.asResource("crafting/" + name));
                    } else {
                        DoorBlock door = Objects.requireNonNull(normalDoor).get();
                        // Using same locked door recipe as Create: Deco
                        ShapelessRecipeBuilder.shapeless(ctx.get())
                                .requires(Items.REDSTONE_TORCH)
                                .requires(door)
                                .unlockedBy("has_ingredient", RegistrateRecipeProvider.hasItem(door))
                                .save(prov, Alloyed.asResource("crafting/" + name));
                    }
                })
                .loot((lootTable, door) -> {
                    LootTable.Builder tableBuilder = LootTable.lootTable();
                    LootPool.Builder poolBuilder = LootPool.lootPool();

                    poolBuilder
                            .setRolls(ConstantRange.exactly(1))
                            .add(ItemLootEntry.lootTableItem(door))
                            .when(BlockStateProperty
                                    .hasBlockStateProperties(door)
                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(
                                            BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER
                                    )));
                    lootTable.add(door, tableBuilder.withPool(poolBuilder));
                })
                .lang(lang)
                .register();
    }
}