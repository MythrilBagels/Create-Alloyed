package com.molybdenum.alloyed.util;

import com.simibubi.create.repack.registrate.builders.BlockBuilder;
import com.simibubi.create.repack.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.world.level.block.Block;

public class AssetUtils {

    public static <P> NonNullUnaryOperator<BlockBuilder<Block, P>> existingModel() {
        return b -> b.blockstate((ctx, prov) ->
                prov.simpleBlock(
                        ctx.getEntry(),
                        prov.models()
                                .getExistingFile(prov.modLoc("block/" + ctx.getName()))
                )
        );
    }

    public static <P> NonNullUnaryOperator<BlockBuilder<Block, P>> copyModel(String name) {
        return b -> b.blockstate((ctx, prov) -> prov.simpleBlock(
                ctx.getEntry(),
                prov.models().cubeAll(ctx.getName(), prov.modLoc(name))
        ));
    }

}
