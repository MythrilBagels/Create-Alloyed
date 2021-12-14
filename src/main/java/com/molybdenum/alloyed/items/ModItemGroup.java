package com.molybdenum.alloyed.items;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

    public static final ItemGroup MAIN_GROUP = new ItemGroup("main_group")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ModItems.BRONZE_INGOT.get());
        }
    };
}
