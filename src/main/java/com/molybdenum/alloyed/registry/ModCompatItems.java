package com.molybdenum.alloyed.registry;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.compat.farmersdelight.FarmersDelightCompat;
import com.molybdenum.alloyed.items.ModItemGroup;
import com.simibubi.create.content.AllSections;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.repack.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

public class ModCompatItems {
    private static final CreateRegistrate REGISTRATE = Alloyed.getRegistrate().creativeModeTab(() -> ModItemGroup.COMPAT_GROUP);

    // Farmer's Delight
    static { REGISTRATE.startSection(AllSections.CURIOSITIES); }

    public static final ItemEntry<Item> STEEL_KNIFE = ModItems.handheldItem(
            "steel_knife",
            properties -> Alloyed.isFarmersDelightLoaded ? FarmersDelightCompat.newSteelKnife(properties) : new Item(properties),
            ModTags.Items.STEEL_KNIFE, ModTags.Items.STEEL_KNIFE_FD
    );

    public static void register() {
        System.out.println("Registering ModCompatItems!");
    }
}
