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

public class DataUtils {

    public static <P> NonNullUnaryOperator<BlockBuilder<Block, P>> tagBlockAndItem(ITag.INamedTag<Block> blockTag, ITag.INamedTag<Item> itemTag) {
        return b -> b
                .tag(blockTag)
                .item()
                .tag(itemTag)
                .build();
    }

}
