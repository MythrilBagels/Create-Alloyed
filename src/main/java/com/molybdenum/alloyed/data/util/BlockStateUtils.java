package com.molybdenum.alloyed.data.util;

import com.simibubi.create.foundation.worldgen.OxidizingBlock;
import com.simibubi.create.repack.registrate.builders.BlockBuilder;
import com.simibubi.create.repack.registrate.providers.DataGenContext;
import com.simibubi.create.repack.registrate.providers.RegistrateBlockstateProvider;
import com.simibubi.create.repack.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.block.Block;
import net.minecraftforge.client.model.generators.ConfiguredModel;

public class BlockStateUtils {
    public static <T extends Block> void oxidizedBronzeBlockstate(DataGenContext<Block, T> ctx, RegistrateBlockstateProvider prov) {
        prov.getVariantBuilder(ctx.getEntry())
                .forAllStates(blockState -> {
                    String name = "block/oxidized/" + ctx.getName() + "_";

                    // Since bronze only has 4 oxidization states, this is where things differ.
                    int level = blockState.getValue(OxidizingBlock.OXIDIZATION);

                    if (level <= 1) {
                        name += "0";
                    } else if (level <= 3) {
                        name += "1";
                    } else if (level <= 5) {
                        name += "2";
                    } else {
                        name += "3";
                    }

                    return ConfiguredModel.builder()
                            .modelFile(prov.models().cubeAll(name, prov.modLoc(name)))
                            .build();
                });
    }

    public static <T extends Block> void existingModel(DataGenContext<Block, T> ctx, RegistrateBlockstateProvider prov) {
        prov.simpleBlock(
                ctx.getEntry(),
                prov.models()
                        .getExistingFile(prov.modLoc("block/" + ctx.getName()))
        );
    }
}
