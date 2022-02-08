package com.molybdenum.alloyed.common.items;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.registry.ModCompatItems;
import com.molybdenum.alloyed.common.registry.ModItems;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroups {

    public static final ItemGroup MAIN_GROUP = new ItemGroup("main_group")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ModItems.BRONZE_INGOT.get());
        }
    };
    public static final ItemGroup COMPAT_GROUP = new ItemGroup("compat_group")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ModCompatItems.STEEL_KNIFE.get());
        }
    };

    // Tell Registrate to create a lang entry for the item groups
    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate();
    static {
        REGISTRATE.itemGroup(() -> MAIN_GROUP, "Create: Alloyed");
        REGISTRATE.itemGroup(() -> COMPAT_GROUP, "Create: Alloyed Compat");
    }
}
