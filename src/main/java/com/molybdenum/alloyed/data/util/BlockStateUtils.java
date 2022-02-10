package com.molybdenum.alloyed.data.util;

import com.simibubi.create.repack.registrate.builders.BlockBuilder;
import com.simibubi.create.repack.registrate.providers.DataGenContext;
import com.simibubi.create.repack.registrate.providers.RegistrateBlockstateProvider;
import com.simibubi.create.repack.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.world.level.block.Block;

public class BlockStateUtils {

    public static <T extends Block> void existingModel(DataGenContext<Block, T> ctx, RegistrateBlockstateProvider prov) {
        prov.simpleBlock(
                ctx.getEntry(),
                prov.models()
                        .getExistingFile(prov.modLoc("block/" + ctx.getName()))
        );
    }
}
