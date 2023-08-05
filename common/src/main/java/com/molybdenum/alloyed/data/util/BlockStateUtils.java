package com.molybdenum.alloyed.data.util;

import com.molybdenum.alloyed.Alloyed;
import com.simibubi.create.foundation.utility.Iterate;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;

public class BlockStateUtils {

    public static <T extends Block> void existingModel(DataGenContext<Block, T> ctx, RegistrateBlockstateProvider prov) {
        prov.simpleBlock(
                ctx.getEntry(),
                prov.models()
                        .getExistingFile(prov.modLoc("block/" + ctx.getName()))
        );
    }

    public static class Unique { // Blockstate code for specific blocks
        public static <T extends Block> void steelCatwalkBlockstate(DataGenContext<Block, T> ctx, RegistrateBlockstateProvider prov) {
            // Credit to C: Deco for most of this blockstate code
            String texture = "alloyed:block/steel_catwalk";

            String lowerParent = "alloyed:block/catwalk_bottom";
            String upperParent = "alloyed:block/catwalk_top";
            String railUpperParent = "alloyed:block/catwalk_rail_upper";
            String railLowerParent = "alloyed:block/catwalk_rail_lower";

            String path = "block/steel_catwalk/";

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

            for (boolean isBottom : Iterate.trueAndFalse) {
                BlockModelBuilder currentModel = isBottom ? railLower : railUpper;

                prov.getMultipartBuilder(ctx.get()).part().modelFile(currentModel).rotationY( 90).addModel()
                        .condition(BlockStateProperties.BOTTOM, isBottom)
                        .condition(BlockStateProperties.NORTH, true).end();
                prov.getMultipartBuilder(ctx.get()).part().modelFile(currentModel).rotationY(-90).addModel()
                        .condition(BlockStateProperties.BOTTOM, isBottom)
                        .condition(BlockStateProperties.SOUTH, true).end();
                prov.getMultipartBuilder(ctx.get()).part().modelFile(currentModel).rotationY(180).addModel()
                        .condition(BlockStateProperties.BOTTOM, isBottom)
                        .condition(BlockStateProperties.EAST,  true).end();
                prov.getMultipartBuilder(ctx.get()).part().modelFile(currentModel).rotationY(  0).addModel()
                        .condition(BlockStateProperties.BOTTOM, isBottom)
                        .condition(BlockStateProperties.WEST,  true).end();
            }
        }

        public static <T extends Block> void steelVerticalSlabBlockstate(DataGenContext<Block, T> ctx, RegistrateBlockstateProvider prov) {
            // Credit to C: Deco for most of this blockstate code
            ResourceLocation texture = prov.modLoc("block/steel_sheet_metal");

            BlockModelBuilder half = prov.models().withExistingParent(ctx.getName(),
                    prov.modLoc("block/vertical_slab")).texture("side", texture);
            BlockModelBuilder both = prov.models().cubeAll(ctx.getName() + "_double", texture);

            int y = 0;
            for (Direction dir : BlockStateProperties.HORIZONTAL_FACING.getPossibleValues()) {
                switch (dir) {
                    case NORTH -> y = 0;
                    case SOUTH -> y = 180;
                    case WEST -> y = -90;
                    case EAST -> y = 90;
                }

                prov.getMultipartBuilder(ctx.get()).part().modelFile(half).rotationY(y).addModel()
                        .condition(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM)
                        .condition(BlockStateProperties.HORIZONTAL_FACING, dir).end();
                prov.getMultipartBuilder(ctx.get()).part().modelFile(both).rotationY(y).addModel()
                        .condition(BlockStateProperties.SLAB_TYPE, SlabType.DOUBLE).end();
            }
        }

