package com.molybdenum.alloyed.data.util;

import com.molybdenum.alloyed.Alloyed;
import com.simibubi.create.foundation.utility.Iterate;
import com.simibubi.create.repack.registrate.providers.DataGenContext;
import com.simibubi.create.repack.registrate.providers.RegistrateBlockstateProvider;
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
            prov.getVariantBuilder(ctx.get()).forAllStates(state -> {
                String baseDirectory = "block/steel_mesh_fence/";
                boolean north, south, east, west;

                north = state.getValue(BlockStateProperties.NORTH);
                south = state.getValue(BlockStateProperties.SOUTH);
                east  = state.getValue(BlockStateProperties.EAST);
                west  = state.getValue(BlockStateProperties.WEST);

                int sides = (north ? 1 : 0)
                        + (south ? 1 : 0)
                        + (east  ? 1 : 0)
                        + (west  ? 1 : 0);

                String suffix;
                int rotationY = -1;

                switch (sides) {
                    case 4 -> suffix = "four_way";
                    case 3 -> {
                        suffix = "tri_way";
                        rotationY = north ? (south ? (east ? 90 : -90) : 0) : 180;
                    }
                    case 2 -> {
                        if ((north && south) || (east && west)) {
                            suffix = "straight";
                            rotationY = east ? 0 : 90;
                        } else {
                            suffix = "corner";
                            rotationY = north ? (east ? 0 : -90) : (east ? 90 : 180);
                        }
                    }
                    case 1 -> {
                        suffix = "end";
                        rotationY = north ? -90 : (south ? 90 : (east ? 0 : 180));
                    }
                    // fall through
                    case 0 -> suffix = "post";
                    default -> suffix = "post";
                }

                ConfiguredModel.Builder<?> builder = ConfiguredModel.builder().modelFile(
                        prov.models().getExistingFile(Alloyed.asResource(baseDirectory + suffix)));

                if (rotationY != -1) builder = builder.rotationY(rotationY);

                return builder.build();
            });
        }
    }

}

