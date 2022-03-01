package com.molybdenum.alloyed.common.compat.createdeco;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class CreateDecoCompat {
    public static Block newCatwalkBlock(AbstractBlock.Properties properties) {
        return new SteelCatwalkBlock(properties);
    }

    public static BlockItem newCatwalkBlockItem(SteelCatwalkBlock block, Item.Properties properties) {
        return new SteelCatwalkBlockItem(block ,properties);
    }
}
