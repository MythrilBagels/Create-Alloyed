package com.molybdenum.alloyed.compat.famersdelight;

import com.molybdenum.alloyed.Alloyed;
import com.molybdenum.alloyed.items.ModItemTiers;
import net.minecraft.item.Item;
import vectorwing.farmersdelight.items.KnifeItem;

public class FarmersDelightCompat {
    public static KnifeItem newSteelKnifeItem(Item.Properties properties) {
        return new KnifeItem(ModItemTiers.STEEL, 0.5F, -2.0F, properties);
    }
}
