package com.molybdenum.alloyed.common.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.compat.createdeco.CreateDecoCompat;
import com.molybdenum.alloyed.common.compat.createdeco.connected.SteelCatwalkCTBehaviour;
import com.molybdenum.alloyed.common.compat.createdeco.connected.SteelSheetVertCTBehaviour;
import com.molybdenum.alloyed.common.compat.hidden.HiddenBlock;
import com.molybdenum.alloyed.common.compat.hidden.HiddenBlockItem;
import com.molybdenum.alloyed.common.compat.hidden.HiddenSlabBlock;
import com.molybdenum.alloyed.common.item.ModItemGroup;
import com.molybdenum.alloyed.data.util.BlockStateUtils;
import com.molybdenum.alloyed.data.util.ModelUtils;
import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.client.model.generators.BlockModelBuilder;

@SuppressWarnings("removal")
public class ModCompatBlocks {
    private static final CreateRegistrate REGISTRATE = Alloyed.REGISTRATE
            .creativeModeTab(() -> ModItemGroup.MAIN_GROUP);

    // Create: Deco
    public static final BlockEntry<Block> STEEL_CATWALK = REGISTRATE
            .block("steel_catwalk", properties -> Alloyed.isCreateDecoLoaded ?
                    CreateDecoCompat.newCatwalkBlock(properties) : new HiddenBlock(properties))
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(properties -> properties
                    .strength(5, 3)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.NETHERITE_BLOCK)
            )
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
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
            .onRegister(CreateRegistrate.connectedTextures(SteelCatwalkCTBehaviour::new))
            .register();

    public static final BlockEntry<Block> STEEL_CATWALK_STAIRS = REGISTRATE
            .block("steel_catwalk_stairs", properties -> Alloyed.isCreateDecoLoaded ?
                    CreateDecoCompat.newCatwalkStairBlock(properties) : new HiddenBlock(properties))
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(properties -> properties
                    .strength(5, 3)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.NETHERITE_BLOCK)
            )
            .addLayer(()-> RenderType::cutoutMipped)
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
            .blockstate((ctx,prov)->
                    prov.horizontalBlock(ctx.get(), prov.models().withExistingParent(ctx.getName(), prov.modLoc("block/catwalk_stairs"))))
            .simpleItem()
            .register();

    public static final BlockEntry<SlabBlock> STEEL_SHEET_VERTICAL_SLAB = REGISTRATE
            .block("steel_sheet_vertical_slab", Alloyed.isCreateDecoLoaded ?
                    CreateDecoCompat::newVerticalSlabBlock : HiddenSlabBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(ModBlocks::steelProperties)
            .simpleItem()
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .blockstate(BlockStateUtils.Unique::steelVerticalSlabBlockstate)
            .loot((lootTable, block) -> {
                LootTable.Builder builder = LootTable.lootTable();
                LootPool.Builder lootPool = LootPool.lootPool();

                lootPool.setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(block)
                                .apply(SetItemCountFunction
                                        .setCount(ConstantValue.exactly(2))
                                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(BlockStateProperties.SLAB_TYPE, SlabType.DOUBLE)))));

                lootTable.add(block, builder.withPool(lootPool));
            })
            .onRegister(CreateRegistrate.connectedTextures(SteelSheetVertCTBehaviour::new))
            .register();

    public static final BlockEntry<Block> STEEL_MESH_FENCE = REGISTRATE
            .block("steel_mesh_fence", Alloyed.isCreateDecoLoaded ?
                    FenceBlock::new : HiddenBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(properties -> properties.sound(SoundType.CHAIN))
            .tag(BlockTags.FENCES)
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .item()
            .model((ctx,prov) -> ModelUtils.customModel(ctx, prov, "block/steel_chain_link"))
            .build()
            .blockstate(BlockStateUtils.Unique::steelMeshFenceBlockstate)
            .addLayer(() -> RenderType::cutoutMipped)
            .register();

    public static void register() {
        Alloyed.LOGGER.debug("Registering ModCompatBlocks!");
    }

}
