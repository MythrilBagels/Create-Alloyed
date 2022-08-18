package com.molybdenum.alloyed.data.util;

import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class DataUtils {

    public static <T extends Block, P> NonNullFunction<BlockBuilder<T, P>, BlockBuilder<T, P>> tagBlockAndItem(
            TagKey<Block> blockTag, TagKey<Item> itemTag) {
        return b -> b
                .tag(blockTag)
                .item()
                .tag(itemTag)
                .build();
    }

}
