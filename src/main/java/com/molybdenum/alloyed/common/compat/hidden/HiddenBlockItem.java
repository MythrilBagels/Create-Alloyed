package com.molybdenum.alloyed.common.compat.hidden;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class HiddenBlockItem extends BlockItem {
    public HiddenBlockItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    public void fillItemCategory(ItemGroup pGroup, NonNullList<ItemStack> pItems) {
        // Don't add the item to any item category - therefore making it hidden.
    }
}
