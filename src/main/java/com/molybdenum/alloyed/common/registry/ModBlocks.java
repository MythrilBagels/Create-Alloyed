package com.molybdenum.alloyed.common.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.compat.createdeco.connected.SteelSheetMetalCTBehaviour;
import com.molybdenum.alloyed.common.compat.createdeco.connected.SteelSheetSlabCTBehaviour;
import com.molybdenum.alloyed.common.content.blocks.BronzeBellBlock;
import com.molybdenum.alloyed.common.content.blocks.SteelDoorBlock;
import com.molybdenum.alloyed.common.content.blocks.AlloyedShaftBlock;
import com.molybdenum.alloyed.common.item.ModCreativeModeTab;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.contraptions.behaviour.DoorMovingInteraction;
import com.simibubi.create.content.decoration.MetalLadderBlock;
import com.simibubi.create.content.decoration.MetalScaffoldingBlock;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.decoration.palettes.ConnectedPillarBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogCTBehaviour;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import com.simibubi.create.foundation.block.CopperBlockSet;
import com.simibubi.create.foundation.block.connected.RotatedPillarCTBehaviour;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.createmod.catnip.data.Couple;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;

import static com.simibubi.create.api.behaviour.interaction.MovingInteractionBehaviour.interactionBehaviour;
import static com.simibubi.create.foundation.data.BlockStateGen.axisBlock;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

@SuppressWarnings({"unused", "removal"})
public class ModBlocks {

    private static final CreateRegistrate REGISTRATE = Alloyed.REGISTRATE.setCreativeTab(ModCreativeModeTab.MAIN_TAB).defaultCreativeTab((ResourceKey<CreativeModeTab>) null);;

    // BRONZE

    public static final CopperBlockSet BRONZE_BLOCKS = new CopperBlockSet( // Ignore that it says COPPER block set. The code works for any oxidizing metal.
            REGISTRATE,
            "bronze_block",
            "bronze_block",
            new CopperBlockSet.Variant<?>[] { CopperBlockSet.BlockVariant.INSTANCE },
            "bronze/"
    );

    public static final BlockEntry<Block> CUT_BRONZE = REGISTRATE
            .block("cut_bronze",Block::new)
            .initialProperties(() -> Blocks.CUT_COPPER)
            .properties(ModBlocks::steelProperties)
            .simpleItem()
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(BlockTags.NEEDS_STONE_TOOL)
            .onRegister(CreateRegistrate.connectedTextures(SteelSheetMetalCTBehaviour::new))
            .register();

    public static final BlockEntry<StairBlock> CUT_BRONZE_STAIRS = REGISTRATE
            .block("cut_bronze_stairs", properties ->
                    new StairBlock(Blocks.BRICK_STAIRS.defaultBlockState(), properties))
            .initialProperties(() -> Blocks.CUT_COPPER)
            .properties(ModBlocks::steelProperties)
            .item().tag(ItemTags.STAIRS).build()
            .tag(BlockTags.STAIRS)
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(BlockTags.NEEDS_STONE_TOOL)
            .blockstate((ctx, prov) -> prov.stairsBlock(ctx.get(),
                    prov.modLoc("block/cut_bronze_metal")))
            .onRegister(CreateRegistrate.connectedTextures(SteelSheetMetalCTBehaviour::new))
            .register();

