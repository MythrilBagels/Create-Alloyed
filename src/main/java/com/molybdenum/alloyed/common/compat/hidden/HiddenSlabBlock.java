package com.molybdenum.alloyed.common.compat.hidden;

import net.minecraft.block.SlabBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class HiddenSlabBlock extends SlabBlock {
    public HiddenSlabBlock(Properties p_i48331_1_) {
        super(p_i48331_1_);
    }

    @Override
    public void fillItemCategory(ItemGroup pTab, NonNullList<ItemStack> pItems) {
        // Don't add the item to any item category - therefore making it hidden.
    }
}
