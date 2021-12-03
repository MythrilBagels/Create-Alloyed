package com.spottytheturtle.create_composition.items;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

    public static final ItemGroup TEST_GROUP = new ItemGroup("testgroup")
    {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(ModItems.BRONZE_INGOT.get());
        }
    };
}