    public static final BlockEntry<SlabBlock> CUT_BRONZE_SLAB = REGISTRATE
            .block("cut_bronze_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.CUT_COPPER)
            .properties(ModBlocks::steelProperties)
            .item().tag(ItemTags.SLABS).build()
            .tag(BlockTags.SLABS)
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(BlockTags.NEEDS_STONE_TOOL)
            .blockstate((ctx, prov) -> prov.slabBlock(ctx.get(),
                    prov.modLoc("block/cut_bronze_metal"),
                    prov.modLoc("block/cut_bronze_metal")))
            .loot((table, block) -> {
                LootTable.Builder builder = LootTable.lootTable();
                LootPool.Builder lootPool = LootPool.lootPool();

                lootPool.setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(block)
                                .apply(SetItemCountFunction
                                        .setCount(ConstantValue.exactly(2))
                                        .when(LootItemBlockStatePropertyCondition
                                                .hasBlockStateProperties(block)
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(BlockStateProperties.SLAB_TYPE, SlabType.DOUBLE)))));

                table.add(block, builder.withPool(lootPool));
            })
            .onRegister(CreateRegistrate.connectedTextures(SteelSheetSlabCTBehaviour::new))
            .register();

    public static final BlockEntry<ConnectedPillarBlock> BRONZE_PILLAR = REGISTRATE.block("bronze_pillar", ConnectedPillarBlock::new)
            .properties(ModBlocks::bronzeProperties).item().build()
            .onRegister(CreateRegistrate.connectedTextures(() -> new RotatedPillarCTBehaviour(ModSpriteShifts.BRONZE_PILLAR, ModSpriteShifts.BRONZE_CAP)))
            .register();

    public static final BlockEntry<CasingBlock> BRONZE_CASING = REGISTRATE.block("bronze_casing", CasingBlock::new)
            .transform(BuilderTransformers.casing(() -> ModSpriteShifts.BRONZE_CASING))
            .properties(ModBlocks::bronzeProperties)
            .register();

    public static final BlockEntry<AlloyedShaftBlock> BRONZE_ENCASED_SHAFT = REGISTRATE
            .block("bronze_encased_shaft", p -> new AlloyedShaftBlock(p, ModBlocks.BRONZE_CASING::get))
            .properties(ModBlocks::bronzeProperties)
            .transform(ModTransformers.encasedShaft("bronze", () -> ModSpriteShifts.BRONZE_CASING))
            .transform(axeOrPickaxe())
            .register();

    public static final BlockEntry<EncasedCogwheelBlock> BRONZE_ENCASED_COGWHEEL = REGISTRATE
            .block("bronze_encased_cogwheel", p -> new EncasedCogwheelBlock(p, false, ModBlocks.BRONZE_CASING::get))
            .properties(ModBlocks::bronzeProperties)
            .transform(ModTransformers.encasedCogwheel("bronze", () -> ModSpriteShifts.BRONZE_CASING))
            .blockstate((c, p) -> axisBlock(c, p, blockState -> {
                String suffix = (blockState.getValue(EncasedCogwheelBlock.TOP_SHAFT) ? "_top" : "")
                        + (blockState.getValue(EncasedCogwheelBlock.BOTTOM_SHAFT) ? "_bottom" : "");
                return p.models().getExistingFile(p.modLoc("block/bronze_encased_cogwheel/block" + suffix));
            }, false))
            .item()
            .model((c, p) -> {
                p.getExistingFile(p.modLoc(c.getName()));
            })
            .build()
            .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(ModSpriteShifts.BRONZE_CASING,
                    Couple.create(ModSpriteShifts.BRONZE_ENCASED_COGWHEEL_SIDE,
                            ModSpriteShifts.BRONZE_ENCASED_COGWHEEL_OTHERSIDE))))
            .transform(axeOrPickaxe())
            .register();


    public static final BlockEntry<EncasedCogwheelBlock> BRONZE_ENCASED_LARGE_COGWHEEL = REGISTRATE
            .block("bronze_encased_large_cogwheel", p -> new EncasedCogwheelBlock(p, true, ModBlocks.BRONZE_CASING::get))
            .properties(ModBlocks::bronzeProperties)
            .transform(ModTransformers.encasedLargeCogwheel("bronze", () -> ModSpriteShifts.BRONZE_CASING))
            .blockstate((c, p) -> axisBlock(c, p, blockState -> {
                String suffix = (blockState.getValue(EncasedCogwheelBlock.TOP_SHAFT) ? "_top" : "")
                        + (blockState.getValue(EncasedCogwheelBlock.BOTTOM_SHAFT) ? "_bottom" : "");
                return p.models().getExistingFile(p.modLoc("block/bronze_encased_large_cogwheel/block" + suffix));
            }, false))
            .item()
            .model((c, p) -> {
                p.getExistingFile(p.modLoc(c.getName()));
            })
            .build()
            .transform(axeOrPickaxe())
            .register();

    public static final BlockEntry<BronzeBellBlock> BRONZE_BELL = REGISTRATE
            .block("bronze_bell", BronzeBellBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(properties -> properties
                    .noOcclusion()
                    .sound(SoundType.ANVIL))
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(BlockTags.NEEDS_STONE_TOOL)
            .recipe((ctx, prov) -> {
                ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ctx.get(), 1)
                        .pattern("#")
                        .pattern("-")
                        .define('#', ModTags.Items.BRONZE_BLOCK)
                        .define('-', ModTags.Items.BRONZE_SHEET)
                        .unlockedBy("has_bronze_ingot", RegistrateRecipeProvider.has(ModTags.Items.BRONZE_INGOT))
                        .save(prov, Alloyed.asResource("crafting/" + ctx.getName()));
            }).item().build()
            .register();



    // STEEL

    public static final BlockEntry<Block> STEEL_BLOCK = REGISTRATE
            .block("steel_block", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(ModBlocks::steelProperties)
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(BlockTags.NEEDS_STONE_TOOL)
            .lang("Block of Steel")
            .item().build()
            .register();

    public static final BlockEntry<CasingBlock> STEEL_CASING = REGISTRATE.block("steel_casing", CasingBlock::new)
            .transform(BuilderTransformers.casing(() -> ModSpriteShifts.STEEL_CASING))
            .properties(ModBlocks::steelProperties)
            .register();

    public static final BlockEntry<AlloyedShaftBlock> STEEL_ENCASED_SHAFT = REGISTRATE
            .block("steel_encased_shaft", p -> new AlloyedShaftBlock(p, ModBlocks.STEEL_CASING::get))
            .properties(ModBlocks::steelProperties)
            .transform(ModTransformers.encasedShaft("steel", () -> ModSpriteShifts.STEEL_CASING))
            .transform(axeOrPickaxe())
            .register();

    public static final BlockEntry<EncasedCogwheelBlock> STEEL_ENCASED_COGWHEEL = REGISTRATE
            .block("steel_encased_cogwheel", p -> new EncasedCogwheelBlock(p, false, ModBlocks.STEEL_CASING::get))
            .properties(ModBlocks::steelProperties)
            .transform(ModTransformers.encasedCogwheel("steel", () -> ModSpriteShifts.STEEL_CASING))
            .blockstate((c, p) -> axisBlock(c, p, blockState -> {
                String suffix = (blockState.getValue(EncasedCogwheelBlock.TOP_SHAFT) ? "_top" : "")
                        + (blockState.getValue(EncasedCogwheelBlock.BOTTOM_SHAFT) ? "_bottom" : "");
                return p.models().getExistingFile(p.modLoc("block/steel_encased_cogwheel/block" + suffix));
            }, false))
            .item()
            .model((c, p) -> {
                p.getExistingFile(p.modLoc(c.getName()));
            })
            .build()
            .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCogCTBehaviour(ModSpriteShifts.STEEL_CASING,
                    Couple.create(ModSpriteShifts.STEEL_ENCASED_COGWHEEL_SIDE,
                            ModSpriteShifts.STEEL_ENCASED_COGWHEEL_OTHERSIDE))))
            .transform(axeOrPickaxe())
            .register();


    public static final BlockEntry<EncasedCogwheelBlock> STEEL_ENCASED_LARGE_COGWHEEL = REGISTRATE
            .block("steel_encased_large_cogwheel", p -> new EncasedCogwheelBlock(p, true, ModBlocks.STEEL_CASING::get))
            .properties(ModBlocks::steelProperties)
            .transform(ModTransformers.encasedLargeCogwheel("steel", () -> ModSpriteShifts.STEEL_CASING))
            .blockstate((c, p) -> axisBlock(c, p, blockState -> {
                String suffix = (blockState.getValue(EncasedCogwheelBlock.TOP_SHAFT) ? "_top" : "")
                        + (blockState.getValue(EncasedCogwheelBlock.BOTTOM_SHAFT) ? "_bottom" : "");
                return p.models().getExistingFile(p.modLoc("block/steel_encased_large_cogwheel/block" + suffix));
            }, false))
            .item()
            .model((c, p) -> {
                p.getExistingFile(p.modLoc(c.getName()));
            })
            .build()
            .transform(axeOrPickaxe())
            .register();


    public static final BlockEntry<MetalScaffoldingBlock> STEEL_SCAFFOLD =
            REGISTRATE.block("steel_scaffolding", MetalScaffoldingBlock::new)
                    .transform(ModTransformers.scaffold("steel",
                            () -> DataIngredient.tag(AllTags.commonItemTag("ingots/steel")), MapColor.COLOR_GRAY,
                            ModSpriteShifts.STEEL_SCAFFOLD, ModSpriteShifts.STEEL_SCAFFOLD_INSIDE, ModSpriteShifts.STEEL_CASING))
                    .properties(ModBlocks::steelProperties)
                    .register();


    public static final BlockEntry<SteelDoorBlock> STEEL_DOOR =
            steelDoorBlock(false, null)
                    .onRegister(interactionBehaviour(new DoorMovingInteraction()))
                    .register();

    public static final BlockEntry<SteelDoorBlock> LOCKED_STEEL_DOOR =
            steelDoorBlock(true, STEEL_DOOR).register();

    public static final BlockEntry<Block> STEEL_SHEET_METAL = REGISTRATE
            .block("steel_sheet_metal",Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(ModBlocks::steelProperties)
            .simpleItem()
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(BlockTags.NEEDS_STONE_TOOL)
            .onRegister(CreateRegistrate.connectedTextures(SteelSheetMetalCTBehaviour::new))
            .register();

    public static final BlockEntry<StairBlock> STEEL_SHEET_STAIRS = REGISTRATE
            .block("steel_sheet_stairs", properties ->
                    new StairBlock(Blocks.BRICK_STAIRS.defaultBlockState(), properties))
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(ModBlocks::steelProperties)
            .item().tag(ItemTags.STAIRS).build()
            .tag(BlockTags.STAIRS)
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(BlockTags.NEEDS_STONE_TOOL)
            .blockstate((ctx, prov) -> prov.stairsBlock(ctx.get(),
                    prov.modLoc("block/steel_sheet_metal")))
            .onRegister(CreateRegistrate.connectedTextures(SteelSheetMetalCTBehaviour::new))
            .register();

    public static final BlockEntry<SlabBlock> STEEL_SHEET_SLAB = REGISTRATE
            .block("steel_sheet_slab", SlabBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(ModBlocks::steelProperties)
            .item().tag(ItemTags.SLABS).build()
            .tag(BlockTags.SLABS)
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(BlockTags.NEEDS_STONE_TOOL)
            .blockstate((ctx, prov) -> prov.slabBlock(ctx.get(),
                    prov.modLoc("block/steel_sheet_metal"),
                    prov.modLoc("block/steel_sheet_metal")))
            .loot((table, block) -> {
                LootTable.Builder builder = LootTable.lootTable();
                LootPool.Builder lootPool = LootPool.lootPool();

                lootPool.setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(block)
                                .apply(SetItemCountFunction
                                        .setCount(ConstantValue.exactly(2))
                                        .when(LootItemBlockStatePropertyCondition
                                                .hasBlockStateProperties(block)
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(BlockStateProperties.SLAB_TYPE, SlabType.DOUBLE)))));

                table.add(block, builder.withPool(lootPool));
            })
            .onRegister(CreateRegistrate.connectedTextures(SteelSheetSlabCTBehaviour::new))
            .register();

    public static final BlockEntry<IronBarsBlock> STEEL_BARS = REGISTRATE
            .block("steel_bars", IronBarsBlock::new)
            .initialProperties(() -> Blocks.IRON_BARS)
            .properties(ModBlocks::steelProperties)
            .tag(BlockTags.WALLS)
            .item()
            .tag(ItemTags.WALLS).build()
            .addLayer(() -> RenderType::cutoutMipped)
            .tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(BlockTags.NEEDS_STONE_TOOL)
            .recipe((ctx, prov) -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ctx.get(), 16)
                    .pattern("###")
                    .pattern("###")
                    .define('#', ModTags.Items.STEEL_INGOT)
                    .unlockedBy("has_ingredient", RegistrateRecipeProvider.has(ModTags.Items.STEEL_INGOT))
                    .save(prov, Alloyed.asResource("crafting/" + ctx.getName())))
            .register();

    public static final BlockEntry<TrapDoorBlock> STEEL_TRAPDOOR = REGISTRATE
            .block("steel_trapdoor", properties -> new TrapDoorBlock(ModBlockSetTypes.STEEL, properties))
            .initialProperties(() -> Blocks.IRON_TRAPDOOR)
            .properties(ModBlocks::steelProperties)
            .blockstate((ctx, prov) ->
                    prov.trapdoorBlock(ctx.get(), prov.modLoc("block/steel_trapdoor"), true))
            .tag(BlockTags.TRAPDOORS)
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(BlockTags.NEEDS_STONE_TOOL)
            .item()
            .model((ctx,prov) ->
                    prov.withExistingParent(ctx.getName(), prov.modLoc("block/steel_trapdoor_bottom"))
            )
            .tag(ItemTags.TRAPDOORS)
            .build()
            .addLayer(() -> RenderType::cutoutMipped)
            .tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
            .recipe((ctx, prov) -> ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ctx.get())
                    .pattern("##")
                    .pattern("##")
                    .define('#', ModTags.Items.STEEL_INGOT)
                    .unlockedBy("has_ingredient", RegistrateRecipeProvider.has(ModTags.Items.STEEL_INGOT))
                    .save(prov, Alloyed.asResource("crafting/" + ctx.getName())))
            .register();

    public static final BlockEntry<MetalLadderBlock> STEEL_LADDER = REGISTRATE
            .block("steel_ladder", MetalLadderBlock::new)
            .transform(BuilderTransformers.ladder("steel",
                    () -> DataIngredient.tag(ModTags.Items.STEEL_SHEET), MapColor.COLOR_GRAY))
            .blockstate((ctx, prov) -> prov.horizontalBlock(ctx.get(), prov.models()
                    .getExistingFile(prov.modLoc("block/steel_ladder"))))
            .lang("Steel Ladder")
            .register();

    public static final BlockEntry<FenceBlock> STEEL_MESH_FENCE = REGISTRATE
            .block("steel_mesh_fence", FenceBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(properties -> properties.sound(SoundType.CHAIN))
            .tag(BlockTags.FENCES)
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .item()
            .build()
            .addLayer(() -> RenderType::cutoutMipped)
            .register();


    public static void register() {
        Alloyed.LOGGER.debug("Registering ModBlocks!");
    }

    public static BlockBehaviour.@NotNull Properties steelProperties(BlockBehaviour.Properties properties) {
        return properties.sound(SoundType.NETHERITE_BLOCK).strength(5, 14).mapColor(MapColor.COLOR_GRAY);
    }

    public static BlockBehaviour.@NotNull Properties bronzeProperties(BlockBehaviour.Properties properties) {
        return properties.sound(SoundType.COPPER).strength(3, 6).mapColor(MapColor.COLOR_ORANGE);
    }

    public static void fixBronzeBlocks() {
    }

    private static BlockBuilder<SteelDoorBlock, CreateRegistrate> steelDoorBlock(boolean locked,
                                                                                 @Nullable BlockEntry<SteelDoorBlock> normalDoor) {
        String path = "block/" + (locked ? "locked_" : "") + "steel_door/";
        String name = (locked ? "locked_" : "") + "steel_door";

        return REGISTRATE
                .block(name, properties -> new SteelDoorBlock(properties, locked)).item().build()
                .properties(properties -> properties
                        .noOcclusion()
                        .sound(SoundType.METAL)
                        .strength(5)
                        .requiresCorrectToolForDrops())
                .blockstate((ctx, prov) -> prov.doorBlock(ctx.get(), path + ctx.getName(),
                        prov.modLoc("block/" + name + "/bottom"),
                        prov.modLoc("block/" + name + "/top"))
                )
                .tag(BlockTags.NEEDS_STONE_TOOL, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.DOORS)
                .recipe((ctx, prov) -> {
                    if (!locked) {
                        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ctx.get())
                                .pattern("##")
                                .pattern("##")
                                .pattern("##")
                                .define('#', ModTags.Items.STEEL_INGOT)
                                .unlockedBy("has_ingredient", RegistrateRecipeProvider.has(ModTags.Items.STEEL_INGOT))
                                .save(prov, Alloyed.asResource("crafting/" + name));
                    } else {
                        DoorBlock door = Objects.requireNonNull(normalDoor).get();
                        // Using same locked door recipe as Create: Deco
                        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ctx.get())
                                .requires(Items.REDSTONE_TORCH)
                                .requires(door)
                                .unlockedBy("has_ingredient", RegistrateRecipeProvider.has(door))
                                .save(prov, Alloyed.asResource("crafting/" + name));
                    }
                })
                .loot((lootTable, door) -> {
                    LootTable.Builder tableBuilder = LootTable.lootTable();
                    LootPool.Builder poolBuilder = LootPool.lootPool();

                    poolBuilder
                            .setRolls(ConstantValue.exactly(1))
                            .add(LootItem.lootTableItem(door))
                            .when(LootItemBlockStatePropertyCondition
                                    .hasBlockStateProperties(door)
                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(
                                            BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER
                                    )));
                    lootTable.add(door, tableBuilder.withPool(poolBuilder));
                });
    }
}
