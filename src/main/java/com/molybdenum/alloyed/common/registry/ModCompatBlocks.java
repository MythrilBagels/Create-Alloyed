package com.molybdenum.alloyed.common.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.compat.HiddenBlock;
import com.molybdenum.alloyed.common.compat.HiddenBlockItem;
import com.molybdenum.alloyed.common.compat.createdeco.CreateDecoCompat;
import com.molybdenum.alloyed.common.compat.createdeco.SteelCatwalkBlock;
import com.molybdenum.alloyed.common.compat.createdeco.SteelCatwalkCTBehaviour;
import com.molybdenum.alloyed.common.items.ModItemGroups;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.entry.BlockEntry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.common.ToolType;

@SuppressWarnings("unused")
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
            .item((block, properties) -> Alloyed.isCreateDecoLoaded ?
                    CreateDecoCompat.newCatwalkBlockItem((SteelCatwalkBlock) block, properties) :
                    new HiddenBlockItem(block, properties))
            .model((ctx,prov)-> prov.withExistingParent(ctx.getName(), prov.mcLoc("block/template_trapdoor_bottom"))
                    .texture("texture", prov.modLoc("block/steel_catwalk"))
            )
            .build()
            .recipe((ctx, prov) -> { /* TODO */})
            .blockstate((ctx,prov)-> {
                // Credit to C: Deco for most of this blockstate code
                String texture = "alloyed:block/steel_catwalk";

                String lowerParent = "alloyed:block/compat/createdeco/catwalk_bottom";
                String upperParent = "alloyed:block/compat/createdeco/catwalk_top";
                String railUpperParent = "alloyed:block/compat/createdeco/catwalk_rail_upper";
                String railLowerParent = "alloyed:block/compat/createdeco/catwalk_rail_lower";

                String path = "block/compat/createdeco/";

                BlockModelBuilder lower = prov.models().withExistingParent(path + ctx.getName() + "_bottom",
                                lowerParent)
                        .texture("2", texture)
                        .texture("particle", texture);
                BlockModelBuilder upper = prov.models().withExistingParent(path + ctx.getName() + "_top",
                                upperParent)
                        .texture("2", texture)
                        .texture("particle", texture);
                BlockModelBuilder railUpper = prov.models().withExistingParent(path + ctx.getName() + "_rail_upper",
                                railUpperParent)
                        .texture("3", texture + "_rail")
                        .texture("particle", texture + "_rail");
                BlockModelBuilder railLower = prov.models().withExistingParent(path + ctx.getName() + "_rail_lower",
                                railLowerParent)
                        .texture("3", texture + "_rail")
                        .texture("particle", texture + "_rail");

                prov.getMultipartBuilder(ctx.get()).part().modelFile(lower).addModel().condition(BlockStateProperties.BOTTOM, true).end();
                prov.getMultipartBuilder(ctx.get()).part().modelFile(upper).addModel().condition(BlockStateProperties.BOTTOM, false).end();

                prov.getMultipartBuilder(ctx.get()).part().modelFile(railLower).rotationY( 90).addModel()
                        .condition(BlockStateProperties.BOTTOM, true)
                        .condition(BlockStateProperties.NORTH, true).end();
                prov.getMultipartBuilder(ctx.get()).part().modelFile(railLower).rotationY(-90).addModel()
                        .condition(BlockStateProperties.BOTTOM, true)
                        .condition(BlockStateProperties.SOUTH, true).end();
                prov.getMultipartBuilder(ctx.get()).part().modelFile(railLower).rotationY(180).addModel()
                        .condition(BlockStateProperties.BOTTOM, true)
                        .condition(BlockStateProperties.EAST,  true).end();
                prov.getMultipartBuilder(ctx.get()).part().modelFile(railLower).rotationY(  0).addModel()
                        .condition(BlockStateProperties.BOTTOM, true)
                        .condition(BlockStateProperties.WEST,  true).end();

                prov.getMultipartBuilder(ctx.get()).part().modelFile(railUpper).rotationY( 90).addModel()
                        .condition(BlockStateProperties.BOTTOM, false)
                        .condition(BlockStateProperties.NORTH, true).end();
                prov.getMultipartBuilder(ctx.get()).part().modelFile(railUpper).rotationY(-90).addModel()
                        .condition(BlockStateProperties.BOTTOM, false)
                        .condition(BlockStateProperties.SOUTH, true).end();
                prov.getMultipartBuilder(ctx.get()).part().modelFile(railUpper).rotationY(180).addModel()
                        .condition(BlockStateProperties.BOTTOM, false)
                        .condition(BlockStateProperties.EAST,  true).end();
                prov.getMultipartBuilder(ctx.get()).part().modelFile(railUpper).rotationY(  0).addModel()
                        .condition(BlockStateProperties.BOTTOM, false)
                        .condition(BlockStateProperties.WEST,  true).end();
            })
            .addLayer(() -> RenderType::cutoutMipped)
            .onRegister(CreateRegistrate.connectedTextures(new SteelCatwalkCTBehaviour()))
            .register();

    public static void register() { Alloyed.LOGGER.debug("Registering ModCompatBlocks!"); }
}
