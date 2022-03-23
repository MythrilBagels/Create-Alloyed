package com.molybdenum.alloyed.common.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.compat.createdeco.CreateDecoCompat;
import com.molybdenum.alloyed.common.compat.createdeco.connected.SteelCatwalkCTBehaviour;
import com.molybdenum.alloyed.common.compat.createdeco.connected.SteelSheetVertCTBehaviour;
import com.molybdenum.alloyed.common.compat.hidden.HiddenBlock;
import com.molybdenum.alloyed.common.compat.hidden.HiddenBlockItem;
import com.molybdenum.alloyed.common.compat.hidden.HiddenSlabBlock;
import com.molybdenum.alloyed.common.items.ModItemGroups;
import com.molybdenum.alloyed.data.util.BlockStateUtils;
import com.molybdenum.alloyed.data.util.ModelUtils;
import com.molybdenum.alloyed.data.util.RecipeUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.entry.BlockEntry;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;

public class ModCompatBlocks {
    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate()
            .itemGroup(() -> ModItemGroups.MAIN_GROUP);

    // Create: Deco
    public static final BlockEntry<Block> STEEL_CATWALK = REGISTRATE
            .block("steel_catwalk", properties -> Alloyed.isCreateDecoLoaded ?
                    CreateDecoCompat.newCatwalkBlock(properties) : new HiddenBlock(properties))
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(properties -> properties
                    .strength(5, 3)
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.NETHERITE_BLOCK)
            )
            .blockstate(BlockStateUtils.Unique::steelCatwalkBlockstate)
            .item((block, properties) -> Alloyed.isCreateDecoLoaded ?
                    CreateDecoCompat.newCatwalkBlockItem(block, properties) :
                    new HiddenBlockItem(block, properties))
            .model((ctx,prov)->
                    prov.withExistingParent(ctx.getName(), prov.mcLoc("block/template_trapdoor_bottom"))
                    .texture("texture", prov.modLoc("block/steel_catwalk"))
            )
            .build()
            .addLayer(() -> RenderType::cutoutMipped)
            .onRegister(CreateRegistrate.connectedTextures(new SteelCatwalkCTBehaviour()))
            .register();

    public static final BlockEntry<SlabBlock> STEEL_SHEET_VERTICAL_SLAB = REGISTRATE
            .block("steel_sheet_vertical_slab", Alloyed.isCreateDecoLoaded ?
                    CreateDecoCompat::newVerticalSlabBlock : HiddenSlabBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(ModBlocks::steelProperties)
            .simpleItem()
            .blockstate(BlockStateUtils.Unique::steelVerticalSlabBlockstate)
            .loot((lootTable, block) -> {
                LootTable.Builder lootBuilder = LootTable.lootTable();
                LootPool.Builder lootPool = LootPool.lootPool();

                lootPool.setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(block)
                        .apply(SetCount.setCount(ConstantRange.exactly(2)).when(
                                BlockStateProperty.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(BlockStateProperties.SLAB_TYPE, SlabType.DOUBLE))
                        )));

                lootPool.setRolls(ConstantRange.exactly(1))
                        .add(ItemLootEntry.lootTableItem(block)
                        .apply(SetCount.setCount(ConstantRange.exactly(1))));

                lootTable.add(block, lootBuilder.withPool(lootPool));
            })
            .onRegister(CreateRegistrate.connectedTextures(new SteelSheetVertCTBehaviour()))
            .register();

    public static final BlockEntry<Block> STEEL_MESH_FENCE = REGISTRATE
            .block("steel_mesh_fence", Alloyed.isCreateDecoLoaded ?
                    FenceBlock::new : HiddenBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(properties -> properties.sound(SoundType.CHAIN))
            .tag(BlockTags.FENCES)
            .item()
            .model((ctx,prov) -> ModelUtils.customModel(ctx, prov, "block/steel_chain_link"))
            .build()
            .blockstate(BlockStateUtils.Unique::steelMeshFenceBlockstate)
            .recipe((ctx,prov)-> ShapedRecipeBuilder.shaped(ctx.get(), 3)
                    .pattern("-s-")
                    .pattern("-s-")
                    .define('-', ModTags.Items.STEEL_SHEET)
                    .define('s', Items.STRING)
                    .unlockedBy("has_ingredient", RecipeUtils.Criterion
                            .has(ModTags.Items.STEEL_SHEET))
                    .save(prov, Alloyed.asResource("crafting/" + ctx.getName())))
            .addLayer(() -> RenderType::cutoutMipped)
            .register();

    public static void register() {
        Alloyed.LOGGER.debug("Registering ModCompatBlocks!");
    }

    public static ResourceLocation asDecoResource(String path) {
        return new ResourceLocation("createdeco", path);
    }

    public static ResourceLocation asDecoBlockPath(String path) {
        return asDecoResource("block/" + path);
    }
}
