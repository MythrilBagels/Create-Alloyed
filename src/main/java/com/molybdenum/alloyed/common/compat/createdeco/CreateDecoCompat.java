package com.molybdenum.alloyed.common.compat.createdeco;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;

public class CreateDecoCompat {
    public static Block newCatwalkBlock(AbstractBlock.Properties properties) {
        return new SteelCatwalkBlock(properties);
    }

    public static @NotNull SlabBlock newVerticalSlabBlock(AbstractBlock.Properties properties) {
        return new SteelVerticalSlabBlock(properties);
    }

    public static BlockItem newCatwalkBlockItem(Block block, Item.Properties properties) {
        return new SteelCatwalkBlockItem((SteelCatwalkBlock) block ,properties);
    }
}
