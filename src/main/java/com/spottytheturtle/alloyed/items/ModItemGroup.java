package com.spottytheturtle.alloyed.items;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

    public static final ItemGroup TEST_GROUP = new ItemGroup("testgroup")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ModItems.BRONZE_INGOT.get());
        }
    };
}
