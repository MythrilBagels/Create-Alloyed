package com.molybdenum.alloyed.items;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.registry.ModCompatItems;
import com.molybdenum.alloyed.registry.ModItems;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemGroup {

    public static final CreativeModeTab MAIN_GROUP = new CreativeModeTab("main_group")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ModItems.BRONZE_INGOT.get());
        }
    };
    public static final CreativeModeTab COMPAT_GROUP = new CreativeModeTab("compat_group")
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
        REGISTRATE.creativeModeTab(() -> MAIN_GROUP, "Create: Alloyed");
        REGISTRATE.creativeModeTab(() -> COMPAT_GROUP, "Create: Alloyed Compat");
    }
}