        public static <T extends Block> void steelBarsBlockstate(DataGenContext<Block, T> ctx, RegistrateBlockstateProvider prov) {
            // Credit to C: Deco for most of this blockstate code
            ResourceLocation barTexture = Alloyed.asResource("block/steel_bars/block");
            ResourceLocation postTexture = Alloyed.asResource("block/steel_bars/post");
            MultiPartBlockStateBuilder builder = prov.getMultipartBuilder(ctx.get());

            BlockModelBuilder sideModel = prov.models().withExistingParent(
                            "block/steel_bars/steel_bars_side", prov.mcLoc("block/iron_bars_side"))
                    .texture("bars", barTexture)
                    .texture("edge", postTexture)
                    .texture("particle", postTexture);
            BlockModelBuilder sideAltModel = prov.models().withExistingParent(
                            "block/steel_bars/steel_bars_side_alt", prov.mcLoc("block/iron_bars_side_alt"))
                    .texture("bars", barTexture)
                    .texture("edge", postTexture)
                    .texture("particle", postTexture);

            builder.part().modelFile(prov.models().withExistingParent("block/steel_bars/steel_bars_post", prov.mcLoc("block/iron_bars_post"))
                            .texture("bars", postTexture).texture("particle", postTexture)
                    ).addModel()
                    .condition(BlockStateProperties.NORTH, false)
                    .condition(BlockStateProperties.SOUTH, false)
                    .condition(BlockStateProperties.EAST, false)
                    .condition(BlockStateProperties.WEST, false)
                    .end();
            builder.part().modelFile(
                    prov.models().withExistingParent("block/steel_bars/steel_bars_post_ends", prov.mcLoc("block/iron_bars_post_ends"))
                            .texture("edge", postTexture).texture("particle", postTexture)
            ).addModel().end();
            builder.part().modelFile(sideModel).addModel().condition(BlockStateProperties.NORTH, true).end();
            builder.part().modelFile(sideModel).rotationY(90).addModel().condition(BlockStateProperties.EAST, true).end();
            builder.part().modelFile(sideAltModel).addModel().condition(BlockStateProperties.SOUTH, true).end();
            builder.part().modelFile(sideAltModel).rotationY(90).addModel().condition(BlockStateProperties.WEST, true).end();
        }

        public static <T extends Block> void steelMeshFenceBlockstate(DataGenContext<Block, T> ctx, RegistrateBlockstateProvider prov) {
            ResourceLocation post = prov.modLoc("block/steel_sheet_metal");
            ResourceLocation mesh = prov.modLoc("block/steel_chain_link");

            char[][] states = {
                    // N    S      E      W
                    {'f', 'f', 'f', 'f'}, // solo
                    {'t', 'x', 't', 'x'},   // NE corner / tri / cross
                    {'t', 'x', 'x', 't'},   // NW corner / tri / cross
                    {'x', 't', 't', 'x'},   // SE corner / tri / cross
                    {'x', 't', 'x', 't'},   // SW corner / tri / cross
                    {'t', 'f', 'f', 'f'},  // N end
                    {'f', 't', 'f', 'f'},  // S end
                    {'f', 'f', 't', 'f'},  // E end
                    {'f', 'f', 'f', 't'}   // W end
            };

            BlockModelBuilder center = prov.models().withExistingParent(
                    ctx.getName() + "_post", prov.mcLoc("block/fence_post")
            ).texture("texture", post);
            BlockModelBuilder side = prov.models().withExistingParent(
                            ctx.getName() + "_side", prov.modLoc("block/chainlink_fence_side")
                    ).texture("particle", mesh)
                    .texture("0", mesh);

            MultiPartBlockStateBuilder builder = prov.getMultipartBuilder(ctx.get());
            for (char[] state : states) {
                MultiPartBlockStateBuilder.PartBuilder part = builder.part().modelFile(center).addModel();
                if (state[0] == 't') {
                    part.condition(BlockStateProperties.NORTH, true);
                }
                else if (state[0] == 'f') {
                    part.condition(BlockStateProperties.NORTH, false);
                } // else 'x' don't care
                if (state[1] == 't') {
                    part.condition(BlockStateProperties.SOUTH, true);
                }
                else if (state[1] == 'f') {
                    part.condition(BlockStateProperties.SOUTH, false);
                } // else 'x' don't care
                if (state[2] == 't') {
                    part.condition(BlockStateProperties.EAST, true);
                }
                else if (state[2] == 'f') {
                    part.condition(BlockStateProperties.EAST, false);
                } // else 'x' don't care
                if (state[3] == 't') {
                    part.condition(BlockStateProperties.WEST, true);
                }
                else if (state[3] == 'f') {
                    part.condition(BlockStateProperties.WEST, false);
                } // else 'x' don't care
                part.end();
            }
            builder.part().modelFile(side).addModel()
                    .condition(BlockStateProperties.EAST, true).end();
            builder.part().modelFile(side)
                    .rotationY(90).addModel()
                    .condition(BlockStateProperties.SOUTH, true).end();
            builder.part().modelFile(side)
                    .rotationY(180).addModel()
                    .condition(BlockStateProperties.WEST, true).end();
            builder.part().modelFile(side)
                    .rotationY(270).addModel()
                    .condition(BlockStateProperties.NORTH, true).end();
        }
    }

}

