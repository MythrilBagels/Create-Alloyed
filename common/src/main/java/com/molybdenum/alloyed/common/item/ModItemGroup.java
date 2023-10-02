package com.molybdenum.alloyed.common.item;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.registry.ModItems;
import com.molybdenum.alloyed.util.ItemUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ModItemGroup {

    public static final CreativeModeTab MAIN_GROUP = new CreativeModeTab(ItemUtils.nextTabId(), Alloyed.MOD_ID + ".main_group")
    {
        @Override
        public @NotNull ItemStack makeIcon()
        {
            return new ItemStack(ModItems.BRONZE_INGOT.get());
        }
    };

    // Tell Registrate to create a lang entry for the item groups
    private static final CreateRegistrate REGISTRATE = Alloyed.registrate()
            .creativeModeTab(() -> MAIN_GROUP, "Create: Alloyed");
}
