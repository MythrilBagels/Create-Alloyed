package com.molybdenum.alloyed.registry;

import com.molybdenum.alloyed.Alloyed;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemGroup {

    public static final CreativeModeTab MAIN_GROUP = new CreativeModeTab("main_group")
    {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.BRONZE_INGOT.get());
        }
    };

    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate().creativeModeTab(
            () -> MAIN_GROUP,
            "Create: Alloyed"
    );
}
