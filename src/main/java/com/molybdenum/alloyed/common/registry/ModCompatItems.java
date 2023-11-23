package com.molybdenum.alloyed.common.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.common.compat.farmersdelight.FarmersDelightCompat;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import net.minecraft.world.item.Item;

import java.util.List;

public class ModCompatItems {
    public static final ItemEntry<Item> STEEL_KNIFE = ModItems.handheldItem(
            "steel_knife",
            properties -> Alloyed.isFarmersDelightLoaded ?
                    FarmersDelightCompat.newSteelKnife(properties) :
                    new Item(properties.stacksTo(1)),
            ModTags.Items.STEEL_KNIFE, ModTags.Items.STEEL_KNIFE_FD
    );

    public static void register() {
        Alloyed.LOGGER.debug("Registering ModCompatItems!");
    }

    public static List<ItemProviderEntry<?>> getFDItems() {
        return List.of(STEEL_KNIFE);
    }
}
