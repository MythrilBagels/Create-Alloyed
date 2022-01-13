package com.molybdenum.alloyed.util;

import com.simibubi.create.repack.registrate.builders.BlockBuilder;
import com.simibubi.create.repack.registrate.util.nullness.NonNullFunction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class DataUtils {

    public static <T extends Block, P> NonNullFunction<BlockBuilder<T, P>, BlockBuilder<T, P>> tagBlockAndItem(
            Tags.IOptionalNamedTag<Block> blockTag, Tags.IOptionalNamedTag<Item> itemTag) {
        return b -> b
                .tag(blockTag)
                .item()
                .tag(itemTag)
                .build();
    }

}
