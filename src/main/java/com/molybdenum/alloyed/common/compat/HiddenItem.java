package com.molybdenum.alloyed.common.compat;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import org.lwjgl.system.NonnullDefault;

public class HiddenItem extends Item {
    public HiddenItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    @NonnullDefault
    public void fillItemCategory(ItemGroup pGroup, NonNullList<ItemStack> pItems) {
        // Don't add the item to any item category - therefore making it hidden.
    }
}
