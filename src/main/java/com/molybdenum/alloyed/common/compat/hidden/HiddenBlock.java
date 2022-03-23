package com.molybdenum.alloyed.common.compat.hidden;

import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.lwjgl.system.NonnullDefault;

public class HiddenBlock extends Block {
    public HiddenBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    @NonnullDefault
    public void fillItemCategory(ItemGroup pTab, NonNullList<ItemStack> pItems) {
        // Don't add the item to any item category - therefore making it hidden.
    }
}
