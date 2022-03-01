package com.molybdenum.alloyed.common.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.items.ModItemGroups;
import com.molybdenum.alloyed.data.util.BlockStateUtils;
import com.molybdenum.alloyed.data.util.DataUtils;
import com.molybdenum.alloyed.data.util.ModelUtils;
import com.simibubi.create.content.AllSections;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.ModelGen;
import com.simibubi.create.foundation.worldgen.OxidizingBlock;
import com.simibubi.create.repack.registrate.providers.RegistrateRecipeProvider;
import com.simibubi.create.repack.registrate.util.entry.BlockEntry;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
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
import net.minecraft.tags.ITag;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.Objects;

import static com.molybdenum.alloyed.data.util.RecipeUtils.Crafting;
import static com.molybdenum.alloyed.data.util.RecipeUtils.Criterion;

@SuppressWarnings("unused")
public class ModBlocks {
    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate().itemGroup(() -> ModItemGroups.MAIN_GROUP);

    // BRONZE
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
            .transform(DataUtils.tagBlockAndItem(
                    ModTags.Blocks.STEEL_BLOCK,
                    ModTags.Items.STEEL_BLOCK
            ))
            .recipe(Crafting.metalBlockRecipe(ModTags.Items.STEEL_INGOT, "steel_ingot"))
            .lang("Block of Steel")
		    .register();

    public static final BlockEntry<DoorBlock> STEEL_DOOR =
            steelDoorBlock("steel_door", "Steel Door", false, null);

    public static final BlockEntry<DoorBlock> LOCKED_STEEL_DOOR =
            steelDoorBlock("locked_steel_door", "Locked Steel Door", true, STEEL_DOOR);



    public static void register() {
        Alloyed.getRegistrate().addToSection(BRONZE_BLOCK, AllSections.MATERIALS);
        Alloyed.getRegistrate().addToSection(STEEL_BLOCK, AllSections.MATERIALS);

        Alloyed.LOGGER.debug("Registering ModBlocks!");
    }

    private static BlockEntry<DoorBlock> steelDoorBlock(
            String name, String lang, boolean locked, @Nullable BlockEntry<DoorBlock> defaultDoor) {
        return metalDoorBlock(name, "block/" + (locked ? "locked_" : "") + "steel_door/", lang, ModTags.Items.STEEL_INGOT, locked, defaultDoor);
    }

    private static BlockEntry<DoorBlock> metalDoorBlock(String name,
                                                        String path,
                                                        String lang,
                                                        ITag.INamedTag<Item> tag,
                                                        boolean locked,
                                                        @Nullable BlockEntry<DoorBlock> defaultDoor) {
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
                                .unlockedBy("has_ingot", RegistrateRecipeProvider.hasItem(tag))
                                .save(prov, Alloyed.asResource("crafting/" + name));
                    } else {
                        DoorBlock door = Objects.requireNonNull(defaultDoor).get();
                        // Using same locked door recipe as Create: Deco
                        ShapelessRecipeBuilder.shapeless(ctx.get())
                                .requires(Items.REDSTONE_TORCH)
                                .requires(door)
                                .unlockedBy("has_door", RegistrateRecipeProvider.hasItem(door))
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