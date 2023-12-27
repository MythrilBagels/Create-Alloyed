package com.molybdenum.alloyed.common.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.compat.createdeco.CreateDecoCompat;
import com.molybdenum.alloyed.common.compat.createdeco.connected.SteelCatwalkCTBehaviour;
import com.molybdenum.alloyed.common.compat.createdeco.connected.SteelSheetVertCTBehaviour;
import com.molybdenum.alloyed.common.item.ModCreativeModeTab;
import com.molybdenum.alloyed.data.util.BlockStateUtils;
import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.List;

@SuppressWarnings("removal")
public class ModCompatBlocks {
    private static final CreateRegistrate REGISTRATE = Alloyed.REGISTRATE
            .setCreativeTab(ModCreativeModeTab.MAIN_TAB);

    // Create: Deco
    public static final BlockEntry<Block> STEEL_CATWALK = REGISTRATE
            .block("steel_catwalk", properties -> Alloyed.isCreateDecoLoaded ?
                    CreateDecoCompat.newCatwalkBlock(properties) : new Block(properties))
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
                    new BlockItem(block, properties))
            .model((ctx,prov)->
                    prov.withExistingParent(ctx.getName(), prov.mcLoc("block/template_trapdoor_bottom"))
                    .texture("texture", prov.modLoc("block/steel_catwalk"))
            )
            .build()
            .addLayer(() -> RenderType::cutoutMipped)
            .onRegister(CreateRegistrate.connectedTextures(SteelCatwalkCTBehaviour::new))
            .register();

    public static final BlockEntry<Block> STEEL_CATWALK_STAIRS = REGISTRATE
            .block("steel_catwalk_stairs", Alloyed.isCreateDecoLoaded ?
                    CreateDecoCompat::newCatwalkStairBlock : Block::new)
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


    public static void register() {
        Alloyed.LOGGER.debug("Registering ModCompatBlocks!");
    }

    public static List<ItemProviderEntry<?>> getDecoBlocks() {
        return List.of(STEEL_CATWALK, STEEL_CATWALK_STAIRS);
    }
}
