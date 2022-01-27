package com.molybdenum.alloyed.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.compat.famersdelight.FarmersDelightCompat;
import com.molybdenum.alloyed.items.ModItemGroups;
import com.simibubi.create.content.AllSections;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.entry.ItemEntry;
import net.minecraft.item.Item;

public class ModCompatItems {
    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate().itemGroup(() -> ModItemGroups.COMPAT_GROUP);

    // Farmer's Delight
    static { REGISTRATE.startSection(AllSections.CURIOSITIES); }

    public static final ItemEntry<Item> STEEL_KNIFE = ModItems.handheldItem(
            "steel_knife",
            properties -> Alloyed.isFarmersDelightLoaded ? FarmersDelightCompat.newSteelKnifeItem(properties) : new Item(properties),
            ModTags.Items.STEEL_KNIFE
    );

    public static void register() {}
}
