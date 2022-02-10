package com.molybdenum.alloyed.common.compat.farmersdelight;

import com.molybdenum.alloyed.common.items.ModItemTiers;
import net.minecraft.world.item.Item;

public class FarmersDelightCompat {
    public static Item newSteelKnife(Item.Properties properties) {
        return new SteelKnife(ModItemTiers.STEEL, 0.5F, -2.0F, properties);
    }
}
