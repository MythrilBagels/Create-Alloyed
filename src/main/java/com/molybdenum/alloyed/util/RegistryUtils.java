package com.molybdenum.alloyed.util;

import com.simibubi.create.foundation.worldgen.OxidizingBlock;
import com.simibubi.create.repack.registrate.builders.BlockBuilder;
import com.simibubi.create.repack.registrate.builders.ItemBuilder;
import com.simibubi.create.repack.registrate.util.nullness.NonNullFunction;
import com.simibubi.create.repack.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraftforge.client.model.generators.ConfiguredModel;

public class RegistryUtils {

    // Useful functions
    public static <P> NonNullUnaryOperator<BlockBuilder<OxidizingBlock, P>> oxidizedBronzeBlockstate() {
        return b -> b.blockstate((ctx, prov) -> prov.getVariantBuilder(ctx.getEntry())
                .forAllStates(blockState -> {
                    String name = "block/oxidized/" + ctx.getName() + "_";

                    // Since bronze only has 3 oxidization states, this is where things differ.
                    int level = blockState.getValue(OxidizingBlock.OXIDIZATION);

                    if (level <= 1) {
                        name += "0";
                    } else if (level <= 5) {
                        name += "1";
                    } else {
                        name += "2";
                    }

                    return ConfiguredModel.builder()
                            .modelFile(prov.models().cubeAll(name, prov.modLoc(name)))
                            .build();
                }));
    }

    public static <P> NonNullUnaryOperator<BlockBuilder<Block, P>> existingModel() {
        return b -> b.blockstate((ctx, prov) ->
                prov.simpleBlock(
                        ctx.getEntry(),
                        prov.models()
                                .getExistingFile(prov.modLoc("block/" + ctx.getName()))
                )
        );
    }

    public static <P> NonNullUnaryOperator<BlockBuilder<Block, P>> tagBlockAndItem(ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag) {
        return b -> b
                .tag(blockTag)
                .item()
                .tag(itemTag)
                .build();
    }

}
