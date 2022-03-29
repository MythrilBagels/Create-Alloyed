package com.molybdenum.alloyed.common.compat.hidden;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import org.lwjgl.system.NonnullDefault;

public class HiddenBlockItem extends BlockItem {
    public HiddenBlockItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    @NonnullDefault
    public void fillItemCategory(CreativeModeTab pTab, NonNullList<ItemStack> pItems) {
        // Don't add the item to any item category - therefore making it hidden.
    }
}
