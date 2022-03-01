package com.molybdenum.alloyed.common.items;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.registry.ModCompatItems;
import com.molybdenum.alloyed.common.registry.ModItems;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ModItemGroups {

    public static final ItemGroup MAIN_GROUP = new ItemGroup("main_group")
    {
        @Override
        public @NotNull ItemStack makeIcon()
        {
            return new ItemStack(ModItems.BRONZE_INGOT.get());
        }
    };

    // Tell Registrate to create a lang entry for the item group
    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate()
            .itemGroup(() -> MAIN_GROUP, "Create: Alloyed");
}
